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
import ui.R;
import ui.ShowCollect;
import ui.UserInfoManage;
import util.Tools;
import view.RoundImageView;


/**
 * 个人信息界面
 * 
 * @author YinWenBing Create at 2016-3-13 ����14:23
 */
public class FiveFragment extends Fragment {
	private View view;
	// 组件
	private RoundImageView mIvUimage = null;// 头像图片
	private TextView mTvLogin;// 立即登录

	// 弹窗item
	private String[] items = new String[] { "从相册中选择", "拍照" };

	private File tempFile = new File(Environment.getExternalStorageDirectory(),
			getPhotoFileName());

	// 请求码
	private static final int IMAGE_REQUEST_CODE = 0;// 打开相册请求码
	private static final int CAMERA_REQUEST_CODE = 1;// 拍照请求码
	private static final int RESULT_REQUEST_CODE = 2;// 结果请求码

	// 用户缓存
	private SharedPreferences preferences;

	// 我的订单、我的收藏、账号管理
	private RelativeLayout mRlAppoint;
	private RelativeLayout mRlCollects;
	private RelativeLayout mRlInfo;
	private boolean login;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.five_fragment, null);
		// 初始化控件
		initView();
		// 登录成功之后，获得用户头像
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
	 * 初始化控件方法
	 */
	private void initView() {
		mIvUimage = (RoundImageView) view.findViewById(R.id.iv_uimage);// 头像
		mTvLogin = (TextView) view.findViewById(R.id.tv_login);// 立即登录
		// 我的订单、我的收藏、账号管理
		mRlAppoint = (RelativeLayout) view.findViewById(R.id.rl_appoint);
		mRlCollects = (RelativeLayout) view.findViewById(R.id.rl_collects);
		mRlInfo = (RelativeLayout) view.findViewById(R.id.rl_info);

		// 监听事件
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
					// 调用弹出选项窗口
					if (login == true) {
						showDialog();
					} else {
						Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
					}

					break;
				case R.id.tv_login:
					// 跳转至登录页面
					startActivity(new Intent(getActivity(), Login.class));
					getActivity().finish();
					break;

				case R.id.rl_appoint:
					// 跳转至已完成订单页面
					if (login == true) {
						startActivity(new Intent(getActivity(), MyIndent.class));
					} else {
						Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
					}
					break;
				case R.id.rl_collects:
					// 跳转至我的收藏页面
					if (login == true) {
						startActivity(new Intent(getActivity(), ShowCollect.class));
					} else {
						Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
					}

					break;
				case R.id.rl_info:
					// 跳转至账号管理界面
					if (login == true) {
						startActivity(new Intent(getActivity(),
								UserInfoManage.class));
					} else {
						Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
					}

					break;
				default:
					break;
			}

		}
	};

	/**
	 * 弹出选项窗口方法
	 */
	private void showDialog() {
		new AlertDialog.Builder(getActivity())
				.setTitle("设置头像")
				.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int which) {
						switch (which) {
							case 0:
								// 从相册中选择
								Intent intentFromImage = new Intent();
								intentFromImage.setType("image/*");
								intentFromImage
										.setAction(Intent.ACTION_GET_CONTENT);
								startActivityForResult(intentFromImage,
										IMAGE_REQUEST_CODE);
								break;
							case 1:
								// 拍照
								Intent intentFromCamera = new Intent(
										MediaStore.ACTION_IMAGE_CAPTURE);
								if (Tools.hasSdcard()) {
									// 指定调用相机拍照后照片的储存路径
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
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// 隐藏对话框,释放对话框所占的资源
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
	 * 使用系统当前日期加以调整作为照片的名称
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
	 * 调用系统裁剪功能：
	 *
	 * @param fromFile
	 */
	private void startPhotoZoom(Uri fromFile) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(fromFile, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");

		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 300);
		intent.putExtra("return-data", true);
		intent.putExtra("noFaceDetection", true);
		startActivityForResult(intent, RESULT_REQUEST_CODE);
	}

	/**
	 * 保存裁剪后的图片
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
