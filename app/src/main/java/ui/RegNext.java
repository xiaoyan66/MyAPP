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
		* 用户注册2
		* @author Administrator
		*
		*/
public class RegNext extends Activity {
	private Button btnyzm;
	// 返回按钮
	private Button back;
	// 得到显示的时间
	private TextView gettime;
	// 让再次产生验证码按钮
	private Button btnagaing;
	// 验证码
	private EditText mycode;

	private MyCount mycount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_reg_next);
		ActivityCollector.addActivity(this);
		// 初始化控件
		initView();
		// 产生随机数
		RanNum();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
	// 初始化控件
	private void initView() {
		btnyzm = (Button) this.findViewById(R.id.btnyzm);
		back = (Button) this.findViewById(R.id.back);
		gettime = (TextView) this.findViewById(R.id.gettime);
		btnagaing = (Button) this.findViewById(R.id.btnagaing);
		// 第一个参数：要倒计时的时间 ;第二个参数：时间间隔
		mycount = new MyCount(10000, 1000);
		mycount.start();

		mycode = (EditText) this.findViewById(R.id.mycode);
		// 加事件
		btnyzm.setOnClickListener(listener);
		back.setOnClickListener(listener);
		btnagaing.setOnClickListener(listener);

	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btnyzm) {
				// 下一个注册页面
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

	/*********************** 使用一个内部的计时器对象 *****************************/
	public class MyCount extends CountDownTimer {
		/**
		 * MyCount的构造方法
		 *
		 * @param millisInFuture
		 *            要倒计时的时间
		 * @param countDownInterval
		 *            时间间隔
		 */
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long arg0) {
			// 在进行倒计时的时候执行的操作
			long time = arg0 / 1000;
			// 设置显示的时间
			gettime.setText(time + "秒后可以重新获得验证码!");
			if (time == 10) {
				gettime.setText(9 + "秒后可以重新获得验证码!");
			}
		}

		@Override
		public void onFinish() {
			// 倒计时完成后做的事(让不可见)
			gettime.setVisibility(View.GONE);
			btnagaing.setVisibility(View.VISIBLE);
		}
	}

	// 产生一个随机数
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
