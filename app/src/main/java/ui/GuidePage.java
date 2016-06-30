package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.example.administrator.ui.GuidePageAdapter;

import util.ActivityCollector;


/**
 * ����ҳ
 * 
 * @author Administrator
 * 
 */
public class GuidePage extends Activity {
	private ViewPager mGuidePage;

	private List<View> ar;

	private ImageView[] mImages;


	/**
	 * AtomicInteger: 一个提供原子操作的Integer的类。
	 * 在Java语言中，++i和i++操作并不是线程安全的，在使用的时候，不可避免的会用到synchronized关键字。
	 * 而AtomicInteger则通过一种线程安全的加减操作接口。
	 */
	private AtomicInteger atomicInteger = new AtomicInteger();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide_page);
		ActivityCollector.addActivity(this);
		initView();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

	private void initView() {
		// 第一步：初始化ViewPager控件
		mGuidePage = (ViewPager) this.findViewById(R.id.guidepage);

		ViewGroup viewGroup = (ViewGroup) this.findViewById(R.id.rounddot);
		// 第二步:创建引导页
		ar = new ArrayList<View>();
		View v0 = getLayoutInflater().inflate(R.layout.page_item, null);
		LinearLayout l0 = (LinearLayout) v0.findViewById(R.id.page_item);
		l0.setBackgroundResource(R.drawable.guide_page_1);
		ar.add(l0);

		View v1 = getLayoutInflater().inflate(R.layout.page_item, null);
		LinearLayout l1 = (LinearLayout) v1.findViewById(R.id.page_item);
		l1.setBackgroundResource(R.drawable.guide_page_2);
		ar.add(l1);

		View v2 = getLayoutInflater().inflate(R.layout.page_item, null);
		LinearLayout l2 = (LinearLayout) v2.findViewById(R.id.page_item);
		l2.setBackgroundResource(R.drawable.guide_page_3);
		ar.add(l2);

		View v3 = getLayoutInflater().inflate(R.layout.page_item, null);
		LinearLayout l3 = (LinearLayout) v3.findViewById(R.id.page_item);
		l3.setBackgroundResource(R.drawable.guide_page_4);
		// 初始化“立即体验按钮”
		Button mBtnJoin = (Button) v3.findViewById(R.id.btn_join);
		// 设置按钮可见
		mBtnJoin.setVisibility(View.VISIBLE);
		// 按钮点击事件
		mBtnJoin.setOnClickListener(btn_listener);
		ar.add(l3);

		// 第三步：实例化适配器
		GuidePageAdapter adapter = new GuidePageAdapter(GuidePage.this, ar);
		// 将页面绑定到ViewPager上面
		mGuidePage.setAdapter(adapter);

		// 给ViewGroup添加图片数组
		mImages = new ImageView[ar.size()];
		for (int i = 0; i < ar.size(); i++) {
			ImageView mImage = new ImageView(GuidePage.this);
			// 设置图片宽高
			LayoutParams layoutParams = new LayoutParams(9, 9);
			// 设置四周边距
			layoutParams.setMargins(10, 5, 10, 5);
			mImage.setLayoutParams(layoutParams);

			mImages[i] = mImage;
			if (i == 0) {
				mImages[i].setBackgroundResource(R.drawable.small_bg);
			} else {
				mImages[i].setBackgroundResource(R.drawable.small_bg1);
			}

			viewGroup.addView(mImages[i]);
		}

		// ViewPager页面变化时事件
		mGuidePage.setOnPageChangeListener(gp_listener);

		// 定时器
		Timer timer = new Timer();
		// 创建并实例化定时器任务对象
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				handler.sendEmptyMessage(atomicInteger.incrementAndGet() - 1);
			}
		};

		// 参数1：任务 参数2：多久执行一次 参数3：执行周期
		timer.schedule(task, 2000, 2000);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 显示第几项
			mGuidePage.setCurrentItem(msg.what);
			if (atomicInteger.get() == ar.size()) {
				atomicInteger.set(0);
			}
		};
	};

	OnPageChangeListener gp_listener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			atomicInteger.getAndSet(arg0);
			for (int i = 0; i < mImages.length; i++) {
				mImages[i].setBackgroundResource(R.drawable.small_bg1);
				if (arg0 != i) {
					mImages[i].setBackgroundResource(R.drawable.small_bg);
				}
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}
	};

	OnClickListener btn_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.btn_join:
					startActivity(new Intent(GuidePage.this, Main.class));
					GuidePage.this.finish();
					break;

				default:
					break;
			}

		}
	};

}
