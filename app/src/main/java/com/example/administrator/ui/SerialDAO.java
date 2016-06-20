package com.example.administrator.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import util.DBConnection;


public class SerialDAO {
	private DBConnection dbc;
	private SQLiteDatabase db;
	private Context context;

	public SerialDAO(Context context) {
		this.context = context;
	}

	public SerialDAO open() {
		dbc = new DBConnection(context);
		db = dbc.getWritableDatabase();
		return this;
	}

	public void closeAll() {
		db.close();
		dbc.close();
	}

	/**
	 * ����Ʒ�Ʊ�Ų�ѯ���г�ϵ
	 * 
	 * @return
	 */
	public List getAllSerial(int bid) {
		List ar = new ArrayList();
		open();
		try {
			Cursor c = db.rawQuery("select * from serial where bid=?",
					new String[] { String.valueOf(bid) });
			while (c.moveToNext()) {
				Map map = new HashMap();
				map.put("sid", c.getInt(c.getColumnIndex("_id")));
				map.put("sname", c.getString(c.getColumnIndex("sname")));
				map.put("simage", c.getInt(c.getColumnIndex("simage")));
				map.put("bid", c.getInt(c.getColumnIndex("bid")));
				ar.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return ar;
	}

	/**
	 * ���ݳ�ϵ��ѯ���г���
	 * 
	 * @param sid
	 * @return
	 */
	public List getAllCarBySid(int sid) {
		List ar = new ArrayList();
		open();
		try {
			Cursor c = db.rawQuery("select * from car where sid=?",
					new String[] { String.valueOf(sid) });
			while (c.moveToNext()) {
				Map map = new HashMap();
				map.put("cid", c.getInt(c.getColumnIndex("_id")));
				map.put("sid", c.getInt(c.getColumnIndex("sid")));
				map.put("cname", c.getString(c.getColumnIndex("cname")));
				map.put("cimage", c.getInt(c.getColumnIndex("cimage")));
				map.put("crank", c.getString(c.getColumnIndex("crank")));
				map.put("cengine", c.getString(c.getColumnIndex("cengine")));
				map.put("cgearbox", c.getString(c.getColumnIndex("cgearbox")));
				map.put("cstructure",
						c.getString(c.getColumnIndex("cstructure")));
				map.put("coldprice", c.getFloat(c.getColumnIndex("coldprice")));
				map.put("cnewprice", c.getFloat(c.getColumnIndex("cnewprice")));

				ar.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return ar;
	}
}
