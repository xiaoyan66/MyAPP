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

import com.example.administrator.ui.CarDAO;
import com.example.administrator.ui.CollectAdapter;
import com.example.administrator.ui.CollectDAO;
import com.example.administrator.ui.PartsDAO;

import entity.Car;
import entity.Parts;
import util.ActivityCollector;

/**
 * 
 * @author YinWenBing
 * 
 */
public class ShowCollect extends Activity {
	private ListView mLvCollects;
	private CollectDAO collectDAO;
	private CollectAdapter collectAdapter;
	private List ar;

	// 汽车表
	private Car car;
	private CarDAO carDAO;

	// 饰品
	private Parts parts;
	private PartsDAO partsDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_show_collect);
		ActivityCollector.addActivity(this);
		collectDAO = new CollectDAO(ShowCollect.this);
		carDAO = new CarDAO(ShowCollect.this);
		partsDAO = new PartsDAO(ShowCollect.this);
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
		ar = collectDAO.getAllCollect();
		collectAdapter = new CollectAdapter(ShowCollect.this, ar);
		mLvCollects.setAdapter(collectAdapter);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mLvCollects = (ListView) this.findViewById(R.id.lv_collects);

		// ListView点击事件
		mLvCollects.setOnItemClickListener(lv_listener);
	}

	/**
	 * ListView点击事件
	 */
	OnItemClickListener lv_listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3) {
			Map map = (Map) ar.get(arg2);
			int gid = Integer.parseInt(map.get("gid").toString());
			int gsource = Integer.parseInt(map.get("gsource").toString());
			if (gsource == 1) {
				// 根据物品编号查询对应的汽车信息
				car = carDAO.getAllCarById(gid);
				// 车辆（编号，车系，名称，图片，级别、发动机、变速箱、结构、原价、现价）
				int cid = car.get_id();
				int sid = car.getSid();
				String cname = car.getCname();
				int cimage = car.getCimage();
				String crank = car.getCrank();
				String cengine = car.getCengine();
				String cgearbox = car.getCgearbox();
				String cstructure = car.getCstructure();
				float coldprice = car.getColdprice();
				float cnewprice = car.getCnewprice();

				Intent intent = new Intent(ShowCollect.this, CarDetail.class);
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
				ShowCollect.this.finish();
			} else {
				// 根据物品编号查询对应的饰品
				parts = partsDAO.getAllPartsById(gid);

				int pid = parts.get_id();
				String pname = parts.getPname();
				int pimage = parts.getPimage();
				int pinventory = parts.getPinventory();
				float poldprice = parts.getPoldprice();
				float pnewprice = parts.getPnewprice();
				String pbrand = parts.getPbrand();
				String preferral = parts.getPreferral();

				Intent intent = new Intent(ShowCollect.this, ShowParts.class);
				intent.putExtra("pid", pid);
				intent.putExtra("pname", pname);
				intent.putExtra("pimage", pimage);
				intent.putExtra("pinventory", pinventory);
				intent.putExtra("poldprice", poldprice);
				intent.putExtra("pnewprice", pnewprice);
				intent.putExtra("pbrand", pbrand);
				intent.putExtra("preferral", preferral);
				startActivity(intent);
				ShowCollect.this.finish();

			}
		}
	};
}
