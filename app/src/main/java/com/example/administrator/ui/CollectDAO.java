package com.example.administrator.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import entity.Collect;
import util.DBConnection;


public class CollectDAO
{
	private DBConnection dbc;
	private SQLiteDatabase db;
	private Context context;

	public CollectDAO(Context context)
	{
		this.context = context;
	}

	public CollectDAO open()
	{
		dbc = new DBConnection(context);
		db = dbc.getWritableDatabase();
		return this;
	}

	public void closeAll()
	{
		db.close();
		dbc.close();
	}

	public List getAllCollect()
	{
		List ar = new ArrayList();
		open();
		try
		{
			Cursor c = db.rawQuery("select * from collect", null);
			while (c.moveToNext())
			{
				Map map = new HashMap();
				map.put("cid", c.getInt(c.getColumnIndex("_id")));
				map.put("uid", c.getInt(c.getColumnIndex("uid")));
				map.put("gid", c.getInt(c.getColumnIndex("gid")));
				map.put("gname", c.getString(c.getColumnIndex("gname")));
				map.put("gprice", c.getFloat(c.getColumnIndex("gprice")));
				map.put("gsource", c.getInt(c.getColumnIndex("gsource")));
				map.put("gimage", c.getInt(c.getColumnIndex("gimage")));
				ar.add(map);

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		closeAll();
		return ar;
	}

	/***
	 * 根据商品编号获得一个收藏表对象
	 * 
	 * @param gid
	 * @return
	 */
	public Collect getOneCollect(int gid, int _gsource)
	{
		Collect collect = null;
		open();
		try
		{
			Cursor c = db.rawQuery(
					"select * from collect where gid=? and gsource=?",
					new String[]
					{ String.valueOf(gid), String.valueOf(_gsource) });
			while (c.moveToNext())
			{
				int id = c.getInt(c.getColumnIndex("_id"));
				int uid = c.getInt(c.getColumnIndex("uid"));
				int _gid = c.getInt(c.getColumnIndex("gid"));
				String gname = c.getString(c.getColumnIndex("gname"));
				float gprice = c.getFloat(c.getColumnIndex("gprice"));
				int gsource = c.getInt(c.getColumnIndex("gsource"));
				int gimage = c.getInt(c.getColumnIndex("gimage"));

				collect = new Collect(id, uid, _gid, gname, gprice, gsource,
						gimage);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		closeAll();
		return collect;
	}

	/**
	 * 加入收藏
	 */
	public void addCollect(Collect collect)
	{
		open();
		try
		{
			ContentValues values = new ContentValues();
			values.put("uid", collect.getUid());
			values.put("gid", collect.getGid());
			values.put("gname", collect.getGname());
			values.put("gprice", collect.getGprice());
			values.put("gsource", collect.getGsource());
			values.put("gimage", collect.getGimage());
			db.insert("collect", null, values);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		closeAll();
	}
}
