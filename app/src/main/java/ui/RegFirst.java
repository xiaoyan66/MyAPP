package ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import entity.UserInfo;
import util.ActivityCollector;
import util.MyToast;


/**
 * �û�ע��1
 * @author Administrator
 *
 */
public class RegFirst extends Activity {
	private Button back;// 返回按钮
	private Button regbtn;// 提交按钮
	private CheckBox chk_agree;// 检查是否阅读了条款
	private EditText address;// 所在地
	private TextView myupdateaddress;// 修改电话所在地
	private EditText regtel;// 注册的手机号

	private boolean flag = false;

	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_reg_first);
		ActivityCollector.addActivity(this);
		// 初始化控件
		initView();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

	private void initView() {
		back = (Button) this.findViewById(R.id.back);
		regbtn = (Button) this.findViewById(R.id.regbtn);
		chk_agree = (CheckBox) this.findViewById(R.id.chk_agree);
		address = (EditText) this.findViewById(R.id.regaddress);
		myupdateaddress = (TextView) this.findViewById(R.id.myupdateaddress);
		regtel = (EditText) this.findViewById(R.id.regtel);
		// 在按钮上加事件
		back.setOnClickListener(listener);
		regbtn.setOnClickListener(listener);
		// 在修改地域上加事件
		myupdateaddress.setOnClickListener(listener);
		// 当你输入完成手机号码后，加事件
		// regtel.addTextChangedListener(mychange);

	}

	// 在按钮上加事件
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.back) {
				// 返回
				startActivity(new Intent(RegFirst.this, Login.class));
				overridePendingTransition(R.anim.backleft, R.anim.backright);
				RegFirst.this.finish();
			}
			if (v.getId() == R.id.regbtn) {
				String telname = regtel.getText().toString();
				if (telname.length() == 0) {
					MyToast.showAlert(RegFirst.this, "错误提示", "账号不能为空！");
				} else if (!chk_agree.isChecked()) {
					MyToast.showAlert(RegFirst.this, "错误提示", "小子，不同意条款是不行的！");
				} else {

					UserInfo userInfo = new UserInfo();
					userInfo.setUname(telname);
					// 放到缓存中去
					preferences = getSharedPreferences("Reg",
							Context.MODE_PRIVATE);

					Editor editor = preferences.edit();
					editor.putString("telname", telname);
					editor.commit();

					// 下一个注册页面
					startActivity(new Intent(RegFirst.this, RegNext.class));
					overridePendingTransition(R.anim.left, R.anim.right);
					RegFirst.this.finish();
				}
			}
			if (v.getId() == R.id.myupdateaddress) {
				// 修改地域
				updateMyAddress();
			}
		}
	};

	/******************************* 修改地域事件 **************************/
	private void updateMyAddress() {
		showDialog(1);
	}

	protected Dialog onCreateDialog(int id, Bundle args) {
		final Builder b = new AlertDialog.Builder(RegFirst.this);
		b.setTitle("请选择所在地");
		// 第一个参数：选择内容
		// 第二个参数：默认的选择项
		// 第三个参数：选择后的事件
		b.setSingleChoiceItems(new String[] { "+86中国大陆", "+853中国澳门",
						"+852中国香港", "+886中国台湾" }, 0,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						switch (arg1) {
							case 0:
								address.setText("+86中国大陆");
								break;
							case 1:
								address.setText("+853中国澳门");
								break;
							case 2:
								address.setText("+852中国香港");
								break;
							case 3:
								address.setText("+886中国台湾");
								break;
						}
						dismissDialog(1);
					}
				});
		return b.create();
	}
}
