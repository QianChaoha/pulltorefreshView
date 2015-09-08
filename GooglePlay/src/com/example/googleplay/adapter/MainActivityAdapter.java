package com.example.googleplay.adapter;

import com.example.googleplay.fragment.Fragment1;
import com.example.googleplay.fragment.FragmentFactory;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
