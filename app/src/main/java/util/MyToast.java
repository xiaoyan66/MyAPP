package util;


import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import ui.R;

public class MyToast
{

	public static void showToast(Context context, String str)
	{
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}

	public static void showAlert(Context context, String title, String str)
	{
		new AlertDialog.Builder(context)
				.setIcon(R.drawable.login_error_icon).setTitle(title)
				.setMessage(str).create().show();
	}

}
