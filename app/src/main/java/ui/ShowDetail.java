package ui;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.administrator.ui.DetailAdapter;
import com.example.administrator.ui.DetailDAO;

import util.ActivityCollector;

/**
 * 查询所有订单详情表
 * 
 * @author YinWenBing
 * 
 */
public class ShowDetail extends Activity {
	private ListView mLvDetails;
	private List ar;
	private DetailDAO detailDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_show_detail);
		ActivityCollector.addActivity(this);
		detailDAO = new DetailDAO(ShowDetail.this);
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
	/*
	 * 加载数据
	 */
	private void initData() {
		ar = detailDAO.getAllDetails();
		DetailAdapter detailAdapter = new DetailAdapter(ShowDetail.this, ar);
		mLvDetails.setAdapter(detailAdapter);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mLvDetails = (ListView) this.findViewById(R.id.lv_details);

		mLvDetails.setOnItemClickListener(lv_listener);

	}

	OnItemClickListener lv_listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3) {
			Map map = (Map) ar.get(arg2);
			// 订单号
			String inumber = map.get("inumber").toString();
			// 根据订单号查询订单表
			Intent intent = new Intent(ShowDetail.this, WaitIncomeGoods.class);
			intent.putExtra("inumber", inumber);
			startActivity(intent);

		}
	};
}
