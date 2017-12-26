package com.jarvis.tbaseview.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 贝塞尔曲线
 * @author tansheng
 *
 */
public class TrackView extends View {
	@SuppressWarnings("unused")
	private Context context;
	private Paint paint;

	private Path mPath;
	/**开始点*/
	private float sx,sy;
	/**控制点*/
	private float kx=300,ky=150;
	/**终点*/
	private float zx=100,zy=500;
	
	public TrackView(Context context) {
		super(context);
		init(context);
	}
	public TrackView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context){
		this.context=context; 
		mPath=new Path();
		paint=new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(5);
		paint.setStyle(Style.STROKE);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawPath(mPath, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
//			sx=event.getX();
//			sy=event.getY();
//			mPath.reset();
//			mPath.moveTo(sx, sy);
//			mPath.quadTo(kx, ky, zx, zy);
//			
//			invalidate();
			break;

		default:
			break;
		}
		
		return super.onTouchEvent(event);
	}
	
	/**
	 * 设置贝塞尔曲线
	 * @param startX 开始点X坐标
	 * @param startY 开始点y坐标
	 * @param kX  控制点X坐标
	 * @param kY  控制点Y坐标
	 * @param stopX  结束点X坐标
	 * @param stopY  结束点Y坐标
	 */
	public void setPoint(float startX,float startY,float kX,float kY,float stopX,float stopY){
		this.sx=startX;
		this.sy=startY;
		this.kx=kX;
		this.ky=kY;
		this.zx=stopX;
		this.zy=stopY;
		mPath.reset();
		//贝塞尔曲线开始点
		mPath.moveTo(sx, sy);
		//贝塞尔曲线控制点、终点
		mPath.quadTo(kx, ky, zx, zy);
		postInvalidate();
	}
}
