/**
 * 
 */
package com.example.googleplay.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.googleplay.R;
import com.example.googleplay.base.BaseAdapterNoMore;
import com.example.googleplay.data.CategoryInfo;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetRequest;
import com.example.googleplay.view.CommenNetImageView;

/**
 * Description: Company: guanghua
 * 
 * @author qianchao
 */
public class CategoryAdapter extends BaseAdapterNoMore<CategoryInfo.Info, CategoryViewHolder, NoMoreHolder> {

	/**
	 * @param context
	 * @param lists
	 */
	public CategoryAdapter(Context context, List<CategoryInfo.Info> lists) {
		super(context, lists);
	}

	@Override
	protected void initViewByOtherHolder(NoMoreHolder holder, View convertView) {
		holder.textview = (TextView) convertView.findViewById(R.id.textview);
	}

	@Override
	protected void initDataNoMore(NoMoreHolder holder, CategoryInfo.Info data, int position) {
		holder.textview.setText(data.title);
	}

	@Override
	protected NoMoreHolder getOtherHolder() {
		return new NoMoreHolder();
	}

	@Override
	protected CategoryViewHolder getHolder() {
		return new CategoryViewHolder();
	}

	@Override
	protected void initViewByHolder(CategoryViewHolder holder, View convertView) {
		holder.iv1 = (CommenNetImageView) convertView.findViewById(R.id.iv_1);
		holder.iv2 = (CommenNetImageView) convertView.findViewById(R.id.iv_2);
		holder.iv3 = (CommenNetImageView) convertView.findViewById(R.id.iv_3);
		holder.tv1 = (TextView) convertView.findViewById(R.id.tv_1);
		holder.tv2 = (TextView) convertView.findViewById(R.id.tv_2);
		holder.tv3 = (TextView) convertView.findViewById(R.id.tv_3);
	}

	@Override
	protected void initData(CategoryViewHolder holder, CategoryInfo.Info data, int position) {
		if (TextUtils.isEmpty(data.url1) || TextUtils.isEmpty(data.name1)) {
			holder.iv1.setVisibility(View.INVISIBLE);
			holder.tv1.setVisibility(View.INVISIBLE);
		} else {
			holder.iv1.setVisibility(View.VISIBLE);
			holder.tv1.setVisibility(View.VISIBLE);
			holder.iv1.setImageUrl(HttpHelper.URL + "image?name=" + data.url1, NetRequest.getInstance(context).getImageLoader());
			holder.tv1.setText(data.name1);
		}

		if (TextUtils.isEmpty(data.url2) || TextUtils.isEmpty(data.name2)) {
			holder.iv2.setVisibility(View.INVISIBLE);
			holder.tv2.setVisibility(View.INVISIBLE);
		} else {
			holder.iv2.setVisibility(View.VISIBLE);
			holder.tv2.setVisibility(View.VISIBLE);
			holder.iv2.setImageUrl(HttpHelper.URL + "image?name=" + data.url2, NetRequest.getInstance(context).getImageLoader());
			holder.tv2.setText(data.name2);
		}
		if (TextUtils.isEmpty(data.url3) || TextUtils.isEmpty(data.name3)) {
			holder.iv3.setVisibility(View.INVISIBLE);
			holder.tv3.setVisibility(View.INVISIBLE);
		} else {
			holder.iv3.setVisibility(View.VISIBLE);
			holder.tv3.setVisibility(View.VISIBLE);
			holder.iv3.setImageUrl(HttpHelper.URL + "image?name=" + data.url3, NetRequest.getInstance(context).getImageLoader());
			holder.tv3.setText(data.name3);
		}

	}

	@Override
	protected int getLayoutId() {
		return R.layout.item_category_content;
	}

	@Override
	protected int getOtherLayoutId() {
		return R.layout.item_textview;
	}

	@Override
	protected boolean isOtherItem(CategoryInfo.Info data) {
		return data.isTitle;
	}

}

class CategoryViewHolder {
	CommenNetImageView iv1, iv2, iv3;
	TextView tv1, tv2, tv3;
}

class NoMoreHolder {
	TextView textview;
}
