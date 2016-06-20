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
public class CarDetail extends Activity
{
	private ImageView mIvCimage;// ����ͼƬ
	private TextView mTvCname1;// ��������
	private TextView mTvColdPrice;// ����ԭ��
	private TextView mTvCnewPrice;// �����ּ�
	private TextView mTvCname2;// ��������
	private TextView mTvCrank;// ��������
	private TextView mTvCengine;// ����������
	private TextView mTvCgearbox;// ����������
	private TextView mTvCstructure;// �����ṹ

	private Button mBtnCollect;// �����ղ�
	private Button mBtnBuy;// ���빺�ﳵ

	private SharedPreferences preferences;

	// ���ﳵ
	private ShoppingCart shoppingCart;
	private ShoppingCartDAO shoppingCartDAO;

	private int cid;
	private String cname;
	private float cnewprice;
	private int cimage;

	// �ղر�
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
	 * ��ʼ���ؼ�����
	 */
	private void initData()
	{
		// ����ϸ��������ͼ��ȡֵ
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

		// ���ؼ���ֵ
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
		// �����ؼ�
		mIvCimage = (ImageView) this.findViewById(R.id.iv_cimage);// ����ͼƬ
		mTvCname1 = (TextView) this.findViewById(R.id.tv_cname1);// ��������
		mTvColdPrice = (TextView) this.findViewById(R.id.tv_coldprice);// ԭ��
		mTvCnewPrice = (TextView) this.findViewById(R.id.tv_cnewprice);// �ּ�
		mTvCname2 = (TextView) this.findViewById(R.id.tv_cname2);// ��������
		mTvCrank = (TextView) this.findViewById(R.id.tv_crank);// ����
		mTvCengine = (TextView) this.findViewById(R.id.tv_cengine);// ������
		mTvCgearbox = (TextView) this.findViewById(R.id.tv_cgearbox);// ������
		mTvCstructure = (TextView) this.findViewById(R.id.tv_cstructure);// �ṹ
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
					collect = collectDAO.getOneCollect(cid, 1);
					if (collect != null)
					{
						MyToast.showToast(CarDetail.this, "���Ѿ��ղع�����Ʒ");
						return;
					}

					// ���ղر������һ������(�û�����ǰ��Ʒ��ţ�cid�������ơ��۸�)
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
						Toast.makeText(CarDetail.this, "�ղسɹ���",Toast.LENGTH_SHORT).show();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

				}
				else
				{
					Toast.makeText(CarDetail.this, "���ȵ�¼", Toast.LENGTH_SHORT).show();
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
					shoppingCart = shoppingCartDAO.getOneShoppingCart(cid, 1);
					if (shoppingCart != null)
					{
						// �޸�����
						int count = shoppingCart.getGcount();
						shoppingCart.setGcount(count + 1);
						shoppingCartDAO.updateShoppingcart(shoppingCart);
						Toast.makeText(CarDetail.this, "�ɹ����빺�복��",Toast.LENGTH_SHORT).show();
						return;
					}

					// ���ﳵ�����һ������(�û�����ǰ��Ʒ��š���������Դ���Ƿ���)
					// �û�
					int uid = preferences.getInt("uid", 0);
					// ��ǰ��Ʒ���cid
					// ����Ĭ��Ϊ1
					// ��ԴĬ��Ϊ1
					// �Ƿ���(0:ÿ����1������

					shoppingCart = new ShoppingCart();
					shoppingCart.setUid(uid);
					shoppingCart.setGid(cid);
					shoppingCart.setGcount(1);
					shoppingCart.setGsource(1);
					shoppingCart.setGbuy(0);

					try
					{
						shoppingCartDAO.addShoppingCart(shoppingCart);
						Toast.makeText(CarDetail.this, "�ɹ����빺�복��",Toast.LENGTH_SHORT).show();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

				}
				else
				{
					Toast.makeText(CarDetail.this, "���ȵ�¼",Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
			}

		}
	};

}
