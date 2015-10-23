package com.example.googleplay.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.googleplay.R;
import com.example.googleplay.activity.view.MenuView;
import com.example.googleplay.adapter.MainActivityAdapter;
import com.example.googleplay.base.BaseActivity;

public class MainActivity extends BaseActivity implements OnQueryTextListener {
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private ViewPager viewPager;
	private MainActivityAdapter mainActivityAdapter;
	private ActionBar actionBar;
	private PagerTabStrip pagerTabStrip;
	private FrameLayout menuView;
	@Override
	protected void initView() {
		menuView = (FrameLayout) findViewById(R.id.menuView);
		//增加侧滑菜单
		menuView.addView(new MenuView(this).getView());
		pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
		pagerTabStrip.setTabIndicatorColorResource(R.color.indicatorcolor);// 设置标签下划线颜色
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		mainActivityAdapter = new MainActivityAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.tab_names));
		viewPager.setAdapter(mainActivityAdapter);

		actionBar = getSupportActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer_am, R.string.open_drawer, R.string.close_drawer) {

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				System.out.println("onDrawerClosed");
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				System.out.println("onDrawerOpened");
			}

		};
		drawerLayout.setDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setOnQueryTextListener(this);
		return super.onCreateOptionsMenu(menu);
	}

	public void click(View view) {
		startActivity(new Intent(this, DetailActivity.class));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_search:
			System.out.println("-------");
			break;

		default:
			break;
		}
		return actionBarDrawerToggle.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onQueryTextChange(String arg0) {
		System.out.println("onQueryTextChange" + arg0);
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		System.out.println("onQueryTextSubmit" + arg0);
		return true;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initData() {
		
	}
}
