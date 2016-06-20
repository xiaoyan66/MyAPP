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

	private Button mBtnCollect;// �����ղ�
	private Button mBtnBuy;// ���빺�ﳵ

	private SharedPreferences preferences;

	// ���ﳵ
	private ShoppingCart shoppingCart;
	private ShoppingCartDAO shoppingCartDAO;
	private int pid;

	// �ղر�
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
		// ��ʼ���ؼ�
		initView();
		// ��������
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
	 * �������ݷ���
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
		// ͼƬ�����ܡ�ԭ�ۡ��ּۡ����ơ�Ʒ�ơ����
		mIvPimage = (ImageView) this.findViewById(R.id.iv_pimage);
		mTvPreferral = (TextView) this.findViewById(R.id.tv_preferral);
		mTvPoldPrice = (TextView) this.findViewById(R.id.tv_poldprice);
		mTvPnewPrice = (TextView) this.findViewById(R.id.tv_pnewprice);
		mTvPname = (TextView) this.findViewById(R.id.tv_pname);
		mTvPbrand = (TextView) this.findViewById(R.id.tv_pbrand);
		mTvPinventory = (TextView) this.findViewById(R.id.tv_pinventory);
		mTvOldPrice = (TextView) this.findViewById(R.id.tv_oldprice);
		mTvNewPrice = (TextView) this.findViewById(R.id.tv_newprice);

		mBtnCollect = (Button) this.findViewById(R.id.btn_collect);// �����ղذ�ť
		mBtnBuy = (Button) this.findViewById(R.id.btn_buy);// ���빺�ﳵ��ť
		// ����ť���ü����¼�
		mBtnCollect.setOnClickListener(btn_listener);
		mBtnBuy.setOnClickListener(btn_listener);

	}

	/**
	 * ��ť�����¼�
	 */
	OnClickListener btn_listener = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.btn_collect:
				// �ж��û��Ƿ��¼
				preferences = getSharedPreferences("Login",
						Context.MODE_PRIVATE);// ����SharedPreferences����
				// �ӻ�����ȡֵ
				boolean login = preferences.getBoolean("login", false);
				if (login == true)
				{
					// �жϸ���Ʒ�Ƿ��Ѿ��ղ�
					collect = collectDAO.getOneCollect(pid, 2);
					if (collect != null)
					{
						MyToast.showToast(ShowParts.this, "���Ѿ��ղع�����Ʒ");
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
						Toast.makeText(ShowParts.this, "�ղسɹ���", 1).show();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

				}
				else
				{
					Toast.makeText(ShowParts.this, "���ȵ�¼", 1).show();
				}

				break;
			case R.id.btn_buy:
				preferences = getSharedPreferences("Login",
						Context.MODE_PRIVATE);// ����SharedPreferences����
				// �ӻ�����ȡֵ
				boolean login2 = preferences.getBoolean("login", false);
				if (login2 == true)
				{
					// �жϸ���Ʒ�Ƿ��Ѿ����빺�ﳵ���ǡ����޸�����
					shoppingCart = shoppingCartDAO.getOneShoppingCart(pid, 2);
					if (shoppingCart != null)
					{
						// �޸�����
						int count = shoppingCart.getGcount();
						shoppingCart.setGcount(count + 1);
						shoppingCartDAO.updateShoppingcart(shoppingCart);
						Toast.makeText(ShowParts.this, "�ɹ����빺�복��", 1).show();
						return;
					}

					// ���ﳵ�����һ������(�û�����ǰ��Ʒ��š���������Դ���Ƿ���)
					// �û�
					int uid = preferences.getInt("uid", 0);
					// ��ǰ��Ʒ���cid
					// ����Ĭ��Ϊ1
					// ��ԴĬ��Ϊ1
					// �Ƿ���Ĭ��Ϊ0

					shoppingCart = new ShoppingCart();
					shoppingCart.setUid(uid);
					shoppingCart.setGid(pid);
					shoppingCart.setGcount(1);
					shoppingCart.setGsource(2);
					shoppingCart.setGbuy(0);

					try
					{
						shoppingCartDAO.addShoppingCart(shoppingCart);
						Toast.makeText(ShowParts.this, "�ɹ����빺�복��", 1).show();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

				}
				else
				{
					Toast.makeText(ShowParts.this, "���ȵ�¼", 1).show();
				}
				break;
			default:
				break;
			}

		}
	};
}
