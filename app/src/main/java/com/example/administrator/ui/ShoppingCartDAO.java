package com.example.administrator.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import entity.MyEntity;
import entity.ShoppingCart;
import util.DBConnection;


public class ShoppingCartDAO {
	private DBConnection dbc;
	private SQLiteDatabase db;
	private Context context;

	public ShoppingCartDAO(Context context) {
		this.context = context;
	}

	public ShoppingCartDAO open() {
		dbc = new DBConnection(context);
		db = dbc.getWritableDatabase();
		return this;
	}

	public void closeAll() {
		db.close();
		dbc.close();
	}

	/**
	 * 查询所有购物车数据
	 */
	public List getAll() {
		List arr = new ArrayList();
		// 第一步：全查询购物车表
		List ar = new ArrayList();
		open();
		try {
			Cursor c = db.rawQuery("select * from shoppingcart", null);
			while (c.moveToNext()) {
				Map map = new HashMap();
				map.put("sid", c.getInt(c.getColumnIndex("_id")));
				map.put("uid", c.getInt(c.getColumnIndex("uid")));
				map.put("gid", c.getInt(c.getColumnIndex("gid")));
				map.put("gcount", c.getInt(c.getColumnIndex("gcount")));
				map.put("gsource", c.getInt(c.getColumnIndex("gsource")));
				map.put("gbuy", c.getInt(c.getColumnIndex("gbuy")));

				ar.add(map);
			}

			// 第二步：分别取汽车表和装饰表
			for (int i = 0; i < ar.size(); i++) {
				Map m = (Map) ar.get(i);
				// 购物车主键
				int sid = Integer.parseInt(m.get("sid").toString());
				// 商品编号
				int gid = Integer.parseInt(m.get("gid").toString());
				// 商品来源
				int gsource = Integer.parseInt(m.get("gsource").toString());
				// 商品数量
				int gcount = Integer.parseInt(m.get("gcount").toString());

				// 判断商品来源：1、汽车表2、配饰表
				if (gsource == 1) {
					Cursor c1 = db.rawQuery("select * from car where _id=?",
							new String[] { String.valueOf(gid) });
					while (c1.moveToNext()) {

						int myimage = c1.getInt(c1.getColumnIndex("cimage"));
						String myname = c1
								.getString(c1.getColumnIndex("cname"));
						float myprice = c1.getFloat(c1
								.getColumnIndex("cnewprice"));

						MyEntity myEntity = new MyEntity();

						myEntity.setMyimage(myimage);
						myEntity.setMyname(myname);
						myEntity.setMyprice(myprice);
						myEntity.setMycount(gcount);
						myEntity.setMyid(sid);

						arr.add(myEntity);

					}
				} else {
					Cursor c1 = db.rawQuery("select * from parts where _id=?",
							new String[] { String.valueOf(gid) });
					while (c1.moveToNext()) {

						int myimage = c1.getInt(c1.getColumnIndex("pimage"));
						String myname = c1
								.getString(c1.getColumnIndex("pname"));
						float myprice = c1.getFloat(c1
								.getColumnIndex("pnewprice"));

						MyEntity myEntity = new MyEntity();

						myEntity.setMyimage(myimage);
						myEntity.setMyname(myname);
						myEntity.setMyprice(myprice);
						myEntity.setMycount(gcount);
						myEntity.setMyid(sid);

						arr.add(myEntity);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return arr;
	}

	/**
	 * 加入购物车
	 */
	public void addShoppingCart(ShoppingCart cart) {
		open();
		try {
			// db.execSQL(
			// "insert into shoppingcart values(null,?,?,?,?,?)",
			// new String[] { String.valueOf(cart.getUid()),
			// String.valueOf(cart.getGid()),
			// String.valueOf(cart.getGcount()),
			// String.valueOf(cart.getGsource()),
			// String.valueOf(cart.getGbuy()) });

			ContentValues values = new ContentValues();
			values.put("uid", cart.getUid());
			values.put("gid", cart.getGid());
			values.put("gcount", cart.getGcount());
			values.put("gsource", cart.getGsource());
			values.put("gbuy", cart.getGbuy());

			db.insert("shoppingcart", null, values);

		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
	}

	/**
	 * 根据编号查询购物车
	 */
	public ShoppingCart getShoppingCart(int sid) {
		ShoppingCart shoppingCart = null;
		open();
		try {
			Cursor c = db.rawQuery("select * from shoppingcart where _id=?",
					new String[] { String.valueOf(sid) });
			while (c.moveToNext()) {
				int id = c.getInt(c.getColumnIndex("_id"));
				int uid = c.getInt(c.getColumnIndex("uid"));
				int gid = c.getInt(c.getColumnIndex("gid"));
				int gcount = c.getInt(c.getColumnIndex("gcount"));
				int gsource = c.getInt(c.getColumnIndex("gsource"));
				int gbuy = c.getInt(c.getColumnIndex("gbuy"));

				shoppingCart = new ShoppingCart(id, uid, gid, gcount, gsource,
						gbuy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		closeAll();
		return shoppingCart;
	}

	/**
	 * 删除购物车
	 */
	public void delShoppingcart(int id) {
		open();
		try {
			db.delete("shoppingcart", "_id=?",
					new String[] { String.valueOf(id) });
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
	}

	/***
	 * 根据商品编号和来源获得一个购物车表对象
	 *
	 * @param gid
	 * @return
	 */
	public ShoppingCart getOneShoppingCart(int gid, int gsource) {
		ShoppingCart shoppingCart = null;
		open();
		try {
			Cursor c = db
					.rawQuery(
							"select * from shoppingcart where gid=? and gsource=?",
							new String[] { String.valueOf(gid),
									String.valueOf(gsource) });
			while (c.moveToNext()) {
				int id = c.getInt(c.getColumnIndex("_id"));
				int uid = c.getInt(c.getColumnIndex("uid"));
				int _gid = c.getInt(c.getColumnIndex("gid"));
				int gcount = c.getInt(c.getColumnIndex("gcount"));
				int _gsource = c.getInt(c.getColumnIndex("gsource"));
				int gbuy = c.getInt(c.getColumnIndex("gbuy"));

				shoppingCart = new ShoppingCart(id, uid, _gid, gcount,
						_gsource, gbuy);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
		return shoppingCart;
	}

	/**
	 * 修改购物车数量
	 *
	 * @param shoppingCart
	 */
	public void updateShoppingcart(ShoppingCart shoppingCart) {
		open();
		try {
			ContentValues values = new ContentValues();
			values.put("uid", shoppingCart.getUid());
			values.put("gid", shoppingCart.getGid());
			values.put("gcount", shoppingCart.getGcount());
			values.put("gsource", shoppingCart.getGsource());
			values.put("gbuy", shoppingCart.getGbuy());

			db.update("shoppingcart", values, "gid=?",
					new String[] { String.valueOf(shoppingCart.getGid()) });
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeAll();
	}

}
