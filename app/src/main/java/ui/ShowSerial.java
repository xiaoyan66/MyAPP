package ui;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.example.administrator.ui.CarDAO;
import com.example.administrator.ui.SerialCategoryAdapter;
import com.example.administrator.ui.SerialDAO;

import entity.Car;
import util.ActivityCollector;

/**
 * 根据品牌编号查询所有车系
 * 
 * @author Administrator
 * 
 */
public class ShowSerial extends Activity {
	private ExpandableListView mElSerials;
	private List ar;

	private SerialDAO serialDAO;
	private SerialCategoryAdapter serialCategoryAdapter;

	private CarDAO carDAO;
	private Car car;

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
		int bid = intent.getIntExtra("bid", 0);
		ar = serialDAO.getAllSerial(bid);
		serialCategoryAdapter = new SerialCategoryAdapter(ShowSerial.this, ar);
		mElSerials.setAdapter(serialCategoryAdapter);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mElSerials = (ExpandableListView) this.findViewById(R.id.el_serials);

		mElSerials.setOnChildClickListener(el_listener);

	}

	/**
	 * 伸缩ListView子项点击事件
	 */
	OnChildClickListener el_listener = new OnChildClickListener() {

		@Override
		public boolean onChildClick(ExpandableListView arg0, View arg1,
									int arg2, int arg3, long arg4) {
			int id = (Integer) arg1.findViewById(R.id.tv_cname).getTag();

			// Toast.makeText(ShowSerial.this, "你点击的是：" + id, 1).show();
			car = carDAO.getAllCarById(id);

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

			Intent intent = new Intent(ShowSerial.this, CarDetail.class);
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

			return false;
		}
	};
}
