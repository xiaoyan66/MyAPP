package ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Window;

import util.ActivityCollector;


/**
 * �ж��û��Ƿ��һ��ʹ��
 * 
 * @author Administrator
 * 
 */
public class IsFirstIn extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ActivityCollector.addActivity(this);
		// �ж��û��Ƿ��һ��ʹ��
		IsFirst();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
	
	// �ж��û��Ƿ��һ��ʹ��
	private void IsFirst()
	{
		/**
		 * SharedPreferences: 1��SharedPreferences��Android���������������ݴ洢����
		 * 2��ʵ����SharedPreferences����ľ���һ��key-value����ֵ�ԣ�
		 * 3��SharedPreferences�������洢һЩ������������
		 */

		// ʵ����SharedPreferences���󣬲���1ָ���ļ���������2ָ���ļ��Ĳ���ģʽ
		SharedPreferences preferences = getSharedPreferences("usedcars",
				Context.MODE_PRIVATE);
		// ������������first����ʼֵΪtrue
		boolean first = preferences.getBoolean("first", true);
		// ���firstΪtrue�������һ��ʹ��
		if (first == true)
		{
			// �����༭��Editor
			Editor editor = preferences.edit();
			// �޸�firstֵΪfalse
			editor.putBoolean("first", false);
			// �޸ĺ��ύ
			editor.commit();
			// ��ת������ҳ
			startActivity(new Intent(IsFirstIn.this, GuidePage.class));
			// �رո�ҳ��
			IsFirstIn.this.finish();
		}
		else
		{
			// ���ǵ�һ��ʹ�ã���ת����ӭҳ
			startActivity(new Intent(IsFirstIn.this, Welcome.class));
			// �رո�ҳ��
			IsFirstIn.this.finish();
		}

	}

}
