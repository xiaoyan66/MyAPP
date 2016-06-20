package com.example.administrator.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import util.DBConnection;


public class BrandDAO {
	private DBConnection dbc;
	private SQLiteDatabase db;
	private Context context;

	public BrandDAO(Context context) {
		this.context = context;
	}

	public BrandDAO open() {
		dbc = new DBConnection(context);
		db = dbc.getWritableDatabase();
		return this;
	}

	public void closeAll() {
		db.close();
		dbc.close();
	}

	/**
	 * ��ѯ��������Ʒ��
	 * 
	 * @return
	 */
	public List getAllBrand() {
		List ar = new ArrayList();
		open();
		try {
			Cursor c = db.rawQuery("select * from brand", null);
			while (c.moveToNext()) {
				Map map = new HashMap();
				map.put("bid", c.getInt(c.getColumnIndex("_id")));
				map.put("bname", c.getString(c.getColumnIndex("bname")));
				map.put("breferral", c.getString(c.getColumnIndex("breferral")));
				map.put("bimage", c.getInt(c.getColumnIndex("bimage")));

				ar.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return ar;
	}

	/**
	 * ��ѯ�˸�����Ʒ��
	 */
	public List getEightBrand() {
		List ar = new ArrayList();
		open();
		try {
			Cursor c = db.rawQuery("select * from brand limit 0,8", null);
			while (c.moveToNext()) {
				Map map = new HashMap();
				map.put("bid", c.getInt(c.getColumnIndex("_id")));
				map.put("bname", c.getString(c.getColumnIndex("bname")));
				map.put("breferral", c.getString(c.getColumnIndex("breferral")));
				map.put("bimage", c.getInt(c.getColumnIndex("bimage")));

				ar.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return ar;
	}

	/**
	 * ����Ʒ�Ʊ�Ų�ѯ���г�ϵ
	 * 
	 * @param bid
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
	 * ��������ģ����ѯ����Ʒ��
	 * 
	 * @return
	 */
	public List getAllBrandByName(String name) {
		List ar = new ArrayList();
		open();
		try {
			Cursor c = db.rawQuery("select * from brand where bname like ?",
					new String[] { "%" + name + "%" });
			while (c.moveToNext()) {
				Map map = new HashMap();
				map.put("bid", c.getInt(c.getColumnIndex("_id")));
				map.put("bname", c.getString(c.getColumnIndex("bname")));
				map.put("breferral", c.getString(c.getColumnIndex("breferral")));
				map.put("bimage", c.getInt(c.getColumnIndex("bimage")));

				ar.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return ar;
	}
}
