package ui;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.ui.DetailDAO;
import com.example.administrator.ui.IndentAdapter;
import com.example.administrator.ui.IndentDAO;

import entity.Detail;
import entity.Indent;
import util.ActivityCollector;
import util.MyToast;

public class ShowIndent extends Activity {

	private ListView mLvIndents;
	private IndentDAO indentDAO;
	private IndentAdapter indentAdapter;
	private List ar;
	private TextView mTvAllPrice;
	private TextView mTvIndentNumber;

	// 确定付款
	private Button mBtnPayment;

	// 订单详情
	private Detail detail;
	private DetailDAO detailDAO;
	private String indentNumber;
	private float allprice;

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;

	private String dtime;

	private SharedPreferences preferences;

	private Indent indent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_show_indent);
		ActivityCollector.addActivity(this);
		indentDAO = new IndentDAO(ShowIndent.this);
		detailDAO = new DetailDAO(ShowIndent.this);
		// 初始化控件
		initView();
		// 加载数据
		initData();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

	/**
	 * 加载数据
	 */
	private void initData() {
		Intent intent = getIntent();
		allprice = intent.getFloatExtra("allprice", 0);
		indentNumber = intent.getStringExtra("indentNumber");
		mTvAllPrice.setText(allprice + "");
		mTvIndentNumber.setText(indentNumber);

		ar = indentDAO.getAllIndent(indentNumber);
		indentAdapter = new IndentAdapter(ShowIndent.this, ar);
		mLvIndents.setAdapter(indentAdapter);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mLvIndents = (ListView) this.findViewById(R.id.lv_indents);

		mTvAllPrice = (TextView) this.findViewById(R.id.tv_iallprice);
		mTvIndentNumber = (TextView) this.findViewById(R.id.tv_indentnumber);
		mBtnPayment = (Button) this.findViewById(R.id.btn_payment);

		mBtnPayment.setOnClickListener(btn_listener);
	}

	/**
	 * 监听返回键
	 */
	// @Override
	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	// if (keyCode == KeyEvent.KEYCODE_BACK) {
	// // 创建退出对话框
	// AlertDialog isExit = new AlertDialog.Builder(this).create();
	// // 设置对话框标题
	// isExit.setTitle("系统提示");
	// // 设置对话框消息
	// isExit.setMessage("确定要退出吗？如果退出订单将被清空！");
	//
	// // 添加选择按钮并注册监听
	// isExit.setButton("确定", listener);
	// isExit.setButton2("取消", listener);
	// // 显示对话框
	// isExit.show();
	// }
	// return false;
	// }

	// /** 监听对话框里面的button点击事件 */
	// DialogInterface.OnClickListener listener = new
	// DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int which) {
	// switch (which) {
	// case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
	// // 清空订单详情
	// try {
	// indentDAO.delAll();
	// finish();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// break;
	// case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
	// break;
	// default:
	// break;
	// }
	// }
	// };

	/**
	 * 确定付款按钮事件
	 */
	OnClickListener btn_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// （编号、订单号、总价、下单日期、状态、用户）
			preferences = getSharedPreferences("Login", Context.MODE_PRIVATE);

			// 下单时间
			Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH) + 1;
			day = c.get(Calendar.DAY_OF_MONTH);
			hour = c.get(Calendar.HOUR);
			minute = c.get(Calendar.MINUTE);
			second = c.get(Calendar.SECOND);

			dtime = year + "-" + month + "-" + day + "" + hour + ":" + minute
					+ ":" + second;
			int uid = preferences.getInt("uid", 0);

			detail = new Detail();
			detail.setInumber(indentNumber);
			detail.setDallprice(allprice);
			detail.setDtime(dtime);
			detail.setDstate(1);
			detail.setUid(uid);

			for (int i = 0; i < ar.size(); i++) {
				Map map = (Map) ar.get(i);
				int _id = Integer.parseInt(map.get("_id").toString());
				indent = indentDAO.getIndentById(_id);
				indent.setDstate(1);
				indentDAO.updateIndent(indent);
			}

			try {
				detailDAO.addDetail(detail);
				startActivity(new Intent(ShowIndent.this, ShowDetail.class));
				ShowIndent.this.finish();
				MyToast.showToast(ShowIndent.this, "付款成功！");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	};
}
