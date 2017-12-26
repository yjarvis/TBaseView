package com.jarvis.tbaseview.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.jarvis.tbaseviewlib.utils.TUtils;

/**
 * Paint的渲染Shader用法
 * @author tansheng
 *
 */
public class PaintView extends View {
	private Context context;
	private Paint paint;
	private Bitmap bitmap1;
	private int type = 0;// 选择效果
	private final int COLOR_RED=0xffff0000;//红色
	private final int COLOR_BLUE=0xff0000ff;//蓝色
	private final int COLOR_YELLOW=0xffffd700;//黄色
	private final int COLOR_GREEN=0xffb3ee3a;//绿色

	public void setType(int type) {
		this.type = type;
		postInvalidate();
	}

	public PaintView(Context context) {
		super(context);
		init(context);
	}

	public PaintView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PaintView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
		this.context=context;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.BLACK);
		//绘制
		choiceType(type, canvas);
	}

	/**
	 * 设置图片资源
	 * @param res  存放在drawable中的图片资源
	 */
	public void setBitmap(int res){
		bitmap1=BitmapFactory.decodeResource(getResources(), res);
	}
	
	
	
	/**
	 * 各种特效
	 * @param type
	 * @param canvas
	 */
	private void choiceType(int type, Canvas canvas) {
		paint=new Paint();
		switch (type) {
		case 0:// 线性渲染
			/* 双色渲染
			 * 参数1：起始点的x坐标 
			 * 参数2：起始点的y坐标 
			 * 参数3：终点的x坐标 
			 * 参数4：终点的y坐标 
			 * 参数5：起始颜色 
			 * 参数6：渐变颜色
			 * 参数7：渐变模式:MIRROR:在水平方向和垂直方向交替景象, 两个相邻图像间没有缝隙，从名称上看就像照镜子一样；
			 *  REPETA：在水平方向和垂直方向重复摆放,两个相邻图像间有缝隙缝隙；
			 *   CLAMP:边缘拉伸，即使用边缘的最后一个像素进行延展；
			 */
//			LinearGradient linearGradient = new LinearGradient(0, 0, 401, 500, COLOR_RED, COLOR_BLUE, TileMode.MIRROR);
			//多色渲染
			int[] colors={COLOR_BLUE,COLOR_YELLOW,COLOR_GREEN,COLOR_RED};
			//positions可以为空，位置表示蓝色从0开始到50%的位置，黄色从50%位置开始到60%的位置，绿色从60%的位置到90%的位置，红色从90%位置到100%
			float[] positions={0,0.5f,0.6f,0.9f};
			LinearGradient linearGradient = new LinearGradient(0, 0, TUtils.getScreenWidth((Activity)context), TUtils.getScreenHeight((Activity)context), colors, positions, TileMode.MIRROR);
			paint.setShader(linearGradient);
			//防抖动，使颜色平滑过渡
			paint.setDither(true);
			canvas.drawRect(0, 0, TUtils.getScreenWidth((Activity)context), TUtils.getScreenHeight((Activity)context), paint);
			break;
		case 1:// 环形渲染
			/*
			 * 参数1：圆心点的x坐标 
			 * 参数2：圆心点的y坐标 
			 * 参数3：半径 
			 * 参数4：起始颜色 
			 * 参数5：渐变颜色
			 * 参数6：渐变模式:MIRROR:在水平方向和垂直方向交替景象, 两个相邻图像间没有缝隙，从名称上看就像照镜子一样；
			 *  CLAMP:边缘拉伸，即使用边缘的最后一个像素进行延展； 
			 *  REPETA：在水平方向和垂直方向重复摆放,两个相邻图像间有缝隙缝隙；
			 */
			RadialGradient radialGradient = new RadialGradient(350, 640, 350, COLOR_RED, COLOR_BLUE, TileMode.MIRROR);
			paint.setShader(radialGradient);
			canvas.drawRect(0, 0,  TUtils.getScreenWidth((Activity)context), TUtils.getScreenHeight((Activity)context), paint);
			break;
		case 2:// 扫描渐变渲染/梯度渲染
			/*
			 * 参数1：圆心点的x坐标 
			 * 参数2：圆心点的y坐标 
			 * 参数3：半径 
			 * 参数4：起始颜色 
			 * 参数5：渐变颜色
			 * 参数6：渐变模式:MIRROR:在水平方向和垂直方向交替景象, 两个相邻图像间没有缝隙，从名称上看就像照镜子一样； CLAMP:
			 * 边缘拉伸，即使用边缘的最后一个像素进行延展； REPETA：在水平方向和垂直方向重复摆放,两个相邻图像间有缝隙缝隙；
			 */
			SweepGradient sweepGradient = new SweepGradient(500, 600, COLOR_RED, COLOR_BLUE);
			paint.setShader(sweepGradient);
			canvas.drawRect(0, 0,  TUtils.getScreenWidth((Activity)context), TUtils.getScreenHeight((Activity)context), paint);
			break;

		case 3:// 位图渲染
			if (bitmap1==null) {
				return;
			}
			BitmapShader bitmapShader = new BitmapShader(bitmap1, TileMode.CLAMP, TileMode.MIRROR);
			paint.setShader(bitmapShader);
			canvas.drawRect(0, 0, bitmap1.getWidth(), bitmap1.getHeight() + bitmap1.getHeight(), paint);
			break;
		case 4:// 组合渲染，可以和其他几个子类组合起来使用
			if (bitmap1==null) {
				return;
			}
			
			BitmapShader shaderA = new BitmapShader(bitmap1, TileMode.CLAMP, TileMode.MIRROR);
			LinearGradient shaderB = new LinearGradient(0, 0, 0, bitmap1.getHeight()+300, 0x00000000, 0xcc000000, TileMode.CLAMP);

			ComposeShader composeGradient = new ComposeShader(shaderA, shaderB, PorterDuff.Mode.SRC_OVER);
			paint.setShader(composeGradient);
			canvas.drawRect(0, 0, bitmap1.getWidth(), bitmap1.getHeight() + 300, paint);
			break;
			
		case 5://倒影效果
			if (bitmap1==null) {
				return;
			}
			//倒影与原图的间隔
			int space=5;
			//反转效果
			Matrix matrix=new Matrix();
			matrix.setScale(1, -1);
			//实现图片的反转
			Bitmap bitmapDown=Bitmap.createBitmap(bitmap1, 0,bitmap1.getHeight()/2, bitmap1.getWidth(), bitmap1.getHeight()/2, matrix, false);
			//绘制原图
			canvas.drawBitmap(bitmap1, 0, 0, new Paint());
			//绘制倒影图
			canvas.drawBitmap(bitmapDown, 0, bitmap1.getHeight()+space, paint);
			//绘制阴影遮罩
			LinearGradient shader = new LinearGradient(0, bitmap1.getHeight(), 0,bitmap1.getHeight()+bitmapDown.getHeight()+space, 0xcc000000, 0x00000000, TileMode.CLAMP);
			paint.setShader(shader);
			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
			canvas.drawRect(0, bitmap1.getHeight(), bitmap1.getWidth(),bitmap1.getHeight()+bitmapDown.getHeight()+space, paint);

		default:
			break;
		}
	}
	
	
	

}
