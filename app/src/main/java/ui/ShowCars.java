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

import com.example.administrator.ui.CarAdapter;
import com.example.administrator.ui.CarDAO;

import util.ActivityCollector;


/**
 * ��ʾ����
 * @author Administrator
 *
 */
public class ShowCars extends Activity {
	private ListView mLvCars;
	private int sid;

	private CarDAO carDAO;
	private CarAdapter carAdapter;
	private List ar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_show_cars);
		ActivityCollector.addActivity(this);
		carDAO = new CarDAO(ShowCars.this);
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
	private void initData() {
		Intent intent = getIntent();
		sid = intent.getIntExtra("sid", 0);
		ar = carDAO.getAllCarBySid(sid);
		carAdapter = new CarAdapter(ShowCars.this, ar);
		mLvCars.setAdapter(carAdapter);
	}

	private void initView() {
		mLvCars = (ListView) this.findViewById(R.id.lv_cars);
		mLvCars.setOnItemClickListener(lv_listener);
	}

	OnItemClickListener lv_listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Map map = (Map) ar.get(arg2);
			// ��������ţ���ϵ�����ƣ�ͼƬ�����𡢷������������䡢�ṹ��ԭ�ۡ��ּۣ�
			int cid = Integer.parseInt(map.get("cid").toString());
			int sid = Integer.parseInt(map.get("sid").toString());
			String cname = map.get("cname").toString();
			int cimage = Integer.parseInt(map.get("cimage").toString());
			String crank = map.get("crank").toString();
			String cengine = map.get("cengine").toString();
			String cgearbox = map.get("cgearbox").toString();
			String cstructure = map.get("cstructure").toString();
			float coldprice = Float.parseFloat(map.get("coldprice").toString());
			float cnewprice = Float.parseFloat(map.get("cnewprice").toString());

			Intent intent = new Intent(ShowCars.this, CarDetail.class);
			intent.putExtra("cid", cid);
			intent.putExtra("sid", sid);
			intent.putExtra("cname", cname);
			intent.putExtra("cimage", cimage);
			intent.putExtra("crank", crank);
			intent.putExtra("cengine", cengine);
			intent.putExtra("cgearbox", cgearbox);
			intent.putExtra("cstructure", cstructure);
			intent.putExtra("coldprice", coldprice);
			intent.putExtra("cnewprice", cnewprice);

			startActivity(intent);
		}
	};

}
