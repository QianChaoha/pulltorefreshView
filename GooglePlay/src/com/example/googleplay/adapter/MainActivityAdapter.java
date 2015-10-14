package com.example.googleplay.adapter;

import java.util.HashMap;
import java.util.Map;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.googleplay.fragment.AppFragment;
import com.example.googleplay.fragment.CategoryFragment;
import com.example.googleplay.fragment.GameFragment;
import com.example.googleplay.fragment.HomeFragment;
import com.example.googleplay.fragment.SubjectFragment;
import com.example.googleplay.fragment.TopFragment;
import com.example.googleplay.util.FragmentFactory;

public class MainActivityAdapter extends FragmentPagerAdapter {
	private String[] tabStrings;
	private Map<Integer, Fragment> mFragments = new HashMap<Integer, Fragment>();

	public MainActivityAdapter(FragmentManager fm, String[] tabStrings) {
		super(fm);
		this.tabStrings = tabStrings;
	}

	@Override
	public Fragment getItem(int arg0) {

		return getFragment(arg0);
	}

	public Handler getHandler() {
		if (mFragments.get(0) != null) {
			((HomeFragment) mFragments.get(0)).getHandler();
		}
		return null;
	}

	/**
	 * @param arg0
	 * @return
	 */
	private Fragment getFragment(int position) {
		Fragment fragment = null;
		fragment = mFragments.get(position);
		if (fragment == null) {
			if (position == 0) {
				fragment = new HomeFragment();
			} else if (position == 1) {
				fragment = new AppFragment();
			} else if (position == 2) {
				fragment = new GameFragment();
			} else if (position == 3) {
				fragment = new SubjectFragment();
			} else if (position == 4) {
				fragment = new CategoryFragment();
			} else if (position == 5) {
				fragment = new TopFragment();
			}
			if (fragment != null) {
				mFragments.put(position, fragment);
			}
		}
		return fragment;
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
