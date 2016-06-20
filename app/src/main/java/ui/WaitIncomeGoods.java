package ui;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.example.administrator.ui.IndentAdapter;
import com.example.administrator.ui.IndentDAO;

import util.ActivityCollector;

/**
 * ���ջ�
 * @author Administrator
 *
 */
public class WaitIncomeGoods extends Activity {
	private ListView mLvWaits;
	private IndentDAO indentDAO;
	private IndentAdapter indentAdapter;
	private List ar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wait_income_goods);
		ActivityCollector.addActivity(this);
		indentDAO = new IndentDAO(WaitIncomeGoods.this);
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

	/**
	 * ��������
	 */
	private void initData() {
		Intent intent = getIntent();
		String inumber = intent.getStringExtra("inumber");
		ar = indentDAO.getAllIndent(inumber);
		indentAdapter = new IndentAdapter(WaitIncomeGoods.this, ar);
		mLvWaits.setAdapter(indentAdapter);
	}

	private void initView() {
		mLvWaits = (ListView) this.findViewById(R.id.lv_waits);
	}

}
