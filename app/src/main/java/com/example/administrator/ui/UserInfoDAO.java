package com.example.administrator.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import entity.UserInfo;
import util.DBConnection;


public class UserInfoDAO {
	private DBConnection dbc;
	private SQLiteDatabase db;
	private Context context;

	public UserInfoDAO(Context context) {
		this.context = context;
	}

	// �����ݿ�
	public UserInfoDAO open() {
		dbc = new DBConnection(context);
		db = dbc.getWritableDatabase();
		return this;
	}

	// �ر����ݿ�
	public void closeAll() {
		db.close();
		dbc.close();
	}

	/**
	 * �����˺������ѯ�û�
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 */
	public UserInfo getUserInfoByNameAndPwd(String name, String pwd) {
		UserInfo userInfo = null;
		open();
		try {
			Cursor c = db.rawQuery(
					"select * from userinfo where uname=? and upwd=?",
					new String[] { name, pwd });
			while (c.moveToNext()) {
				int _id = c.getInt(c.getColumnIndex("_id"));
				String uname = c.getString(c.getColumnIndex("uname"));
				String upwd = c.getString(c.getColumnIndex("upwd"));
				int uimage = c.getInt(c.getColumnIndex("uimage"));

				userInfo = new UserInfo(_id, uname, upwd, uimage);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return userInfo;
	}

	/**
	 * ע�� �������
	 * 
	 * @param userInfo
	 */
	public void AddUserInfo(UserInfo userInfo) {
		open();
		try {
			// db.execSQL("insert into userinfo values(null,?,?,?)",
			// new String[] { userInfo.getUname(), userInfo.getUpwd(),
			// String.valueOf(userInfo.getUimage()) });
			ContentValues values = new ContentValues();

			values.put("uname", userInfo.getUname());

			values.put("upwd", userInfo.getUpwd());

			values.put("uimage", userInfo.getUimage());

			db.insert("userinfo", null, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
	}

	/**
	 * �޸�����
	 */
	public void updateUserInfoPwd(UserInfo userInfo) {
		open();
		try {
			ContentValues values = new ContentValues();
			values.put("uname", userInfo.getUname());
			values.put("upwd", userInfo.getUpwd());
			values.put("uimage", userInfo.getUimage());

			db.update("userinfo", values, "uname=?",
					new String[] { userInfo.getUname() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
	}
}
