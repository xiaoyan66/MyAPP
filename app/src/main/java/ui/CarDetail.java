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


/**
 * �鿴������ϸ����
 * 
 * @author YinWenBing
 * 
 */
public class CarDetail extends Activity{
		private ImageView mIvCimage;// 汽车图片
		private TextView mTvCname1;// 汽车名称
		private TextView mTvColdPrice;// 汽车原价
		private TextView mTvCnewPrice;// 汽车现价
		private TextView mTvCname2;// 汽车名称
		private TextView mTvCrank;// 汽车级别
		private TextView mTvCengine;// 汽车发动机
		private TextView mTvCgearbox;// 汽车变速箱
		private TextView mTvCstructure;// 汽车结构

		private Button mBtnCollect;// 加入收藏
		private Button mBtnBuy;// 加入购物车

		private SharedPreferences preferences;

		// 购物车
		private ShoppingCart shoppingCart;
		private ShoppingCartDAO shoppingCartDAO;

		private int cid;
		private String cname;
		private float cnewprice;
		private int cimage;

		// 收藏表
		private Collect collect;
		private CollectDAO collectDAO;

		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_car_detail);
			ActivityCollector.addActivity(this);
			shoppingCartDAO = new ShoppingCartDAO(CarDetail.this);
			collectDAO = new CollectDAO(CarDetail.this);
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
		 * 初始化控件方法
		 */
		private void initData()
		{
			// 获得上个界面的意图，取值
			Intent intent = getIntent();
			cid = intent.getIntExtra("cid", 0);

			cimage = intent.getIntExtra("cimage", 0);
			cname = intent.getStringExtra("cname");
			float coldprice = intent.getFloatExtra("coldprice", 0);
			cnewprice = intent.getFloatExtra("cnewprice", 0);
			String crank = intent.getStringExtra("crank");
			String cengine = intent.getStringExtra("cengine");
			String cgearbox = intent.getStringExtra("cgearbox");
			String cstructure = intent.getStringExtra("cstructure");

			// 给控件赋值
			mIvCimage.setBackgroundResource(cimage);
			mTvCname1.setText(cname);
			mTvColdPrice.setText(String.valueOf(coldprice));
			mTvColdPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			mTvCnewPrice.setText(String.valueOf(cnewprice));
			mTvCname2.setText(cname);
			mTvCrank.setText(crank);
			mTvCengine.setText(cengine);
			mTvCgearbox.setText(cgearbox);
			mTvCstructure.setText(cstructure);

		}

		private void initView()
		{
			// 声明控件
			mIvCimage = (ImageView) this.findViewById(R.id.iv_cimage);// 汽车图片
			mTvCname1 = (TextView) this.findViewById(R.id.tv_cname1);// 汽车名称
			mTvColdPrice = (TextView) this.findViewById(R.id.tv_coldprice);// 原价
			mTvCnewPrice = (TextView) this.findViewById(R.id.tv_cnewprice);// 现价
			mTvCname2 = (TextView) this.findViewById(R.id.tv_cname2);// 汽车名称
			mTvCrank = (TextView) this.findViewById(R.id.tv_crank);// 级别
			mTvCengine = (TextView) this.findViewById(R.id.tv_cengine);// 发动机
			mTvCgearbox = (TextView) this.findViewById(R.id.tv_cgearbox);// 变速箱
			mTvCstructure = (TextView) this.findViewById(R.id.tv_cstructure);// 结构
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
							collect = collectDAO.getOneCollect(cid, 1);
							if (collect != null)
							{
								MyToast.showToast(CarDetail.this, "你已经收藏过该商品");
								return;
							}

							// 向收藏表中添加一条数据(用户、当前商品编号（cid）、名称、价格)
							int uid = preferences.getInt("uid", 0);
							collect = new Collect();
							collect.setUid(uid);
							collect.setGid(cid);
							collect.setGname(cname);
							collect.setGprice(cnewprice);
							collect.setGsource(1);
							collect.setGimage(cimage);

							try
							{
								collectDAO.addCollect(collect);
								Toast.makeText(CarDetail.this, "收藏成功！",Toast.LENGTH_SHORT).show();
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}

						}
						else
						{
							Toast.makeText(CarDetail.this, "请先登录", Toast.LENGTH_SHORT).show();
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
							shoppingCart = shoppingCartDAO.getOneShoppingCart(cid, 1);
							if (shoppingCart != null)
							{
								// 修改数量
								int count = shoppingCart.getGcount();
								shoppingCart.setGcount(count + 1);
								shoppingCartDAO.updateShoppingcart(shoppingCart);
								Toast.makeText(CarDetail.this, "成功加入购入车！",Toast.LENGTH_SHORT).show();
								return;
							}

							// 向购物车中添加一条数据(用户、当前商品编号、数量、来源，是否购买)
							// 用户
							int uid = preferences.getInt("uid", 0);
							// 当前商品编号cid
							// 数量默认为1
							// 来源默认为1
							// 是否购买(0:每购买；1：购买）

							shoppingCart = new ShoppingCart();
							shoppingCart.setUid(uid);
							shoppingCart.setGid(cid);
							shoppingCart.setGcount(1);
							shoppingCart.setGsource(1);
							shoppingCart.setGbuy(0);

							try
							{
								shoppingCartDAO.addShoppingCart(shoppingCart);
								Toast.makeText(CarDetail.this, "成功加入购入车！", Toast.LENGTH_SHORT).show();
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}

						}
						else
						{
							Toast.makeText(CarDetail.this, "请先登录", Toast.LENGTH_SHORT).show();
						}
						break;
					default:
						break;
				}

			}
		};

}
