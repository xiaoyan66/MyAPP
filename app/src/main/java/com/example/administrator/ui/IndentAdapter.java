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

import ui.R;

public class IndentAdapter extends BaseAdapter
{
	private Context context;
	private List ar;
	private LayoutInflater inflater;

	public IndentAdapter(Context context, List ar)
	{
		this.context = context;
		this.ar = ar;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return ar.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		// TODO Auto-generated method stub
		return ar.get(arg0);
	}

	@Override
	public long getItemId(int arg0)
	{
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2)
	{
		viewHolder holder;
		if (arg1 == null)
		{
			holder = new viewHolder();
			arg1 = inflater.inflate(R.layout.indent_item, null);

			holder.mIvMyimage = (ImageView) arg1.findViewById(R.id.iv_myimage);
			holder.mTvMyname = (TextView) arg1.findViewById(R.id.tv_myname);
			holder.mTvMyprice = (TextView) arg1.findViewById(R.id.tv_myprice);
			holder.mTvMycount = (TextView) arg1.findViewById(R.id.tv_mycount);

			arg1.setTag(holder);
		}
		else
		{
			holder = (viewHolder) arg1.getTag();
		}
		Map map = (Map) ar.get(arg0);
		holder.mIvMyimage.setBackgroundResource(Integer.parseInt(map.get(
				"iimage").toString()));
		holder.mTvMyname.setText(map.get("iname").toString());
		holder.mTvMyprice.setText(map.get("iprice").toString());
		holder.mTvMycount.setText(map.get("icount").toString());
		return arg1;
	}

	private class viewHolder
	{
		private ImageView mIvMyimage;
		private TextView mTvMyname;
		private TextView mTvMyprice;
		private TextView mTvMycount;
	}

}
