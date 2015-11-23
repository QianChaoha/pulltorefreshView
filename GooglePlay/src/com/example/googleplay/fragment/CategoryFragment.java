package com.example.googleplay.fragment;

import java.util.ArrayList;
import java.util.List;
import android.view.View;
import com.example.googleplay.R;
import com.example.googleplay.adapter.CategoryAdapter;
import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.base.BaseListView;
import com.example.googleplay.data.CategoryInfo;
import com.example.googleplay.data.CategoryInfo.Info;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetWorkResponse;
import com.example.volley.Request.Method;

public class CategoryFragment extends BaseFragment {
	private BaseListView baseListView;
	@Override
	protected int getLayoutId() {
		return R.layout.baselistview;	
	}

	@Override
	protected void initView(View view) {
		baseListView = (BaseListView) view.findViewById(R.id.listview);
	}

	@Override
	protected void initData() {
		//final Context context, int method, final String url, final Map<String, String> param,
		NetWorkResponse<List<CategoryInfo>> netWorkResponse=new NetWorkResponse<List<CategoryInfo>>(getActivity(),Method.GET,HttpHelper.CATEGORY_URL,null) {
			
			@Override
			public void onSuccess(List<CategoryInfo> backData) {
				 List<Info> infos=new ArrayList<CategoryInfo.Info>();
				 for (CategoryInfo categoryInfo : backData) {
					 CategoryInfo.Info infoTitle=new CategoryInfo().new Info();
					 infoTitle.isTitle=true;
					 infoTitle.title=categoryInfo.title;
					 infos.add(infoTitle);
					for (Info info : categoryInfo.infos) {
						 CategoryInfo.Info infoContent=new CategoryInfo().new Info();
						 infoContent.isTitle=false;
						 infoContent.name1=info.name1;
						 infoContent.name2=info.name2;
						 infoContent.name3=info.name3;
						 infoContent.url1=info.url1;
						 infoContent.url2=info.url2;
						 infoContent.url3=info.url3;
						 infos.add(infoContent);
					}
				}
				baseListView.setAdapter(new CategoryAdapter(getContext(), infos));
			}
		};
	}
}
