/**
 * 
 */
package com.example.googleplay.activity.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.googleplay.R;
import com.example.googleplay.base.BaseViewHolder;
import com.example.googleplay.data.AppInfo;

/**
 * Description: Company: guanghua
 * 
 * @author qianchao
 */
public class DetailDesHolder extends BaseViewHolder<AppInfo> implements OnClickListener {
	private TextView des_content;
	private TextView des_author;
	private ImageView des_arrow;
	private RelativeLayout des_layout;
	private ScrollView scrollView;

	/**
	 * @param context
	 * @param bottom_layout 
	 */
	public DetailDesHolder(Context context, ScrollView scrollView) {
		super(context);
		this.scrollView = scrollView;
	}

	@Override
	public int getLayoutId() {
		return R.layout.detail_des;
	}

	@Override
	public void initView(View view) {
		des_content = (TextView) view.findViewById(R.id.des_content);
		des_author = (TextView) view.findViewById(R.id.des_author);
		des_arrow = (ImageView) view.findViewById(R.id.des_arrow);
		des_layout = (RelativeLayout) view.findViewById(R.id.des_layout);
	}

	@Override
	public void initData(AppInfo data) {
		des_content.setText(data.getDes());
		des_author.setText("作者:" + data.getAuthor());
		des_layout.setOnClickListener(this);

		// des_content 起始高度7行的高度
		LayoutParams layoutParams = des_content.getLayoutParams();
		layoutParams.height = getShortMeasureHeight();
		des_content.setLayoutParams(layoutParams);
		des_arrow.setImageResource(R.drawable.arrow_down);
	}

	public int getShortMeasureHeight() {
		// 复制一个新的TextView 用来测量,最好不要在之前的TextView测量 有可能影响其它代码执行
		TextView textView = new TextView(context);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);// 设置字体大小14dp
		// textView.setMaxLines(7);
		textView.setLines(7);// 强制有7行
		int width = des_content.getMeasuredWidth(); // 开始宽度

		int widthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY, width);
		int heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.AT_MOST, 1000);
		textView.measure(widthMeasureSpec, heightMeasureSpec);
		return textView.getMeasuredHeight();
	}

	/**
	 * 获取全部高度
	 * 
	 * @return
	 */
	public int getAllMeasureHeight() {
		int width = des_content.getMeasuredWidth(); // 开始宽度
		int widthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY, width);
		int heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.AT_MOST, 1000);
		des_content.measure(widthMeasureSpec, heightMeasureSpec);
		return des_content.getMeasuredHeight();
	}

	private boolean isDown = true;

	@Override
	public void onClick(View v) {
		int startHeight = 0, endHeight = 0;
		if (isDown) {
			startHeight = getShortMeasureHeight();
			endHeight = getAllMeasureHeight();
		} else {
			endHeight = getShortMeasureHeight();
			startHeight = getAllMeasureHeight();
		}
		isDown = !isDown;
		ValueAnimator valueAnimator = ValueAnimator.ofInt(startHeight, endHeight);
		valueAnimator.setDuration(500);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				LayoutParams layoutParams = des_content.getLayoutParams();
				int height = (Integer) animation.getAnimatedValue();
				layoutParams.height = height;
				des_content.setLayoutParams(layoutParams);
				scrollView.scrollTo(0, scrollView.getMeasuredHeight());// 让scrollView 移动到最下面
			}
		});
		valueAnimator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				if (isDown) {
					des_arrow.setImageResource(R.drawable.arrow_down);
				}else {
					des_arrow.setImageResource(R.drawable.arrow_up);
				}
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				
			}
		});
		valueAnimator.start();
	}

}
