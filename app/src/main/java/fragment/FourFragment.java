package fragment;

import java.util.Calendar;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.ui.CarDAO;
import com.example.administrator.ui.IndentDAO;
import com.example.administrator.ui.PartsDAO;
import com.example.administrator.ui.ShoppingCartAdapter;
import com.example.administrator.ui.ShoppingCartDAO;

import entity.Car;
import entity.Indent;
import entity.MyEntity;
import entity.Parts;
import entity.ShoppingCart;
import ui.Login;
import ui.R;
import ui.ShowIndent;
import util.ListItemClickHelp;
import util.MyToast;


/**
 * @author YinWenBing Create at 2016-3-15
 */
public class FourFragment extends Fragment implements ListItemClickHelp {
	private View view;
	private ListView mLvCarts;
	private SharedPreferences preferences;

	// 购物车
	private ShoppingCartDAO shoppingCartDAO;
	private ShoppingCartAdapter shoppingCartAdapter;
	private List ar;

	// 全选框
	private CheckBox mCbCheckAll;
	private float allprice;

	private TextView mTvAllPrcie;

	// 结算按钮
	private Button mBtnSettleAccounts;

	// 订单表
	private Indent indent;
	private IndentDAO indentDAO;

	private int id;
	private List myar;
	private ShoppingCart shoppingCart;

	private Car car;
	private CarDAO carDAO;
	private Parts parts;
	private PartsDAO partsDAO;

	// 变量
	private int myimage;
	private String myname;
	private float myprice;

	//
	private MyEntity myEntity;

