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
	// �ؼ�
	private EditText mEdtUname;// �˺�
	private EditText mEdtUpwd;// ����

	private CheckBox mCbShowPwd;// ��ʾ����
	private Button mBtnLogin;// ��¼
	private Button mBtnRegister;// ע��

	// ʵ��㡢ҵ���߼���
	private UserInfo userInfo;
	private UserInfoDAO userInfoDAO;

	// ����
	private String uname;
	private String upwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ر�����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		ActivityCollector.addActivity(this);
		userInfoDAO = new UserInfoDAO(Login.this);
		// ��ʼ���ؼ�
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
			// ������ʾ����
			AlertDialog isExit = new AlertDialog.Builder(Login.this).create();
			// ���ô��ڱ���
			isExit.setTitle("ϵͳ��ʾ");
			// ���ô�������
			isExit.setMessage("��ȷ���˳���");
			// ��Ӱ�ť
			isExit.setButton("ȷ��", isExitlistener);
			isExit.setButton2("ȡ��", isExitlistener);
			// ��ʾ����
			isExit.show();
		}
		return false;
	}

	/**
	 * ������ť�¼�
	 */

	DialogInterface.OnClickListener isExitlistener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			switch (arg1) {
			case AlertDialog.BUTTON_POSITIVE:
				// ȷ��
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
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		mEdtUname = (EditText) this.findViewById(R.id.et_uname);
		mEdtUpwd = (EditText) this.findViewById(R.id.et_upwd);

		mCbShowPwd = (CheckBox) this.findViewById(R.id.cb_show_pwd);
		mBtnLogin = (Button) this.findViewById(R.id.btn_login);
		mBtnRegister = (Button) this.findViewById(R.id.btn_register);

		// ���¼�
		mBtnLogin.setOnClickListener(listener);
		mBtnRegister.setOnClickListener(listener);
		mCbShowPwd.setOnCheckedChangeListener(cb_listener);
	}

	/**
	 * ��ʾ����
	 */
	OnCheckedChangeListener cb_listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			if (mCbShowPwd.isChecked()) {
				// ��ʾ����
				mEdtUpwd.setTransformationMethod(HideReturnsTransformationMethod
						.getInstance());
			} else {
				// ����ʾ
				mEdtUpwd.setTransformationMethod(PasswordTransformationMethod
						.getInstance());
			}
		}
	};
	/**
	 * ��ť�����¼�
	 */
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_login:
				// ��ȡ�û������ֵ
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
							Toast.makeText(Login.this, "��¼�ɹ���",Toast.LENGTH_SHORT).show();
							Login.this.finish();
						} else {
							Toast.makeText(Login.this, "�˺Ż�����������������룡",Toast.LENGTH_SHORT)
									.show();
						}
					} else {
						Toast.makeText(Login.this, "���������룡", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(Login.this, "�������˺ţ�", Toast.LENGTH_SHORT).show();
				}

				break;
			case R.id.btn_register:
				// ע��
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
