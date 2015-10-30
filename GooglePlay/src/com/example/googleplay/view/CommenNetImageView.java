package com.example.googleplay.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;
import com.example.googleplay.R;

/**
 * Created by QianChao on 2015/9/2.
 */
public class CommenNetImageView extends NetworkImageView {
	// 按照宽高比例去显示
	private float ratio = 0f; // 比例值

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

	public CommenNetImageView(Context context) {
		super(context);
		init();
	}

	public CommenNetImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initRatio(context, attrs);
		init();
	}

	private void initRatio(Context context, AttributeSet attrs) {
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ratioLayout);
		ratio = ta.getFloat(R.styleable.ratioLayout_ratio, ratio);
		setRatio(ratio);
	}

	public CommenNetImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initRatio(context, attrs);
		init();
	}

	public void init() {
		setErrorImageResId(R.drawable.ic_default);
		setDefaultImageResId(R.drawable.ic_default);
	}

	// 测量当前布局
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (ratio != 0) {
			// widthMeasureSpec 宽度的规则 包含了两部分 模式 值
			int widthMode = MeasureSpec.getMode(widthMeasureSpec); // 模式
			int widthSize = MeasureSpec.getSize(widthMeasureSpec);// 宽度大小
			int width = widthSize - getPaddingLeft() - getPaddingRight();// 去掉左右两边的padding

			int heightMode = MeasureSpec.getMode(heightMeasureSpec); // 模式
			int heightSize = MeasureSpec.getSize(heightMeasureSpec);// 高度大小
			int height = heightSize - getPaddingTop() - getPaddingBottom();// 去掉上下两边的padding

			if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
				// 修正一下 高度的值 让高度=宽度/比例
				height = (int) (width / ratio + 0.5f); // 保证4舍五入
			} else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
				// 由于高度是精确的值 ,宽度随着高度的变化而变化
				width = (int) ((height * ratio) + 0.5f);
			}
			// 重新制作了新的规则
			widthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY, width + getPaddingLeft() + getPaddingRight());
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY, height + getPaddingTop() + getPaddingBottom());
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
