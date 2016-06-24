package com.example.administrator.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import entity.Car;
import util.DBConnection;


public class CarDAO {
	private DBConnection dbc;
	private SQLiteDatabase db;
	private Context context;

	public CarDAO(Context context) {
		this.context = context;
	}

	public CarDAO open() {
		dbc = new DBConnection(context);
		db = dbc.getWritableDatabase();
		return this;
	}

	public void closeAll() {
		db.close();
		dbc.close();
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

	/**
	 * ���ݱ�Ų�ѯ����
	 * 
	 * @param id
	 * @return
	 */
	public Car getAllCarById(int id) {
		Car car = new Car();
		open();
		try {
			Cursor c = db.rawQuery("select * from car where _id=?",
					new String[] { String.valueOf(id) });
			while (c.moveToNext()) {
				car.set_id(c.getInt(c.getColumnIndex("_id")));
				car.setSid(c.getInt(c.getColumnIndex("sid")));
				car.setCname(c.getString(c.getColumnIndex("cname")));
				car.setCimage(c.getInt(c.getColumnIndex("cimage")));

				car.setCrank(c.getString(c.getColumnIndex("crank")));
				car.setCengine(c.getString(c.getColumnIndex("cengine")));
				car.setCgearbox(c.getString(c.getColumnIndex("cgearbox")));
				car.setCstructure(c.getString(c.getColumnIndex("cstructure")));

				car.setColdprice(c.getFloat(c.getColumnIndex("coldprice")));
				car.setCnewprice(c.getFloat(c.getColumnIndex("cnewprice")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return car;
	}
}
