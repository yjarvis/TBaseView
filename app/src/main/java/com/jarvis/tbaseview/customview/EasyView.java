package com.jarvis.tbaseview.customview;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import com.jarvis.tbaseviewlib.utils.TUtils;

/**
 * 自定义控件基础
 * @author tansheng
 *
 */
public class EasyView extends View implements Runnable {
	private Context context;
	private Paint paint;//圆的画笔
	private Paint paintBall;//小球的画笔
	private float radiu=160;//圆的半径
	private float radiuBall=20;//球的半径
	private Thread thread;
	private boolean isPlay=true;//是否开始动画
	
	
	public EasyView(Context context) {
		super(context);
		init(context);
	}
	public EasyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context){
		this.context=context;
		thread=new Thread(this);
		paint=new Paint();
		paintBall=new Paint();
		//设置画笔颜色
		paint.setColor(Color.BLUE);
		//打开抗锯齿
		paint.setAntiAlias(true);
		/*设置画笔类型
		 * 画笔类型了3种：
		 * STROKE：描边
		 * FILL:填充
		 * FILL_AND_STROKE:填充并且描边
		 */
		paint.setStyle(Style.STROKE);
		/*
		 * 设置描边的宽度，单位是像素
		 */
		paint.setStrokeWidth(10);
		//设置画笔颜色
		paintBall.setColor(Color.RED);
		//打开抗锯齿
		paintBall.setAntiAlias(true);
		/*设置画笔类型
		 * 画笔类型了3种：
		 * STROKE：描边
		 * FILL:填充
		 * FILL_AND_STROKE:填充并且描边
		 */
		paintBall.setStyle(Style.FILL_AND_STROKE);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		/*
		 *画圆的四个参数：
		 *1：圆心的x坐标
		 *2：圆心的y坐标
		 *3：圆的半径
		 *4：画笔paint 
		 */
		canvas.drawCircle(TUtils.getScreenWidth((Activity)context)/2, TUtils.getScreenHeight((Activity)context)/2, radiu, paint);
		canvas.drawCircle(TUtils.getScreenWidth((Activity)context)/2, TUtils.getScreenHeight((Activity)context)/2, radiuBall, paintBall);
	}
	@Override
	public void run() {
		//通过一个死循环确保界面不断绘制
		while (isPlay) {
			try {
				if (radiu<=300) {
					radiu+=8;
					//重绘界面
					postInvalidate();
				}else {
					radiu=0;
				}
				//线程睡眠
				Thread.sleep(50);
				
			} catch (Exception e) {
				 e.printStackTrace(); 
			}
		}
	}
	
	/**
	 * 开始动画
	 */
	public void startPlay(){
		if (null==thread) {
			thread=new Thread(this);
		}
		if (!thread.isAlive()) {
			isPlay=true;
			thread.start();
		}
	}
	/**
	 * 停止动画
	 */
	public void stopPlay(){
		if (null!=thread) {
			isPlay=false;
			thread=null;
		}
	}
}
