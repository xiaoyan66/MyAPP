package ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import util.ActivityCollector;
import util.MyToast;


public class RegPswSet extends Activity {

	private Button btnpsw;
	private Button myback;
	private EditText pw1, pw2;

	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_reg_psw_set);
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
	// ��ʼ���ؼ�
	private void initView() {
		btnpsw = (Button) this.findViewById(R.id.regpsw);
		myback = (Button) this.findViewById(R.id.myback);
		pw1 = (EditText) this.findViewById(R.id.pw1);
		pw2 = (EditText) this.findViewById(R.id.pw2);
		// ���¼�
		btnpsw.setOnClickListener(listener);
		myback.setOnClickListener(listener);

	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.regpsw) {
				String p1 = pw1.getText().toString();
				String p2 = pw2.getText().toString();
				if (p1.length() == 0 || p2.length() == 0) {
					MyToast.showAlert(RegPswSet.this, "������ʾ", "�Բ����������ò���Ϊ�գ�");
				} else if (!p1.equalsIgnoreCase(p2)) {
					MyToast.showAlert(RegPswSet.this, "������ʾ", "�Բ����������벻һ�£�");
				} else {
					preferences = getSharedPreferences("Reg",
							Context.MODE_PRIVATE);
					Editor editor = preferences.edit();
					editor.putString("pwd", p1);
					editor.commit();

					startActivity(new Intent(RegPswSet.this, RegLast.class));
					overridePendingTransition(R.anim.left, R.anim.right);
					RegPswSet.this.finish();
				}

			}
			if (v.getId() == R.id.myback) {
				startActivity(new Intent(RegPswSet.this, RegNext.class));
				overridePendingTransition(R.anim.backleft, R.anim.backright);
				RegPswSet.this.finish();
			}

		}
	};

}
