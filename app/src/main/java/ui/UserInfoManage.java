package ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;

import util.ActivityCollector;


/**
 * �˺Ź���
 * @author Administrator
 *
 */
public class UserInfoManage extends Activity {
	// �޸����롢�л��˺�
	private RelativeLayout mRlUpdatePassWord;
	private RelativeLayout mRlCutUserInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_info_manage);
		ActivityCollector.addActivity(this);
		// ��ʼ���ؼ�
		initView();
		// ���¼�
		initEvent();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

	/**
	 * ���¼�
	 */
	private void initEvent() {

		mRlUpdatePassWord.setOnClickListener(listener);
		mRlCutUserInfo.setOnClickListener(listener);
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		mRlUpdatePassWord = (RelativeLayout) this
				.findViewById(R.id.rl_update_pwd);
		mRlCutUserInfo = (RelativeLayout) this
				.findViewById(R.id.rl_cut_userinfo);

	}

	/**
	 * ��������¼�
	 */
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.rl_update_pwd:
				// �޸�����
				startActivity(new Intent(UserInfoManage.this, UpdatePwd.class));
				break;
			case R.id.rl_cut_userinfo:
				// �л��˺�
				startActivity(new Intent(UserInfoManage.this, Login.class));
				break;
			default:
				break;
			}
		}
	};
}
