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


public class SerialCategoryAdapter extends BaseExpandableListAdapter {
	private SerialDAO serialDAO;// 父类
	private Context context;

	// 车系表
	private List ar;
	private LayoutInflater inflater;

	// 填充车系表的数据
	private String snames[];
	private int sids[];
	private int simages[];
	private TextView sname;
	private ImageView simage;

	// 汽车详细表
	private String cnames[][];
	private int cids[][];
	private int cimages[][];

	public SerialCategoryAdapter(Context context, List ar) {
		this.context = context;
		// 实例化SerialDao
		serialDAO = new SerialDAO(context);
		this.ar = ar;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// 实例化车系表数据
		snames = new String[ar.size()];
		sids = new int[ar.size()];
		simages = new int[ar.size()];

		// 实例化汽车详细数据
		cnames = new String[ar.size()][];
		cids = new int[ar.size()][];
		cimages = new int[ar.size()][];

		// 获得车系表数据
		for (int i = 0; i < ar.size(); i++) {
			Map map = (Map) ar.get(i);
			snames[i] = map.get("sname").toString();
			sids[i] = Integer.parseInt(map.get("sid").toString());
			simages[i] = Integer.parseInt(map.get("simage").toString());
		}

		// 获得汽车数据
		for (int i = 0; i < sids.length; i++) {
			int id = sids[i];
			// 根据车系查询汽车详情
			List carList = serialDAO.getAllCarBySid(id);
			cnames[i] = new String[carList.size()];
			cids[i] = new int[carList.size()];
			cimages[i] = new int[carList.size()];
			for (int j = 0; j < carList.size(); j++) {
				Map map = (Map) carList.get(j);
				cnames[i][j] = map.get("cname").toString();
				cids[i][j] = Integer.parseInt(map.get("cid").toString());
				cimages[i][j] = Integer.parseInt(map.get("cimage").toString());
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
	 * 4、填充汽车详细
	 */
	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
							 ViewGroup arg4) {
		ViewHolder viewHolder;
		if (arg3 == null) {
			arg3 = inflater.inflate(R.layout.car_names, null);
			viewHolder = new ViewHolder();
			viewHolder.mIvCimage = (ImageView) arg3
					.findViewById(R.id.iv_cimage);
			viewHolder.mTvCname = (TextView) arg3.findViewById(R.id.tv_cname);

			arg3.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg3.getTag();
		}

		viewHolder.mIvCimage.setImageResource(cimages[arg0][arg1]);
		viewHolder.mTvCname.setText(cnames[arg0][arg1]);
		viewHolder.mTvCname.setTag(cids[arg0][arg1]);

		return arg3;
	}

	/**
	 * 获得汽车详细的数量
	 */
	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return cnames[arg0].length;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 1.获得车系的数量
	 */
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return snames.length;
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 2、填充车系
	 */
	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		if (arg2 == null) {
			arg2 = inflater.inflate(R.layout.all_serial_names, null);
			sname = (TextView) arg2.findViewById(R.id.tv_sname);
			simage = (ImageView) arg2.findViewById(R.id.iv_simage);
			arg2.setTag(sname);
		} else {
			sname = (TextView) arg2.getTag();
		}

		sname.setText(snames[arg0]);
		simage.setImageResource(simages[arg0]);
		return arg2;
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

	/**
	 * 缓存
	 *
	 * @author Administrator
	 *
	 */
	public class ViewHolder {
		private ImageView mIvCimage;
		private TextView mTvCname;
	}

}
