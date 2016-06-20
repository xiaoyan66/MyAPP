package com.example.administrator.ui;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class DetailAdapter extends BaseAdapter {
	private Context context;
	private List ar;
	private LayoutInflater inflater;

	public DetailAdapter(Context context, List ar) {
		this.context = context;
		this.ar = ar;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ar.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return ar.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		viewHolder holder;
		if (arg1 == null) {
			holder = new viewHolder();
			arg1 = inflater.inflate(R.layout.detail_item, null);
			holder.mTvDnumber = (TextView) arg1.findViewById(R.id.tv_dnumber);
			holder.mTvDtime = (TextView) arg1.findViewById(R.id.tv_dtime);
			holder.mTvDstate = (TextView) arg1.findViewById(R.id.tv_dstate);
			holder.mTvDmoney = (TextView) arg1.findViewById(R.id.tv_dmoney);

			arg1.setTag(holder);
		} else {
			holder = (viewHolder) arg1.getTag();
		}
		Map map = (Map) ar.get(arg0);
		holder.mTvDnumber.setText(map.get("inumber").toString());
		holder.mTvDtime.setText(map.get("dtime").toString());
		int dstate = Integer.parseInt(map.get("dstate").toString());
		if (dstate == 1) {
			holder.mTvDstate.setText("�Ѹ���");
		} else {
			holder.mTvDstate.setText("δ����");
		}

		holder.mTvDmoney.setText(map.get("dallprice").toString());
		return arg1;
	}

	private class viewHolder {
		private TextView mTvDnumber;
		private TextView mTvDtime;
		private TextView mTvDmoney;
		private TextView mTvDstate;
	}

}
