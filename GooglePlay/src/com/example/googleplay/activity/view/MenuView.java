package com.example.googleplay.activity.view;

import android.content.Context;
import android.view.View;
import com.example.googleplay.R;

public class MenuView {

	private Context context;
	private View menuView;

	public MenuView(Context context) {
		this.context = context;
	}

	public View getView() {
		 menuView=View.inflate(context, R.layout.menu_holder, null);
		 return menuView;
	}
}
