package com.example.administrator.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import entity.MyEntity;
import util.ListItemClickHelp;

public class ShoppingCartAdapter extends BaseAdapter {
	private Context context;
	private List ar;
	private ListItemClickHelp callback;
	private LayoutInflater inflater;

	private int id;

	public ShoppingCartAdapter(Context context, List ar,
			ListItemClickHelp callback) {
		this.context = context;
		this.ar = ar;
		this.callback = callback;
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
	public View getView(final int position, View arg1, final ViewGroup arg2) {
		viewHolder holder;
		if (arg1 == null) {
			holder = new viewHolder();
			arg1 = inflater.inflate(R.layout.shopping_cart_item, null);
			holder.mCbCheck = (CheckBox) arg1.findViewById(R.id.cb_check);
			holder.mIvGimage = (ImageView) arg1.findViewById(R.id.iv_gimage);
			holder.mTvGname = (TextView) arg1.findViewById(R.id.tv_gname);
			holder.mTvGprice = (TextView) arg1.findViewById(R.id.tv_gprice);
			holder.mTvGcount = (TextView) arg1.findViewById(R.id.tv_gcount);

			// �Ӽ���ť
			holder.mIbJian = (ImageButton) arg1.findViewById(R.id.ib_jian);
			holder.mIbSum = (ImageButton) arg1.findViewById(R.id.ib_sum);

			arg1.setTag(holder);
		} else {
			holder = (viewHolder) arg1.getTag();
		}
		MyEntity myEntity = (MyEntity) ar.get(position);
		holder.mIvGimage.setImageResource(myEntity.getMyimage());
		holder.mTvGname.setText(myEntity.getMyname());
		holder.mTvGprice.setText(myEntity.getMyprice() + "");
		holder.mTvGcount.setText(myEntity.getMycount() + "");
		holder.mCbCheck.setChecked(myEntity.isChecked());

		// �Ӽ���ť
		final View view = arg1;
		final int p = position;
		final int jian = holder.mIbJian.getId();
		holder.mIbJian.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				callback.onClick(view, arg2, p, jian);
			}
		});

		final int sum = holder.mIbSum.getId();
		holder.mIbSum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				callback.onClick(view, arg2, p, sum);
			}
		});

		holder.mCbCheck.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CheckBox cb = (CheckBox) v;
				MyEntity myEntity = (MyEntity) ar.get(position);
				myEntity.setChecked(cb.isChecked());

				float sum = 0.0f;
				List myar = new ArrayList();
				;
				for (int i = 0; i < ar.size(); i++) {
					MyEntity entity = (MyEntity) ar.get(i);
					if (entity.isChecked()) {
						sum = sum + (entity.getMycount() * entity.getMyprice());
						id = entity.getMyid();
						myar.add(id);
					}
				}

				// ͨ����ͼ������㲥�ġ�Ƶ��������Ҫ�㲥������
				Intent intent = new Intent();
				intent.setAction("87.6");
				intent.putExtra("sum", sum);
				intent.putIntegerArrayListExtra("myar",
						(ArrayList<Integer>) myar);
				context.sendBroadcast(intent);// ���͹㲥
			}
		});

		holder.mCbCheck
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean arg1) {
						MyEntity myEntity = (MyEntity) ar.get(position);
						myEntity.setChecked(arg1);

						float sum = 0.0f;
						List myar = new ArrayList();

						for (int i = 0; i < ar.size(); i++) {
							MyEntity entity = (MyEntity) ar.get(i);
							if (entity.isChecked()) {
								sum = sum
										+ (entity.getMycount() * entity
												.getMyprice());
								id = entity.getMyid();
								myar.add(id);
							}
						}
						// ͨ����ͼ������㲥�ġ�Ƶ��������Ҫ�㲥������
						Intent intent = new Intent();
						intent.setAction("87.6");
						intent.putExtra("sum", sum);
						intent.putIntegerArrayListExtra("myar",
								(ArrayList<Integer>) myar);
						context.sendBroadcast(intent);// ���͹㲥
					}
				});

		return arg1;
	}

	private class viewHolder {
		private CheckBox mCbCheck;
		private ImageView mIvGimage;
		private TextView mTvGname;
		private TextView mTvGprice;
		private TextView mTvGcount;

		private ImageButton mIbJian;
		private ImageButton mIbSum;
	}

}
