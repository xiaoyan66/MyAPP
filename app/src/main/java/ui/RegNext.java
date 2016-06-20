package ui;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import util.ActivityCollector;


/**
 * �û�ע��2
 * @author Administrator
 *
 */
public class RegNext extends Activity {
	private Button btnyzm;
	// ���ذ�ť
	private Button back;
	// �õ���ʾ��ʱ��
	private TextView gettime;
	// ���ٴβ�����֤�밴ť
	private Button btnagaing;
	// ��֤��
	private EditText mycode;

	private MyCount mycount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_reg_next);
		ActivityCollector.addActivity(this);
		// ��ʼ���ؼ�
		initView();
		// ���������
		RanNum();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
	// ��ʼ���ؼ�
	private void initView() {
		btnyzm = (Button) this.findViewById(R.id.btnyzm);
		back = (Button) this.findViewById(R.id.back);
		gettime = (TextView) this.findViewById(R.id.gettime);
		btnagaing = (Button) this.findViewById(R.id.btnagaing);
		// ��һ��������Ҫ����ʱ��ʱ�� ;�ڶ���������ʱ����
		mycount = new MyCount(10000, 1000);
		mycount.start();

		mycode = (EditText) this.findViewById(R.id.mycode);
		// ���¼�
		btnyzm.setOnClickListener(listener);
		back.setOnClickListener(listener);
		btnagaing.setOnClickListener(listener);

	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btnyzm) {
				// ��һ��ע��ҳ��
				startActivity(new Intent(RegNext.this, RegPswSet.class));
				overridePendingTransition(R.anim.left, R.anim.right);
				RegNext.this.finish();
			}
			if (v.getId() == R.id.back) {
				startActivity(new Intent(RegNext.this, RegFirst.class));
				overridePendingTransition(R.anim.backleft, R.anim.backright);
				RegNext.this.finish();
			}
			if (v.getId() == R.id.btnagaing) {
				mycount.start();
				gettime.setVisibility(View.VISIBLE);
				btnagaing.setVisibility(View.GONE);
				gettime.setText("");
				RanNum();
			}
		}
	};

	/*********************** ʹ��һ���ڲ��ļ�ʱ������ *****************************/
	public class MyCount extends CountDownTimer {
		/**
		 * MyCount�Ĺ��췽��
		 * 
		 * @param millisInFuture
		 *            Ҫ����ʱ��ʱ��
		 * @param countDownInterval
		 *            ʱ����
		 */
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long arg0) {
			// �ڽ��е���ʱ��ʱ��ִ�еĲ���
			long time = arg0 / 1000;
			// ������ʾ��ʱ��
			gettime.setText(time + "���������»����֤��!");
			if (time == 10) {
				gettime.setText(9 + "���������»����֤��!");
			}
		}

		@Override
		public void onFinish() {
			// ����ʱ��ɺ�������(�ò��ɼ�)
			gettime.setVisibility(View.GONE);
			btnagaing.setVisibility(View.VISIBLE);
		}
	}

	// ����һ�������
	public void RanNum() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Random ran = new Random();
				int num = ran.nextInt(999999999);
				mycode.setText(String.valueOf(num));
			}
		}, 2000);
	}

}
