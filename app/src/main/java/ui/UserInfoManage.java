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
 * 账号管理
 * @author Administrator
 *
 */
public class UserInfoManage extends Activity {
	// 修改密码、切换账号
	private RelativeLayout mRlUpdatePassWord;
	private RelativeLayout mRlCutUserInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_info_manage);
		ActivityCollector.addActivity(this);
		// 初始化控件
		initView();
		// 加事件
		initEvent();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

	/**
	 * 加事件
	 */
	private void initEvent() {

		mRlUpdatePassWord.setOnClickListener(listener);
		mRlCutUserInfo.setOnClickListener(listener);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mRlUpdatePassWord = (RelativeLayout) this
				.findViewById(R.id.rl_update_pwd);
		mRlCutUserInfo = (RelativeLayout) this
				.findViewById(R.id.rl_cut_userinfo);

	}

	/**
	 * 监听点击事件
	 */
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.rl_update_pwd:
					// 修改密码
					startActivity(new Intent(UserInfoManage.this, UpdatePwd.class));
					break;
				case R.id.rl_cut_userinfo:
					// 切换账号
					startActivity(new Intent(UserInfoManage.this, Login.class));
					break;
				default:
					break;
			}
		}
	};
}
