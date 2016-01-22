package com.baoyz.swipemenulistview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshSwipeListView;
import com.handmark.pulltorefresh.library.R;

public class MainActivity extends Activity {
	private PullToRefreshSwipeListView listView;
	private SwipeMenuListView menuListView;
	private String[] arr = new String[20];
	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			listView.onRefreshComplete();
			Toast.makeText(MainActivity.this, "更新完成", Toast.LENGTH_SHORT).show();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		for (int i = 0; i < 20; i++) {
			arr[i] = i + "";
		}
		listView = (PullToRefreshSwipeListView) findViewById(R.id.lv);
		listView.setMode(Mode.BOTH);

		menuListView =listView.getRefreshableView();
		menuListView.setMenuCreator(new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				SwipeMenuItem callItem = new SwipeMenuItem(getApplicationContext());
				// set item background
				callItem.setBackground(new ColorDrawable(Color.GRAY));
				// set item width
				callItem.setWidth(130);
				// set a icon
				// deleteItem.setIcon(R.drawable.ic_launcher);
				callItem.setTitle("Call");
				callItem.setTitleColor(Color.WHITE);
				callItem.setTitleSize(18);
				
				SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.RED));
				// set item width
				deleteItem.setWidth(130);
				// set a icon
				// deleteItem.setIcon(R.drawable.ic_launcher);
				deleteItem.setTitle("Delete");
				deleteItem.setTitleColor(Color.WHITE);
				deleteItem.setTitleSize(18);
				// add to menu
				menu.addMenuItem(callItem);
				menu.addMenuItem(deleteItem);
			}
		});
		
		
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view=View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
				TextView textView=(TextView) view.findViewById(android.R.id.text1);
				textView.setText(arr[position]);
				textView.setHeight(120);
				return view;
			}
		});
		
		menuListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
				if (index==0) {
					Toast.makeText(MainActivity.this, "Call", Toast.LENGTH_SHORT).show();
				}else if (index==1) {
					Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
				}
				return false;
			}
		});
		listView.setOnRefreshListener(new OnRefreshListener2() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase refreshView) {
				System.out.println("下拉刷新");
				handler.sendEmptyMessageDelayed(0, 1000);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase refreshView) {
				System.out.println("上拉加载");
				handler.sendEmptyMessageDelayed(0, 1000);
			}
		});

	}
}
