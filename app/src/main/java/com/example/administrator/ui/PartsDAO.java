package com.example.administrator.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import entity.Parts;
import util.DBConnection;


public class PartsDAO {
	private DBConnection dbc;
	private SQLiteDatabase db;
	private Context context;

	public PartsDAO(Context context) {
		this.context = context;
	}

	public PartsDAO open() {
		dbc = new DBConnection(context);
		db = dbc.getWritableDatabase();
		return this;
	}

	public void closeAll() {
		db.close();
		dbc.close();
	}

	/**
	 * ��ѯʹ��������Ʒ
	 */
	public List getAllParts() {
		List ar = new ArrayList();
		open();
		try {
			Cursor c = db.rawQuery("select * from parts", null);
			while (c.moveToNext()) {
				Map map = new HashMap();
				map.put("pid", c.getInt(c.getColumnIndex("_id")));
				map.put("pname", c.getString(c.getColumnIndex("pname")));
				map.put("pimage", c.getInt(c.getColumnIndex("pimage")));
				map.put("pinventory", c.getInt(c.getColumnIndex("pinventory")));
				map.put("poldprice", c.getFloat(c.getColumnIndex("poldprice")));
				map.put("pnewprice", c.getFloat(c.getColumnIndex("pnewprice")));
				map.put("pbrand", c.getString(c.getColumnIndex("pbrand")));
				map.put("preferral", c.getString(c.getColumnIndex("preferral")));

				ar.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return ar;
	}

	/**
	 * ��ѯ�ĸ�������Ʒ
	 */
	public List getFourParts() {
		List ar = new ArrayList();
		open();
		try {
			Cursor c = db.rawQuery("select * from parts limit 0,4", null);
			while (c.moveToNext()) {
				Map map = new HashMap();
				map.put("pid", c.getInt(c.getColumnIndex("_id")));
				map.put("pname", c.getString(c.getColumnIndex("pname")));
				map.put("pimage", c.getInt(c.getColumnIndex("pimage")));
				map.put("pinventory", c.getInt(c.getColumnIndex("pinventory")));
				map.put("poldprice", c.getFloat(c.getColumnIndex("poldprice")));
				map.put("pnewprice", c.getFloat(c.getColumnIndex("pnewprice")));
				map.put("pbrand", c.getString(c.getColumnIndex("pbrand")));
				map.put("preferral", c.getString(c.getColumnIndex("preferral")));

				ar.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return ar;
	}

	/**
	 * ���ݱ�Ų�ѯ��Ʒ
	 * 
	 * @param sid
	 * @return
	 */
	public Parts getAllPartsById(int id) {
		Parts parts = new Parts();
		open();
		try {
			Cursor c = db.rawQuery("select * from parts where _id=?",
					new String[] { String.valueOf(id) });
			while (c.moveToNext()) {
				parts.set_id(c.getInt(c.getColumnIndex("_id")));
				parts.setPname(c.getString(c.getColumnIndex("pname")));
				parts.setPimage(c.getInt(c.getColumnIndex("pimage")));
				parts.setPinventory(c.getInt(c.getColumnIndex("pinventory")));
				parts.setPoldprice(c.getFloat(c.getColumnIndex("poldprice")));
				parts.setPnewprice(c.getFloat(c.getColumnIndex("pnewprice")));
				parts.setPbrand(c.getString(c.getColumnIndex("pbrand")));
				parts.setPreferral(c.getString(c.getColumnIndex("preferral")));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return parts;
	}
}
