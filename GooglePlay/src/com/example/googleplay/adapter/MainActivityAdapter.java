package com.example.googleplay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.util.FragmentFactory;

public class MainActivityAdapter extends FragmentPagerAdapter {
	private String[] tabStrings;

	public MainActivityAdapter(FragmentManager fm, String[] tabStrings) {
		super(fm);
		this.tabStrings = tabStrings;
	}

	@Override
	public Fragment getItem(int arg0) {

		return FragmentFactory.createFragment(arg0);
	}

	@Override
	public int getCount() {

		return tabStrings.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return tabStrings[position];
	}
}
