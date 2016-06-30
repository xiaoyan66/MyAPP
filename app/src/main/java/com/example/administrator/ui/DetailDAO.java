package com.example.administrator.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
;import entity.Detail;
import util.DBConnection;

public class DetailDAO {
	private DBConnection dbc;
	private SQLiteDatabase db;
	private Context context;

	public DetailDAO(Context context) {
		this.context = context;
	}

	public DetailDAO open() {
		dbc = new DBConnection(context);
		db = dbc.getWritableDatabase();
		return this;
	}

	public void closeAll() {
		db.close();
		dbc.close();
	}

	/**
	 * 生成订单详情
	 * 
	 * @param detail
	 */
	public void addDetail(Detail detail) {
		open();
		try {
			ContentValues values = new ContentValues();
			values.put("inumber", detail.getInumber());
			values.put("dallprice", detail.getDallprice());
			values.put("dtime", detail.getDtime());
			values.put("dstate", detail.getDstate());
			values.put("uid", detail.getUid());

			db.insert("detail", null, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
	}

	/**
	 * 查询所有已完成订单
	 */
	public List getAllDetails() {
		List ar = new ArrayList();
		open();
		try {

			Cursor c = db.rawQuery("select * from detail", null);
			while (c.moveToNext()) {
				Map map = new HashMap();
				map.put("id", c.getInt(c.getColumnIndex("_id")));
				map.put("inumber", c.getString(c.getColumnIndex("inumber")));
				map.put("dallprice", c.getFloat(c.getColumnIndex("dallprice")));
				map.put("dtime", c.getString(c.getColumnIndex("dtime")));
				map.put("dstate", c.getInt(c.getColumnIndex("dstate")));
				map.put("uid", c.getInt(c.getColumnIndex("uid")));

				ar.add(map);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return ar;
	}

	/**
	 * 查询所有已完成订单
	 */
	public List getAllDetailsByDstate(int dstate) {
		List ar = new ArrayList();
		open();
		try {

			Cursor c = db.rawQuery("select * from detail where dstate=?",
					new String[] { String.valueOf(dstate) });
			while (c.moveToNext()) {
				Map map = new HashMap();
				map.put("id", c.getInt(c.getColumnIndex("_id")));
				map.put("inumber", c.getString(c.getColumnIndex("inumber")));
				map.put("dallprice", c.getFloat(c.getColumnIndex("dallprice")));
				map.put("dtime", c.getString(c.getColumnIndex("dtime")));
				map.put("dstate", c.getInt(c.getColumnIndex("dstate")));
				map.put("uid", c.getInt(c.getColumnIndex("uid")));

				ar.add(map);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return ar;
	}
}