	private int year;
	private int month;
	private int day;
	private int minute;
	private int hour;
	private int second;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.four_fragment, null);
		// 初始化控件
		initView();
		// 判断用户是否登录
		isLogin();
		// 加载数据
		initData();
		// 加事件
		initEvent();

		// 接收广播
		IntentFilter filter = new IntentFilter();
		filter.addAction("87.6");
		Broadcast bc = new Broadcast();
		getActivity().registerReceiver(bc, filter);// 动态注册广播接收器
		return view;
	}

	/**
	 * 事件
	 */
	private void initEvent() {
		mCbCheckAll.setOnCheckedChangeListener(cb_listener);
		// 结算按钮点击事件
		mBtnSettleAccounts.setOnClickListener(btn_listener);
	}

	/**
	 * 加载数据
	 */
	private void initData() {
		shoppingCartDAO = new ShoppingCartDAO(getActivity());
		ar = shoppingCartDAO.getAll();
		shoppingCartAdapter = new ShoppingCartAdapter(getActivity(), ar, this);
		mLvCarts.setAdapter(shoppingCartAdapter);
	}

	/**
	 * 判断用户是否登录方法
	 */
	private void isLogin() {
		preferences = getActivity().getSharedPreferences("Login",
				Context.MODE_PRIVATE);
		boolean login = preferences.getBoolean("login", false);
		if (login == true) {
		} else {
			MyToast.showToast(getActivity(), "请先登录！");
			startActivity(new Intent(getActivity(), Login.class));
		}

	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mLvCarts = (ListView) view.findViewById(R.id.lv_carts);
		mCbCheckAll = (CheckBox) view.findViewById(R.id.cb_check_all);
		mTvAllPrcie = (TextView) view.findViewById(R.id.tv_all_price);
		mBtnSettleAccounts = (Button) view
				.findViewById(R.id.btn_settle_accounts);

	}

	/**
	 * 刷新Fragment
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initData();
		mCbCheckAll.setChecked(false);
		allprice = 0.0f;
		mTvAllPrcie.setText(0 + "");
	}

	/**
	 * 全选框监听事件
	 */
	OnCheckedChangeListener cb_listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
			for (int i = 0; i < ar.size(); i++) {
				MyEntity myEntity = (MyEntity) ar.get(i);
				myEntity.setChecked(isChecked);
			}
			shoppingCartAdapter.notifyDataSetChanged();
		}
	};

	// 广播接收者
	public class Broadcast extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals("87.6")) {
				allprice = intent.getExtras().getFloat("sum");
				myar = intent.getIntegerArrayListExtra("myar");
				mTvAllPrcie.setText("" + allprice);
			}
		}
	}

	OnClickListener btn_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			if (allprice == 0.0f) {
				MyToast.showAlert(getActivity(), "提示", "请至少选择一样商品！");
				return;
			}

			// 系统时间
			Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH) + 1;
			day = c.get(Calendar.DAY_OF_MONTH);
			hour = c.get(Calendar.HOUR);
			minute = c.get(Calendar.MINUTE);
			second = c.get(Calendar.SECOND);

			String inumber = "D" + year + "" + month + "" + day + "" + hour
					+ "" + minute + "" + second;

			for (int i = 0; i < myar.size(); i++) {
				id = (Integer) myar.get(i);
				/**
				 * 根据购物车id查询要插入的字段，循环插入 1、根据id查询对应的购物车数据 2、判断数据来源
				 * 3、取出对应的图片、名称、单价
				 */
				shoppingCartDAO = new ShoppingCartDAO(getActivity());
				shoppingCart = shoppingCartDAO.getShoppingCart(id);

				int gsource = shoppingCart.getGsource();
				int gid = shoppingCart.getGid();
				int gcount = shoppingCart.getGcount();

				if (gsource == 1) {
					// 查询汽车表
					carDAO = new CarDAO(getActivity());
					car = carDAO.getAllCarById(gid);

					myimage = car.getCimage();
					myname = car.getCname();
					myprice = car.getCnewprice();

				} else {
					partsDAO = new PartsDAO(getActivity());
					parts = partsDAO.getAllPartsById(gid);

					myimage = parts.getPimage();
					myname = parts.getPname();
					myprice = parts.getPnewprice();
				}

				// 用户
				preferences = getActivity().getSharedPreferences("Login",
						Context.MODE_PRIVATE);
				int uid = preferences.getInt("uid", 0);

				indentDAO = new IndentDAO(getActivity());

				// 生成订单（图片、名称、单价、数量、订单号、用户、总价,状态）
				indent = new Indent();
				indent.setIimage(myimage);
				indent.setIname(myname);
				indent.setIprice(myprice);
				indent.setIcount(gcount);
				indent.setUid(uid);
				indent.setInumber(inumber);
				indent.setDstate(0);

				try {
					indentDAO.addIndent(indent);
					shoppingCartDAO.delShoppingcart(id);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			Intent intent = new Intent(getActivity(), ShowIndent.class);
			intent.putExtra("allprice", allprice);
			intent.putExtra("indentNumber", inumber);
			startActivity(intent);

		}
	};

	/**
	 *
	 * @param item
	 * @param widget
	 * @param position
	 * @param which
	 */
	@Override
	public void onClick(View item, View widget, int position, int which) {
		switch (which) {
			case R.id.ib_jian:
				// 修改购物车数量
				myEntity = (MyEntity) ar.get(position);
				// 购物车编号
				int sid = myEntity.getMyid();
				// 根据购物车编号查询购物车
				shoppingCartDAO = new ShoppingCartDAO(getActivity());
				shoppingCart = shoppingCartDAO.getShoppingCart(sid);

				int count = shoppingCart.getGcount();
				if (count > 1) {
					shoppingCart.setGcount(count - 1);
					shoppingCartDAO.updateShoppingcart(shoppingCart);
					initData();
				} else {
					MyToast.showToast(getActivity(), "不能再减啦，再减就没啦！");
				}

				break;
			case R.id.ib_sum:
				// 修改购物车数量
				myEntity = (MyEntity) ar.get(position);
				// 购物车编号
				int _sid = myEntity.getMyid();
				// 根据购物车编号查询购物车
				shoppingCartDAO = new ShoppingCartDAO(getActivity());
				shoppingCart = shoppingCartDAO.getShoppingCart(_sid);

				int _count = shoppingCart.getGcount();
				shoppingCart.setGcount(_count + 1);
				shoppingCartDAO.updateShoppingcart(shoppingCart);
				initData();
				break;
			default:
				break;
		}

	}

}
