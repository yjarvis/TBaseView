package com.jarvis.tbaseview.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * Created by liuhan on 17/3/10.
 */

public class AjustPositionImageView extends ImageView {

    public AjustPositionImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public AjustPositionImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public AjustPositionImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AjustPositionImageView(Context context) {
        super(context);
        init(context, null);
    }

    private Matrix matrix;
    private void init(Context context, AttributeSet attrs) {

    }

    public void setmAdjustBean() {
        removeCallbacks(showBitmap);
        postDelayed(showBitmap, 10);
    }

    private int width;
    private int hight;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = right-left;
        hight = bottom-top;
        removeCallbacks(showBitmap);
        postDelayed(showBitmap, 10);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        removeCallbacks(showBitmap);
        postDelayed(showBitmap, 10);
    }

    private Runnable showBitmap = new Runnable() {
        @Override
        public void run() {
            changed();
        }
    };

    private void changed(){
        if(getDrawable()!=null&&width>0&&hight>0){
            Drawable drawable = getDrawable();
            int avaterWidth = drawable.getIntrinsicWidth();
            int avaterHight = drawable.getIntrinsicHeight();

//            float contour_leftright = avaterWidth*(mAdjustBean.contour_right1.x-mAdjustBean.contour_left1.x);
            float contour_leftright = 11800.001f;
            float horizontal_scale = 48*width/contour_leftright;


            if(matrix==null) {
                matrix = new Matrix();
                setScaleType(ScaleType.MATRIX);
            }

			matrix.setScale(horizontal_scale,horizontal_scale);

//            float contour_center_x = horizontal_scale*mAdjustBean.nose_tip.x*avaterWidth/100;
//            float contour_center_y = horizontal_scale*mAdjustBean.nose_tip.y*avaterHight/100;
            float contour_center_x =411.66098f;
            float contour_center_y =519.05084f;

            matrix.postTranslate(width/2-contour_center_x,hight*0.45f-contour_center_y);
            setImageMatrix(matrix);
        }
    }
}
