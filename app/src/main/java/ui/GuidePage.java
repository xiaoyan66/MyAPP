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
	private Button mBtnJoin;

	private List<View> ar;
	private GuidePageAdapter adapter;

	private ImageView[] mImages;
	private ImageView mImage;

	/**
	 * AtomicInteger: һ���ṩԭ�Ӳ�����Integer���ࡣ
	 * ��Java�����У�++i��i++�����������̰߳�ȫ�ģ���ʹ�õ�ʱ�򣬲��ɱ���Ļ��õ�synchronized�ؼ��֡�
	 * ��AtomicInteger��ͨ��һ���̰߳�ȫ�ļӼ������ӿڡ�
	 */
	private AtomicInteger atomicInteger = new AtomicInteger();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide_page);
		ActivityCollector.addActivity(this);
		// ��ʼ���ؼ�
		initView();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

	private void initView() {
		// ��һ������ʼ��ViewPager�ؼ�
		mGuidePage = (ViewPager) this.findViewById(R.id.guidepage);

		ViewGroup viewGroup = (ViewGroup) this.findViewById(R.id.rounddot);
		// �ڶ���:��������ҳ
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
		// ��ʼ�����������鰴ť��
		mBtnJoin = (Button) v3.findViewById(R.id.btn_join);
		// ���ð�ť�ɼ�
		mBtnJoin.setVisibility(View.VISIBLE);
		// ��ť����¼�
		mBtnJoin.setOnClickListener(btn_listener);
		ar.add(l3);

		// ��������ʵ����������
		adapter = new GuidePageAdapter(GuidePage.this, ar);
		// ��ҳ��󶨵�ViewPager����
		mGuidePage.setAdapter(adapter);

		// ��ViewGroup���ͼƬ����
		mImages = new ImageView[ar.size()];
		for (int i = 0; i < ar.size(); i++) {
			mImage = new ImageView(GuidePage.this);
			// ����ͼƬ���
			LayoutParams layoutParams = new LayoutParams(9, 9);
			// �������ܱ߾�
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

		// ViewPagerҳ��仯ʱ�¼�
		mGuidePage.setOnPageChangeListener(gp_listener);

		// ��ʱ��
		Timer timer = new Timer();
		// ������ʵ������ʱ���������
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				handler.sendEmptyMessage(atomicInteger.incrementAndGet() - 1);
			}
		};

		// ����1������ ����2�����ִ��һ�� ����3��ִ������
		timer.schedule(task, 2000, 2000);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// ��ʾ�ڼ���
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
