package ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import fragment.FiveFragment;
import fragment.FourFragment;
import fragment.OneFragment;
import fragment.ThreeFragment;
import fragment.TwoFragment;
import util.ActivityCollector;


/**
 * ������
 *
 * @author Administrator
 */
public class Main extends FragmentActivity {
	private OneFragment oneFragment;
	private TwoFragment twoFragment;
	private ThreeFragment threeFragment;
	private FourFragment fourFragment;
	private FiveFragment fiveFragment;

	private ImageView mOneImage;
	private ImageView mTwoImage;
	private ImageView mThreeImage;
	private ImageView mFourImage;
	private ImageView mFiveImage;

	private TextView mOneText;
	private TextView mTwoText;
	private TextView mThreeText;
	private TextView mFourText;
	private TextView mFiveText;

	private FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		ActivityCollector.addActivity(this);
		manager = getSupportFragmentManager();
		// 初始化控件
		initView();

		getFragment(1);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
	// 初始化控件
	private void initView() {
		LinearLayout mOneLay = (LinearLayout) this.findViewById(R.id.ll_tab_1);
		LinearLayout mTwoLay = (LinearLayout) this.findViewById(R.id.ll_tab_2);
		LinearLayout mThreeLay = (LinearLayout) this.findViewById(R.id.ll_tab_3);
		LinearLayout mFourLay = (LinearLayout) this.findViewById(R.id.ll_tab_4);
		LinearLayout mFiveLay = (LinearLayout) this.findViewById(R.id.ll_tab_5);

		mOneImage = (ImageView) this.findViewById(R.id.iv_tab_1);
		mTwoImage = (ImageView) this.findViewById(R.id.iv_tab_2);
		mThreeImage = (ImageView) this.findViewById(R.id.iv_tab_3);
		mFourImage = (ImageView) this.findViewById(R.id.iv_tab_4);
		mFiveImage = (ImageView) this.findViewById(R.id.iv_tab_5);

		mOneText = (TextView) this.findViewById(R.id.tv_tab_1);
		mTwoText = (TextView) this.findViewById(R.id.tv_tab_2);
		mThreeText = (TextView) this.findViewById(R.id.tv_tab_3);
		mFourText = (TextView) this.findViewById(R.id.tv_tab_4);
		mFiveText = (TextView) this.findViewById(R.id.tv_tab_5);

		// Tab点击事件
		mOneLay.setOnClickListener(lay_listener);
		mTwoLay.setOnClickListener(lay_listener);
		mThreeLay.setOnClickListener(lay_listener);
		mFourLay.setOnClickListener(lay_listener);
		mFiveLay.setOnClickListener(lay_listener);
	}

	OnClickListener lay_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.ll_tab_1) {
				getFragment(1);

			}
			if (v.getId() == R.id.ll_tab_2) {
				getFragment(2);

			}
			if (v.getId() == R.id.ll_tab_3) {
				getFragment(3);

			}
			if (v.getId() == R.id.ll_tab_4) {
				getFragment(4);

			}
			if (v.getId() == R.id.ll_tab_5) {
				getFragment(5);

			}
		}

	};

	private void getFragment(int i) {
		// 重置图片和字体为暗色
		resetImageAndText();
		// 开启事务
		FragmentTransaction transaction = manager.beginTransaction();
		// 隐藏所有Fragment
		hideFragment(transaction);

		if (i == 1) {
			mOneImage.setImageResource(R.drawable.main_tab1_pressed);
			mOneText.setTextColor(this.getResources().getColor(R.color.orange));
			if (oneFragment == null) {
				oneFragment = new OneFragment();
				transaction.add(R.id.fl_content, oneFragment);

				// 调用Fragment之间通信的方法
				oneFragment.jumpFragment(new MyCommunication() {

					@Override
					public void getResultFragment(int postion) {
						getFragment(postion);
					}
				});
			} else {
				transaction.show(oneFragment);
			}
		}

		if (i == 2) {
			mTwoImage.setImageResource(R.drawable.main_tab2_pressed);
			mTwoText.setTextColor(this.getResources().getColor(R.color.orange));
			if (twoFragment == null) {
				twoFragment = new TwoFragment();
				transaction.add(R.id.fl_content, twoFragment);
			} else {
				transaction.show(twoFragment);
			}
		}

		if (i == 3) {
			mThreeImage.setImageResource(R.drawable.main_tab3_pressed);
			mThreeText.setTextColor(this.getResources()
					.getColor(R.color.orange));
			if (threeFragment == null) {

				threeFragment = new ThreeFragment();
				transaction.add(R.id.fl_content, threeFragment);
			} else {
				transaction.show(threeFragment);
			}
		}

		if (i == 4) {
			mFourImage.setImageResource(R.drawable.main_tab4_pressed);
			mFourText
					.setTextColor(this.getResources().getColor(R.color.orange));
			if (fourFragment == null) {
				fourFragment = new FourFragment();
				transaction.add(R.id.fl_content, fourFragment);
			} else {
				transaction.show(fourFragment);
			}
		}

		if (i == 5) {
			mFiveImage.setImageResource(R.drawable.main_tab5_pressed);
			mFiveText
					.setTextColor(this.getResources().getColor(R.color.orange));
			if (fiveFragment == null) {
				fiveFragment = new FiveFragment();
				transaction.add(R.id.fl_content, fiveFragment);
			} else {
				transaction.show(fiveFragment);
			}
		}

		// 提交事务！！！
		transaction.commit();
	}

	// 隐藏所有的Fragment
	private void hideFragment(FragmentTransaction transaction) {
		if (oneFragment != null) {
			transaction.hide(oneFragment);
		}
		if (twoFragment != null) {
			transaction.hide(twoFragment);
		}
		if (threeFragment != null) {
			transaction.hide(threeFragment);
		}
		if (fourFragment != null) {
			transaction.hide(fourFragment);
		}
		if (fiveFragment != null) {
			transaction.hide(fiveFragment);
		}

	}

	// 重置图片为暗色
	private void resetImageAndText() {
		mOneImage.setImageResource(R.drawable.main_tab1_nomal);
		mOneText.setTextColor(this.getResources().getColor(R.color.black));

		mTwoImage.setImageResource(R.drawable.main_tab2_nomal);
		mTwoText.setTextColor(this.getResources().getColor(R.color.black));

		mThreeImage.setImageResource(R.drawable.main_tab3_nomal);
		mThreeText.setTextColor(this.getResources().getColor(R.color.black));

		mFourImage.setImageResource(R.drawable.main_tab4_nomal);
		mFourText.setTextColor(this.getResources().getColor(R.color.black));

		mFiveImage.setImageResource(R.drawable.main_tab5_nomal);
		mFiveText.setTextColor(this.getResources().getColor(R.color.black));
	}

	/**
	 * 定义一个接口，实现两个Fragment之间的通信
	 */
	public interface MyCommunication {
		public void getResultFragment(int indx);
	}

}
