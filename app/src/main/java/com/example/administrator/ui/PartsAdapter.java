package com.example.administrator.ui;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ui.R;
import view.RoundImageView;

public class PartsAdapter extends BaseAdapter
{
	private Context context;
	private List ar;
	private LayoutInflater inflater;

	public PartsAdapter(Context context, List ar)
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
			arg1 = inflater.inflate(R.layout.parts_item, null);
			holder.mIvPimage = (RoundImageView) arg1
					.findViewById(R.id.iv_pimage);
			holder.mTvPname = (TextView) arg1.findViewById(R.id.tv_pname);
			holder.mTvPoldPrice = (TextView) arg1
					.findViewById(R.id.tv_poldprice);
			holder.mTvPnewPrice = (TextView) arg1
					.findViewById(R.id.tv_pnewprice);

			arg1.setTag(holder);
		}
		else
		{
			holder = (viewHolder) arg1.getTag();
		}
		Map map = (Map) ar.get(arg0);
		holder.mIvPimage.setImageResource(Integer.parseInt(map.get("pimage")
				.toString()));
		holder.mTvPname.setText(map.get("pname").toString());
		holder.mTvPoldPrice.setText(map.get("poldprice").toString());
		holder.mTvPoldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		holder.mTvPnewPrice.setText(map.get("pnewprice").toString());

		return arg1;
	}

	private class viewHolder
	{
		private RoundImageView mIvPimage;
		private TextView mTvPname;
		private TextView mTvPoldPrice;
		private TextView mTvPnewPrice;
	}

}
