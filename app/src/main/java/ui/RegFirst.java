package ui;

import android.app.Activity;
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
	private Button back;// ���ذ�ť
	private Button regbtn;// �ύ��ť
	private CheckBox chk_agree;// ����Ƿ��Ķ�������
	private EditText address;// ���ڵ�
	private TextView myupdateaddress;// �޸ĵ绰���ڵ�
	private EditText regtel;// ע����ֻ���

	private boolean flag = false;

	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_reg_first);
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

	private void initView() {
		back = (Button) this.findViewById(R.id.back);
		regbtn = (Button) this.findViewById(R.id.regbtn);
		chk_agree = (CheckBox) this.findViewById(R.id.chk_agree);
		address = (EditText) this.findViewById(R.id.regaddress);
		myupdateaddress = (TextView) this.findViewById(R.id.myupdateaddress);
		regtel = (EditText) this.findViewById(R.id.regtel);
		// �ڰ�ť�ϼ��¼�
		back.setOnClickListener(listener);
		regbtn.setOnClickListener(listener);
		// ���޸ĵ����ϼ��¼�
		myupdateaddress.setOnClickListener(listener);
		// ������������ֻ�����󣬼��¼�
		// regtel.addTextChangedListener(mychange);

	}

	// �ڰ�ť�ϼ��¼�
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.back) {
				// ����
				startActivity(new Intent(RegFirst.this, Login.class));
				overridePendingTransition(R.anim.backleft, R.anim.backright);
				RegFirst.this.finish();
			}
			if (v.getId() == R.id.regbtn) {
				String telname = regtel.getText().toString();
				if (telname.length() == 0) {
					MyToast.showAlert(RegFirst.this, "������ʾ", "�˺Ų���Ϊ�գ�");
				} else if (!chk_agree.isChecked()) {
					MyToast.showAlert(RegFirst.this, "������ʾ", "С�ӣ���ͬ�������ǲ��еģ�");
				} else {

					UserInfo userInfo = new UserInfo();
					userInfo.setUname(telname);
					// �ŵ�������ȥ
					preferences = getSharedPreferences("Reg",
							Context.MODE_PRIVATE);

					Editor editor = preferences.edit();
					editor.putString("telname", telname);
					editor.commit();

					// ��һ��ע��ҳ��
					startActivity(new Intent(RegFirst.this, RegNext.class));
					overridePendingTransition(R.anim.left, R.anim.right);
					RegFirst.this.finish();
				}
			}
			if (v.getId() == R.id.myupdateaddress) {
				// �޸ĵ���
				updateMyAddress();
			}
		}
	};

	/******************************* �޸ĵ����¼� **************************/
	private void updateMyAddress() {
		showDialog(1);
	}

	protected Dialog onCreateDialog(int id, Bundle args) {
		final Builder b = new Builder(RegFirst.this);
		b.setTitle("��ѡ�����ڵ�");
		// ��һ��������ѡ������
		// �ڶ���������Ĭ�ϵ�ѡ����
		// ������������ѡ�����¼�
		b.setSingleChoiceItems(new String[] { "+86�й���½", "+853�й�����",
				"+852�й����", "+886�й�̨��" }, 0,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						switch (arg1) {
						case 0:
							address.setText("+86�й���½");
							break;
						case 1:
							address.setText("+853�й�����");
							break;
						case 2:
							address.setText("+852�й����");
							break;
						case 3:
							address.setText("+886�й�̨��");
							break;
						}
						dismissDialog(1);
					}
				});
		return b.create();
	}
}
