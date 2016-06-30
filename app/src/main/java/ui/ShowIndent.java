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
	private List ar;
	private TextView mTvAllPrice;
	private TextView mTvIndentNumber;

	private DetailDAO detailDAO;
	private String indentNumber;
	private float allprice;

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
		IndentAdapter indentAdapter = new IndentAdapter(ShowIndent.this, ar);
		mLvIndents.setAdapter(indentAdapter);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mLvIndents = (ListView) this.findViewById(R.id.lv_indents);

		mTvAllPrice = (TextView) this.findViewById(R.id.tv_iallprice);
		mTvIndentNumber = (TextView) this.findViewById(R.id.tv_indentnumber);
		Button mBtnPayment = (Button) this.findViewById(R.id.btn_payment);

		mBtnPayment.setOnClickListener(btn_listener);
	}

	/**
	 * 确定付款按钮事件
	 */
	OnClickListener btn_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// （编号、订单号、总价、下单日期、状态、用户）
			SharedPreferences preferences = getSharedPreferences("Login", Context.MODE_PRIVATE);

			// 下单时间
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DAY_OF_MONTH);
			int hour = c.get(Calendar.HOUR);
			int minute = c.get(Calendar.MINUTE);
			int second = c.get(Calendar.SECOND);

			String dtime = year + "-" + month + "-" + day + "" + hour + ":" + minute
					+ ":" + second;
			int uid = preferences.getInt("uid", 0);

			Detail detail = new Detail();
			detail.setInumber(indentNumber);
			detail.setDallprice(allprice);
			detail.setDtime(dtime);
			detail.setDstate(1);
			detail.setUid(uid);

			for (int i = 0; i < ar.size(); i++) {
				Map map = (Map) ar.get(i);
				int _id = Integer.parseInt(map.get("_id").toString());
				Indent indent = indentDAO.getIndentById(_id);
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
