package com.example.administrator.ui;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CarAdapter extends BaseAdapter {
	private Context context;
	private List ar;
	private LayoutInflater inflater;

	public CarAdapter(Context context, List ar) {
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
			arg1 = inflater.inflate(R.layout.car_item, null);
			
			holder.mIvCimage = (ImageView) arg1.findViewById(R.id.iv_cimage);
			holder.mTvCname = (TextView) arg1.findViewById(R.id.tv_cname);
			arg1.setTag(holder);
		} else {
			holder = (viewHolder) arg1.getTag();
		}
		Map map = (Map) ar.get(arg0);
		holder.mIvCimage.setImageResource(Integer.parseInt(map.get("cimage")
				.toString()));
		holder.mTvCname.setText(map.get("cname").toString());
		return arg1;
	}

	private class viewHolder {
		private ImageView mIvCimage;
		private TextView mTvCname;
	}

}