package ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ui.CollectDAO;
import com.example.administrator.ui.ShoppingCartDAO;

import entity.Collect;
import entity.ShoppingCart;
import util.ActivityCollector;
import util.MyToast;

public class ShowParts extends Activity
{
	private ImageView mIvPimage;
	private TextView mTvPreferral;
	private TextView mTvPoldPrice;
	private TextView mTvPnewPrice;
	private TextView mTvPname;
	private TextView mTvPbrand;
	private TextView mTvPinventory;

	private TextView mTvOldPrice;
	private TextView mTvNewPrice;

	private Button mBtnCollect;// 加入收藏
	private Button mBtnBuy;// 加入购物车

	private SharedPreferences preferences;

	// 购物车
	private ShoppingCart shoppingCart;
	private ShoppingCartDAO shoppingCartDAO;
	private int pid;

	// 收藏表
	private Collect collect;
	private CollectDAO collectDAO;

	private String pname;
	private float pnewprice;
	private int pimage;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_show_parts);
		ActivityCollector.addActivity(this);
		shoppingCartDAO = new ShoppingCartDAO(ShowParts.this);
		collectDAO = new CollectDAO(ShowParts.this);
		// 初始化控件
		initView();
		// 加载数据
		initData();
	}

	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

	/**
	 * 加载数据方法
	 */
	private void initData()
	{
		Intent intent = getIntent();
		pid = intent.getIntExtra("pid", 0);
		pimage = intent.getIntExtra("pimage", 0);
		String preferral = intent.getStringExtra("preferral");
		float poldprice = intent.getFloatExtra("poldprice", 0);
		pnewprice = intent.getFloatExtra("pnewprice", 0);
		pname = intent.getStringExtra("pname");
		String pbrand = intent.getStringExtra("pbrand");
		int pinventory = intent.getIntExtra("pinventory", 0);

		mIvPimage.setBackgroundResource(pimage);
		mTvPreferral.setText(preferral);
		mTvPoldPrice.setText(String.valueOf(poldprice));
		mTvPoldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		mTvPnewPrice.setText(String.valueOf(pnewprice));
		mTvPname.setText(pname);
		mTvPbrand.setText(pbrand);
		mTvOldPrice.setText(String.valueOf(poldprice));
		mTvNewPrice.setText(String.valueOf(pnewprice));
		mTvPinventory.setText(String.valueOf(pinventory));
	}

	private void initView()
	{
		// 图片、介绍、原价、现价、名称、品牌、库存
		mIvPimage = (ImageView) this.findViewById(R.id.iv_pimage);
		mTvPreferral = (TextView) this.findViewById(R.id.tv_preferral);
		mTvPoldPrice = (TextView) this.findViewById(R.id.tv_poldprice);
		mTvPnewPrice = (TextView) this.findViewById(R.id.tv_pnewprice);
		mTvPname = (TextView) this.findViewById(R.id.tv_pname);
		mTvPbrand = (TextView) this.findViewById(R.id.tv_pbrand);
		mTvPinventory = (TextView) this.findViewById(R.id.tv_pinventory);
		mTvOldPrice = (TextView) this.findViewById(R.id.tv_oldprice);
		mTvNewPrice = (TextView) this.findViewById(R.id.tv_newprice);

		mBtnCollect = (Button) this.findViewById(R.id.btn_collect);// 加入收藏按钮
		mBtnBuy = (Button) this.findViewById(R.id.btn_buy);// 加入购物车按钮
		// 给按钮设置监听事件
		mBtnCollect.setOnClickListener(btn_listener);
		mBtnBuy.setOnClickListener(btn_listener);

	}

	/**
	 * 按钮监听事件
	 */
	OnClickListener btn_listener = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
				case R.id.btn_collect:
					// 判断用户是否登录
					preferences = getSharedPreferences("Login",
							Context.MODE_PRIVATE);// 创建SharedPreferences对象
					// 从缓存中取值
					boolean login = preferences.getBoolean("login", false);
					if (login == true)
					{
						// 判断该商品是否已经收藏
						collect = collectDAO.getOneCollect(pid, 2);
						if (collect != null)
						{
							MyToast.showToast(ShowParts.this, "你已经收藏过该商品");
							return;
						}
						int uid = preferences.getInt("uid", 0);

						collect = new Collect();

						collect.setUid(uid);
						collect.setGid(pid);
						collect.setGname(pname);
						collect.setGprice(pnewprice);
						collect.setGsource(2);
						collect.setGimage(pimage);

						try
						{
							collectDAO.addCollect(collect);
							Toast.makeText(ShowParts.this, "收藏成功！", Toast.LENGTH_SHORT).show();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}

					}
					else
					{
						Toast.makeText(ShowParts.this, "请先登录", Toast.LENGTH_SHORT).show();
					}

					break;
				case R.id.btn_buy:
					preferences = getSharedPreferences("Login",
							Context.MODE_PRIVATE);// 创建SharedPreferences对象
					// 从缓存中取值
					boolean login2 = preferences.getBoolean("login", false);
					if (login2 == true)
					{
						// 判断该商品是否已经加入购物车：是——修改数量
						shoppingCart = shoppingCartDAO.getOneShoppingCart(pid, 2);
						if (shoppingCart != null)
						{
							// 修改数量
							int count = shoppingCart.getGcount();
							shoppingCart.setGcount(count + 1);
							shoppingCartDAO.updateShoppingcart(shoppingCart);
							Toast.makeText(ShowParts.this, "成功加入购入车！", Toast.LENGTH_SHORT).show();
							return;
						}

						// 向购物车中添加一条数据(用户、当前商品编号、数量、来源，是否购买)
						// 用户
						int uid = preferences.getInt("uid", 0);
						// 当前商品编号cid
						// 数量默认为1
						// 来源默认为1
						// 是否购买默认为0

						shoppingCart = new ShoppingCart();
						shoppingCart.setUid(uid);
						shoppingCart.setGid(pid);
						shoppingCart.setGcount(1);
						shoppingCart.setGsource(2);
						shoppingCart.setGbuy(0);

						try
						{
							shoppingCartDAO.addShoppingCart(shoppingCart);
							Toast.makeText(ShowParts.this, "成功加入购入车！", Toast.LENGTH_SHORT).show();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}

					}
					else
					{
						Toast.makeText(ShowParts.this, "请先登录", Toast.LENGTH_SHORT).show();
					}
					break;
				default:
					break;
			}

		}
	};
}
