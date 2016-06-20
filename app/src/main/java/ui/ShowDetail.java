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
 * ��ѯ���ж��������
 * 
 * @author YinWenBing
 * 
 */
public class ShowDetail extends Activity {
	private ListView mLvDetails;
	private List ar;
	private DetailDAO detailDAO;
	private DetailAdapter detailAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_show_detail);
		ActivityCollector.addActivity(this);
		detailDAO = new DetailDAO(ShowDetail.this);
		// ��ʼ���ؼ�
		initView();
		// ��������
		initData();
	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
	/*
	 * ��������
	 */
	private void initData() {
		ar = detailDAO.getAllDetails();
		detailAdapter = new DetailAdapter(ShowDetail.this, ar);
		mLvDetails.setAdapter(detailAdapter);
	}

	/**
	 * ��ʼ���ؼ�
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
			// ������
			String inumber = map.get("inumber").toString();
			// ���ݶ����Ų�ѯ������
			Intent intent = new Intent(ShowDetail.this, WaitIncomeGoods.class);
			intent.putExtra("inumber", inumber);
			startActivity(intent);

		}
	};
}
