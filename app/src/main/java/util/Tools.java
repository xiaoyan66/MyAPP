package util;

import android.os.Environment;

public class Tools {
	/**
	 * ����Ƿ����SD��
	 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}
