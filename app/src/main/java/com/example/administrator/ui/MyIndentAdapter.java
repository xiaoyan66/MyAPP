package com.example.administrator.ui;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyIndentAdapter extends FragmentPagerAdapter {
	private List ar;

	public MyIndentAdapter(FragmentManager fm, List ar) {
		super(fm);
		this.ar = ar;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return (Fragment) ar.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ar.size();
	}

}
