package ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.ui.UserInfoDAO;

import entity.UserInfo;
import util.ActivityCollector;
import util.MyToast;

/**
 * �޸�����
 * @author Administrator
 *
 */
public class UpdatePwd extends Activity {
	private EditText mEtOldPwd;
	private EditText mEtNewPwd;
	private EditText mEtAgainNewPwd;
	private UserInfoDAO userInfoDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_update_pwd);
		userInfoDAO = new UserInfoDAO(UpdatePwd.this);
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

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		mEtOldPwd = (EditText) this.findViewById(R.id.edt_old_pw);
		mEtNewPwd = (EditText) this.findViewById(R.id.edt_new_pw);
		mEtAgainNewPwd = (EditText) this.findViewById(R.id.edt_again_new_pw);

		Button mBtnOk = (Button) this.findViewById(R.id.btn_ok);

		mBtnOk.setOnClickListener(listener);
	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			SharedPreferences preferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
			String uname = preferences.getString("uname", "");

			String oldpwd = mEtOldPwd.getText().toString().trim();
			String newpwd = mEtNewPwd.getText().toString().trim();
			String againnewpwd = mEtAgainNewPwd.getText().toString().trim();

			if (TextUtils.isEmpty(oldpwd)) {
				MyToast.showToast(UpdatePwd.this, "请输入旧密码！");
				return;
			}

			UserInfo userInfo = userInfoDAO.getUserInfoByNameAndPwd(uname, oldpwd);

			if (userInfo == null) {
				MyToast.showToast(UpdatePwd.this, "旧密码有误，请重新输入！");
				return;
			}

			if (TextUtils.isEmpty(newpwd)) {
				MyToast.showToast(UpdatePwd.this, "请输入新密码！");
				return;
			}

			if (TextUtils.isEmpty(againnewpwd)) {
				MyToast.showToast(UpdatePwd.this, "请再次输入新密码！");
				return;
			}

			if (!newpwd.equals(againnewpwd)) {
				MyToast.showToast(UpdatePwd.this, "新密码不一致，请重新输入！");
				return;
			}

			userInfo.setUpwd(againnewpwd);
			try {
				userInfoDAO.updateUserInfoPwd(userInfo);
				MyToast.showToast(UpdatePwd.this, "您的密码已变更，请重新登录！");
				startActivity(new Intent(UpdatePwd.this, Login.class));
			} catch (Exception e) {
				MyToast.showToast(UpdatePwd.this, "修改失败！");
			}

		}
	};
}
