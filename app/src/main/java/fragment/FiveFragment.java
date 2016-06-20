package fragment;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ui.Login;
import ui.MyIndent;
import ui.ShowCollect;
import ui.UserInfoManage;
import util.Tools;
import view.RoundImageView;


/**
 * ������Ϣ����
 * 
 * @author YinWenBing Create at 2016-3-13 ����14:23
 */
public class FiveFragment extends Fragment {
	private View view;
	// ���
	private RoundImageView mIvUimage = null;// ͷ��ͼƬ
	private TextView mTvLogin;// ������¼

	// ����item
	private String[] items = new String[] { "�������ѡ��", "����" };

	private File tempFile = new File(Environment.getExternalStorageDirectory(),
			getPhotoFileName());

	// ������
	private static final int IMAGE_REQUEST_CODE = 0;// �����������
	private static final int CAMERA_REQUEST_CODE = 1;// ����������
	private static final int RESULT_REQUEST_CODE = 2;// ���������

	// �û�����
	private SharedPreferences preferences;

	// �ҵĶ������ҵ��ղء��˺Ź���
	private RelativeLayout mRlAppoint;
	private RelativeLayout mRlCollects;
	private RelativeLayout mRlInfo;
	private boolean login;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.five_fragment, null);
		// ��ʼ���ؼ�
		initView();
		// ��¼�ɹ�֮�󣬻���û�ͷ��
		preferences = getActivity().getSharedPreferences("Login",
				Context.MODE_PRIVATE);
		int uimage = preferences.getInt("uimage", 0);
		login = preferences.getBoolean("login", false);
		if (login == true) {
			mTvLogin.setVisibility(view.GONE);
		}
		mIvUimage.setImageResource(uimage);
		return view;
	}

	/**
	 * ��ʼ���ؼ�����
	 */
	private void initView() {
		mIvUimage = (RoundImageView) view.findViewById(R.id.iv_uimage);// ͷ��
		mTvLogin = (TextView) view.findViewById(R.id.tv_login);// ������¼
		// �ҵĶ������ҵ��ղء��˺Ź���
		mRlAppoint = (RelativeLayout) view.findViewById(R.id.rl_appoint);
		mRlCollects = (RelativeLayout) view.findViewById(R.id.rl_collects);
		mRlInfo = (RelativeLayout) view.findViewById(R.id.rl_info);

		// �����¼�
		mIvUimage.setOnClickListener(iv_listener);
		mTvLogin.setOnClickListener(iv_listener);

		mRlAppoint.setOnClickListener(iv_listener);
		mRlCollects.setOnClickListener(iv_listener);
		mRlInfo.setOnClickListener(iv_listener);
	}

	OnClickListener iv_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_uimage:
				// ���õ���ѡ���
				if (login == true) {
					showDialog();
				} else {
					Toast.makeText(getActivity(), "���ȵ�¼",Toast.LENGTH_SHORT).show();
				}

				break;
			case R.id.tv_login:
				// ��ת����¼ҳ��
				startActivity(new Intent(getActivity(), Login.class));
				getActivity().finish();
				break;

			case R.id.rl_appoint:
				// ��ת������ɶ���ҳ��
				if (login == true) {
					startActivity(new Intent(getActivity(), MyIndent.class));
				} else {
					Toast.makeText(getActivity(), "���ȵ�¼",Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.rl_collects:
				// ��ת���ҵ��ղ�ҳ��
				if (login == true) {
					startActivity(new Intent(getActivity(), ShowCollect.class));
				} else {
					Toast.makeText(getActivity(), "���ȵ�¼",Toast.LENGTH_SHORT).show();
				}

				break;
			case R.id.rl_info:
				// ��ת���˺Ź������
				if (login == true) {
					startActivity(new Intent(getActivity(),
							UserInfoManage.class));
				} else {
					Toast.makeText(getActivity(), "���ȵ�¼", Toast.LENGTH_SHORT).show();
				}

				break;
			default:
				break;
			}

		}
	};

	/**
	 * ����ѡ��ڷ���
	 */
	private void showDialog() {
		new AlertDialog.Builder(getActivity())
				.setTitle("����ͷ��")
				.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int which) {
						switch (which) {
						case 0:
							// �������ѡ��
							Intent intentFromImage = new Intent();
							intentFromImage.setType("image/*");
							intentFromImage
									.setAction(Intent.ACTION_GET_CONTENT);
							startActivityForResult(intentFromImage,
									IMAGE_REQUEST_CODE);
							break;
						case 1:
							// ����
							Intent intentFromCamera = new Intent(
									MediaStore.ACTION_IMAGE_CAPTURE);
							if (Tools.hasSdcard()) {
								// ָ������������պ���Ƭ�Ĵ���·��
								intentFromCamera.putExtra(
										MediaStore.EXTRA_OUTPUT,
										Uri.fromFile(tempFile));

							}
							startActivityForResult(intentFromCamera,
									CAMERA_REQUEST_CODE);
							break;
						default:
							break;
						}
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// ���ضԻ���,�ͷŶԻ�����ռ����Դ
						arg0.dismiss();
					}
				}).show();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case IMAGE_REQUEST_CODE:
			startPhotoZoom(data.getData());
			break;
		case CAMERA_REQUEST_CODE:
			startPhotoZoom(Uri.fromFile(tempFile));
			break;
		case RESULT_REQUEST_CODE:
			if (data != null) {
				sentPicToNext(data);
			}
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * ʹ��ϵͳ��ǰ���ڼ��Ե�����Ϊ��Ƭ������
	 * 
	 * @return
	 */
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'img'_yyyyMMdd_HHmmss");

		return dateFormat.format(date) + ".jpg";
	}

	/**
	 * ����ϵͳ�ü����ܣ�
	 * 
	 * @param fromFile
	 */
	private void startPhotoZoom(Uri fromFile) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(fromFile, "image/*");
		// cropΪtrue�������ڿ�����intent��������ʾ��view���Լ���
		intent.putExtra("crop", "true");

		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		// outputX,outputY �Ǽ���ͼƬ�Ŀ��
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 300);
		intent.putExtra("return-data", true);
		intent.putExtra("noFaceDetection", true);
		startActivityForResult(intent, RESULT_REQUEST_CODE);
	}

	/**
	 * ����ü����ͼƬ
	 * 
	 * @param data
	 */
	private void sentPicToNext(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			mIvUimage.setImageBitmap(photo);
		}
	}
}
