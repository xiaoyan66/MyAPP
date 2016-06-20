package ui;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.example.administrator.ui.MyIndentAdapter;

import fragment.CompleteIndent;
import fragment.NoCompleteIndent;

/**
 * �ҵĶ���
 * @author Administrator
 *
 */
public class MyIndent extends FragmentActivity {
	private CompleteIndent completeIndent;// ����ɶ���
	private NoCompleteIndent noCompleteIndent;// δ��ɶ���

	private ViewPager mVpMyIndent;
	private Button mBtnCompleteYes;
	private Button mBtnCompleteNo;

	private FragmentManager fragmentManager;
	private MyIndentAdapter myIndentAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_indent);
		fragmentManager = getSupportFragmentManager();
		// ��ʼ���ؼ�
		initView();
		// ���¼�
		initEvent();
	}

	/**
	 * ���¼�
	 */
	private void initEvent() {
		// ��ť����¼�
		mBtnCompleteYes.setOnClickListener(listener);
		mBtnCompleteNo.setOnClickListener(listener);

		// ViewPager�����¼�
		mVpMyIndent.setOnPageChangeListener(vp_listener);
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		mVpMyIndent = (ViewPager) this.findViewById(R.id.vp_myindent);
		mBtnCompleteYes = (Button) this.findViewById(R.id.btn_complete_yes);
		mBtnCompleteNo = (Button) this.findViewById(R.id.btn_complete_no);

		// ʵ����FragMent
		completeIndent = new CompleteIndent();
		noCompleteIndent = new NoCompleteIndent();

		// ��FragMent��ӵ�������
		List ar = new ArrayList();
		ar.add(completeIndent);
		ar.add(noCompleteIndent);

		myIndentAdapter = new MyIndentAdapter(fragmentManager, ar);
		mVpMyIndent.setAdapter(myIndentAdapter);

		// Ĭ��ѡ���һ��
		mVpMyIndent.setCurrentItem(0);

	}

	/**
	 * ��ť����¼�
	 */
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btn_complete_yes) {
				mVpMyIndent.setCurrentItem(0);
			}
			if (v.getId() == R.id.btn_complete_no) {
				mVpMyIndent.setCurrentItem(1);
			}
		}
	};

	/**
	 * ViewPager�����¼�
	 */
	OnPageChangeListener vp_listener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// �����ʽ
			clearStyle();
			switch (arg0) {
			case 0:
				mBtnCompleteYes.setBackgroundColor(Color.parseColor("#e0620d"));

				break;
			case 1:
				mBtnCompleteNo.setBackgroundColor(Color.parseColor("#e0620d"));

				break;
			default:
				break;
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

	/**
	 * �����ʽ
	 */
	private void clearStyle() {
		mBtnCompleteYes.setBackgroundColor(Color.parseColor("#FF5809"));
		mBtnCompleteNo.setBackgroundColor(Color.parseColor("#FF5809"));
	}
}
