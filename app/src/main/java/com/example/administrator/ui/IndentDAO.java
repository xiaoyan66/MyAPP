package com.example.administrator.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import entity.Indent;
import util.DBConnection;


public class IndentDAO {
	private DBConnection dbc;
	private SQLiteDatabase db;
	private Context context;

	public IndentDAO(Context context) {
		this.context = context;
	}

	public IndentDAO open() {
		dbc = new DBConnection(context);
		db = dbc.getWritableDatabase();
		return this;
	}

	public void closeAll() {
		db.close();
		dbc.close();
	}

	/**
	 * ��ѯ����
	 */
	public List getAllIndent(String inumber) {
		List ar = new ArrayList();
		open();
		try {
			Cursor c = db.rawQuery("select * from indent where inumber=?",
					new String[] { inumber });
			while (c.moveToNext()) {
				// ����š�ͼƬ�����ơ����ۡ������������š��û����ܼۣ�
				Map map = new HashMap();
				map.put("_id", c.getInt(c.getColumnIndex("_id")));
				map.put("iimage", c.getInt(c.getColumnIndex("iimage")));
				map.put("iname", c.getString(c.getColumnIndex("iname")));
				map.put("iprice", c.getFloat(c.getColumnIndex("iprice")));
				map.put("icount", c.getInt(c.getColumnIndex("icount")));
				map.put("inumber", c.getString(c.getColumnIndex("inumber")));
				map.put("uid", c.getInt(c.getColumnIndex("uid")));
				map.put("dstate", c.getInt(c.getColumnIndex("dstate")));

				ar.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		closeAll();
		return ar;
	}

	/**
	 * ���ݱ�Ų�ѯ����
	 */
	public Indent getIndentById(int id) {
		Indent indent = null;
		open();
		try {
			Cursor c = db.rawQuery("select * from indent where _id=?",
					new String[] { String.valueOf(id) });
			while (c.moveToNext()) {
				// ����š�ͼƬ�����ơ����ۡ������������š��û����ܼۣ�
				int _id = c.getInt(c.getColumnIndex("_id"));
				int iimage = c.getInt(c.getColumnIndex("iimage"));
				String iname = c.getString(c.getColumnIndex("iname"));
				float iprice = c.getFloat(c.getColumnIndex("iprice"));
				int icount = c.getInt(c.getColumnIndex("icount"));
				String inumber = c.getString(c.getColumnIndex("inumber"));
				int uid = c.getInt(c.getColumnIndex("uid"));
				int dstate = c.getInt(c.getColumnIndex("dstate"));

				indent = new Indent(_id, iimage, iname, iprice, icount, uid,
						inumber, dstate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		closeAll();
		return indent;
	}

	/**
	 * ���ɶ���
	 * 
	 * @param indent
	 */
	public void addIndent(Indent indent) {
		open();
		try {
			ContentValues values = new ContentValues();
			values.put("iimage", indent.getIimage());
			values.put("iname", indent.getIname());
			values.put("iprice", indent.getIprice());
			values.put("icount", indent.getIcount());
			values.put("uid", indent.getUid());
			values.put("inumber", indent.getInumber());
			values.put("dstate", indent.getDstate());

			db.insert("indent", null, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
	}

	/**
	 * ɾ������
	 */
	public void delAll() {
		open();
		try {
			db.delete("indent", null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
	}

	/**
	 * �޸�
	 * 
	 * @param shoppingCart
	 */
	public void updateIndent(Indent indent) {
		open();
		try {
			ContentValues values = new ContentValues();
			values.put("iimage", indent.getIimage());
			values.put("iname", indent.getIname());
			values.put("iprice", indent.getIprice());
			values.put("icount", indent.getIcount());
			values.put("uid", indent.getUid());
			values.put("inumber", indent.getInumber());
			values.put("dstate", indent.getDstate());

			db.update("indent", values, "_id=?",
					new String[] { String.valueOf(indent.get_id()) });
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
	}

	/**
	 * ��ѯ����δ����Ķ���
	 */
	public List getAllIndentByDstate(int dstate) {
		List ar = new ArrayList();
		open();
		try {
			Cursor c = db.rawQuery("select * from indent where dstate=?",
					new String[] { String.valueOf(dstate) });
			while (c.moveToNext()) {
				// ����š�ͼƬ�����ơ����ۡ������������š��û����ܼۣ�
				Map map = new HashMap();
				map.put("_id", c.getInt(c.getColumnIndex("_id")));
				map.put("iimage", c.getInt(c.getColumnIndex("iimage")));
				map.put("iname", c.getString(c.getColumnIndex("iname")));
				map.put("iprice", c.getFloat(c.getColumnIndex("iprice")));
				map.put("icount", c.getInt(c.getColumnIndex("icount")));
				map.put("inumber", c.getString(c.getColumnIndex("inumber")));
				map.put("uid", c.getInt(c.getColumnIndex("uid")));
				map.put("dstate", c.getInt(c.getColumnIndex("dstate")));

				ar.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return ar;
	}
}
