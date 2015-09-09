package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;
import com.example.googleplay.R;

/**
 * 圆角ImageView,默认没有圆角，代码使用可以调用setRectAdius设置其圆角，xml文件可使用rect_adius标签设置其圆角
 * 如果想使用圆形ImageView的话可以将circle标签置为true
 * Created by QianChao on 2015/9/1.
 */
public class CircleImageView extends NetworkImageView {
    private final RectF roundRect = new RectF();
    private float rect_adius = 6;
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();
    private float density;
    private boolean isCircle;
    private int width, height;
    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.circleImageview);
        //返回的是像素值，所以与xml文件中的值不一定相等
        rect_adius = ta.getDimension(R.styleable.circleImageview_radius, 6);
        isCircle = ta.getBoolean(R.styleable.circleImageview_circle, false);
        ta.recycle();
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        this.setDefaultImageResId(R.drawable.ic_launcher);
        this.setErrorImageResId(R.drawable.ic_launcher);

        maskPaint.setAntiAlias(true);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);

    }

    public void setRectAdius(int adius) {
        rect_adius = adius;
        invalidate();
    }
    public void setCircle(boolean isCircle) {
        this.isCircle = isCircle;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //width和height的返回值单位为像素,所以不一定与xml文件设置的值相同
        width = getWidth();
        height = getHeight();
        if (isCircle){
            roundRect.set(0, 0, rect_adius, rect_adius);
        }else {
            roundRect.set(0, 0, width, height);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        if (isCircle) {
            //画圆,当rect_adius为roundRect变长一半时为圆
            canvas.drawRoundRect(roundRect, rect_adius/2, rect_adius/2, zonePaint);
            canvas.saveLayer(0, 0, rect_adius, rect_adius, maskPaint, Canvas.ALL_SAVE_FLAG);
        } else {
            //画圆角矩形，
            canvas.drawRoundRect(roundRect, rect_adius, rect_adius, zonePaint);
            canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
        }
        super.draw(canvas);
        canvas.restore();
    }

}
