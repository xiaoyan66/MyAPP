package com.example.administrator.ui;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ui.R;


public class CategoryAdapter extends BaseExpandableListAdapter {

	// 品牌表 Brand
	private List ar;
	private LayoutInflater inflater;

	// 填充品牌表的数据
	private String bnames[];
	private int bimages[];
	private ImageView bimage;

	// 车系表(serial)的二维数组
	private String snames[][];// 存放的是每一个总类下子类的名称
	private int sids[][];
	private int simages[][];

	public CategoryAdapter(Context context, List ar) {
		Context context1 = context;
		// 实例化brandDAO
		BrandDAO brandDAO = new BrandDAO(context);
		this.ar = ar;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// 实例化品牌名称和品牌编号数组
		bnames = new String[ar.size()];
		int[] bids = new int[ar.size()];
		bimages = new int[ar.size()];

		// 实例化车系数组
		snames = new String[ar.size()][];
		sids = new int[ar.size()][];
		simages = new int[ar.size()][];

		// 获取品牌名称和编号
		for (int i = 0; i < ar.size(); i++) {
			Map map = (Map) ar.get(i);
			bnames[i] = map.get("bname").toString();
			bids[i] = Integer.parseInt(map.get("bid").toString());
			bimages[i] = Integer.parseInt(map.get("bimage").toString());
		}

		// 获得车系名称和编号
		for (int i = 0; i < bids.length; i++) {
			int id = bids[i];
			List data = brandDAO.getAllSerial(id);
			snames[i] = new String[data.size()];
			sids[i] = new int[data.size()];
			simages[i] = new int[data.size()];
			for (int j = 0; j < data.size(); j++) {
				Map map = (Map) data.get(j);
				snames[i][j] = map.get("sname").toString();
				sids[i][j] = Integer.parseInt(map.get("sid").toString());
				simages[i][j] = Integer.parseInt(map.get("simage").toString());
			}
		}

	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 1、 获得品牌的数量
	 */
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return bnames.length;
	}

	/**
	 * 2、填充品牌
	 */
	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		TextView bname;
		if (arg2 == null) {
			arg2 = inflater.inflate(R.layout.brand_names, null);
			bname = (TextView) arg2.findViewById(R.id.tv_bname);
			bimage = (ImageView) arg2.findViewById(R.id.iv_bimage);
			arg2.setTag(bname);
		} else {
			bname = (TextView) arg2.getTag();
		}
		bname.setText(bnames[arg0]);
		bimage.setImageResource(bimages[arg0]);
		return arg2;
	}

	/**
	 * 3、获得车系的数量
	 */
	@Override
	public int getChildrenCount(int arg0) {
		return snames[arg0].length;
	}

	/**
	 * 4、 填充车系
	 */
	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
							 ViewGroup arg4) {
		ViewHolder viewHolder;
		if (arg3 == null) {
			arg3 = inflater.inflate(R.layout.serial_names, null);
			viewHolder = new ViewHolder();
			viewHolder.mTvSname = (TextView) arg3.findViewById(R.id.tv_sname);
			viewHolder.mIvSimage = (ImageView) arg3
					.findViewById(R.id.iv_simage);
			arg3.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg3.getTag();
		}

		viewHolder.mTvSname.setText(snames[arg0][arg1]);
		viewHolder.mTvSname.setTag(sids[arg0][arg1]);
		viewHolder.mIvSimage.setImageResource(simages[arg0][arg1]);
		return arg3;
	}

	public class ViewHolder {
		private TextView mTvSname;
		private ImageView mIvSimage;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
