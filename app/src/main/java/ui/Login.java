package ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.ui.UserInfoDAO;

import entity.UserInfo;
import util.ActivityCollector;


/**
 * �û���¼
 * 
 * @author YinWenBing Create at 2016-3-13 ����16��16
 * 
 */
public class Login extends Activity {
	// 控件
	private EditText mEdtUname;// 账号
	private EditText mEdtUpwd;// 密码

	private CheckBox mCbShowPwd;// 显示密码
	private Button mBtnLogin;// 登录
	private Button mBtnRegister;// 注册

	// 实体层、业务逻辑层
	private UserInfo userInfo;
	private UserInfoDAO userInfoDAO;

	// 变量
	private String uname;
	private String upwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		ActivityCollector.addActivity(this);
		userInfoDAO = new UserInfoDAO(Login.this);
		// 初始化控件
		initView();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 创建提示窗口
			AlertDialog isExit = new AlertDialog.Builder(Login.this).create();
			// 设置窗口标题
			isExit.setTitle("系统提示");
			// 设置窗口内容
			isExit.setMessage("你确定退出吗？");
			// 添加按钮
			isExit.setButton("确定", isExitlistener);
			isExit.setButton2("取消", isExitlistener);
			// 显示窗口
			isExit.show();
		}
		return false;
	}

	/**
	 * 弹窗按钮事件
	 */

	DialogInterface.OnClickListener isExitlistener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			switch (arg1) {
				case AlertDialog.BUTTON_POSITIVE:
					// 确定
					ActivityCollector.finishAll();
					break;
				case AlertDialog.BUTTON_NEGATIVE:

					break;
				default:
					break;
			}

		}
	};

	/**
	 * 初始化控件
	 */
	private void initView() {
		mEdtUname = (EditText) this.findViewById(R.id.et_uname);
		mEdtUpwd = (EditText) this.findViewById(R.id.et_upwd);

		mCbShowPwd = (CheckBox) this.findViewById(R.id.cb_show_pwd);
		mBtnLogin = (Button) this.findViewById(R.id.btn_login);
		mBtnRegister = (Button) this.findViewById(R.id.btn_register);

		// 加事件
		mBtnLogin.setOnClickListener(listener);
		mBtnRegister.setOnClickListener(listener);
		mCbShowPwd.setOnCheckedChangeListener(cb_listener);
	}

	/**
	 * 显示密码
	 */
	OnCheckedChangeListener cb_listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			if (mCbShowPwd.isChecked()) {
				// 显示密码
				mEdtUpwd.setTransformationMethod(HideReturnsTransformationMethod
						.getInstance());
			} else {
				// 不显示
				mEdtUpwd.setTransformationMethod(PasswordTransformationMethod
						.getInstance());
			}
		}
	};
	/**
	 * 按钮监听事件
	 */
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.btn_login:
					// 获取用户输入的值
					uname = mEdtUname.getText().toString();
					upwd = mEdtUpwd.getText().toString();

					userInfo = userInfoDAO.getUserInfoByNameAndPwd(uname, upwd);

					if (!TextUtils.isEmpty(uname)) {
						if (!TextUtils.isEmpty(upwd)) {
							if (userInfo != null) {

								SharedPreferences preferences = getSharedPreferences(
										"Login", Context.MODE_PRIVATE);
								Editor editor = preferences.edit();
								editor.putBoolean("login", true);
								editor.putString("uname", uname);
								editor.putInt("uid", userInfo.get_id());
								editor.putInt("uimage", userInfo.getUimage());
								editor.commit();
								startActivity(new Intent(Login.this, Main.class));
								Toast.makeText(Login.this, "登录成功！", Toast.LENGTH_SHORT).show();
								Login.this.finish();
							} else {
								Toast.makeText(Login.this, "账号或密码错误，请重新输入！",Toast.LENGTH_SHORT)
										.show();
							}
						} else {
							Toast.makeText(Login.this, "请输入密码！", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(Login.this, "请输入账号！", Toast.LENGTH_SHORT).show();
					}

					break;
				case R.id.btn_register:
					// 注册
					startActivity(new Intent(Login.this, RegFirst.class));
					overridePendingTransition(R.anim.left, R.anim.right);
					Login.this.finish();
					break;
				default:
					break;
			}
		}
	};

}
