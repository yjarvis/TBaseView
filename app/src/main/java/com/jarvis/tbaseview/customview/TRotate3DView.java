package com.jarvis.tbaseview.customview;

import com.jarvis.tbaseview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 旋转木马3D+倒影效果
 * @author tansheng
 *
 */
public class TRotate3DView extends View {
	private Context context;
	private Paint paint=new Paint();
	private Matrix matrix=new Matrix(); 
	private Bitmap bitmap1;
	private Bitmap bitmap2;
	private Bitmap bitmap3;
	private Bitmap bitmap4;
	private Bitmap bitmap5;
	private Camera camera=new Camera();
	/**Y轴旋转角度*/
	private float angleY=0;
	
	private int moveX=100;
	

	public TRotate3DView(Context context) {
		super(context);
		init(context);
	}
	public TRotate3DView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public TRotate3DView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}
	
	private void init(Context context){
		this.context=context;
		bitmap1=BitmapFactory.decodeResource(getResources(), R.drawable.meinv1);
		bitmap2=BitmapFactory.decodeResource(getResources(), R.drawable.meinv2);
//		bitmap3=BitmapFactory.decodeResource(getResources(), R.drawable.meinv3);
		bitmap4=BitmapFactory.decodeResource(getResources(), R.drawable.meinv4);
		bitmap5=BitmapFactory.decodeResource(getResources(), R.drawable.meinv5);
		

	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//绘制原图
		canvas.drawBitmap(bitmap1, 0, 0, paint);
		//生成反转图
		Bitmap newBitmap=RefBitmap(bitmap1);
		//绘制反转图
		canvas.drawBitmap(newBitmap, 0,bitmap1.getHeight() ,paint);
		//绘制阴影遮罩
		canvas.drawRect(0, bitmap1.getHeight(), bitmap1.getWidth(),  bitmap1.getHeight()+newBitmap.getHeight(), drawShadow(bitmap1, canvas));
	
		if (bitmap3!=null) {
			canvas.drawBitmap(bitmap3, moveX,bitmap1.getHeight()+30,paint);
		}
	}
	
	/**实现图片的反转*/
	private Bitmap RefBitmap(Bitmap bitmap){
		if (bitmap==null) {
			return null;
		}
		 
		Matrix matrix=new Matrix();
		matrix.setScale(1, -1);
		Bitmap newBitmap=Bitmap.createBitmap(bitmap, 0, bitmap.getHeight()-100, bitmap.getWidth(), 100, matrix, true);
		
		return newBitmap;
	} 
	/**实现阴影遮罩层*/
	private Paint drawShadow(Bitmap bitmap,Canvas canvas){
		Shader shader=new LinearGradient(0, bitmap.getHeight(), 0, bitmap.getHeight()+100, Color.TRANSPARENT, Color.BLUE, TileMode.CLAMP);
		Paint paint=new Paint();
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		return paint;
	}
	
	/**生成旋转角度后的图片*/
	private Bitmap setRotate(Bitmap bitmap,float angleY){
		Matrix matrix=new Matrix();
		Camera camera=new Camera();
		camera.save();
		if (angleY<90) {
			camera.translate(0, 0, angleY*5);
		}
		camera.rotateY(angleY);
		camera.getMatrix(matrix);
		//按照图片的中心点旋转
		matrix.preTranslate(-bitmap.getWidth()/2,-bitmap.getHeight()/2);
		matrix.postTranslate(bitmap.getWidth()/2, bitmap.getHeight()/2);
		camera.restore();
		Bitmap newBitmap=null;
		try {
			 newBitmap=Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		} catch (Exception e) {
			newBitmap=bitmap;
		}
		return newBitmap;
	}
	
	
	float downX=0;
	float downY=0;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX=event.getX();
			downY=event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			
			if (event.getX()-downX>20) {
				//向右
				float angleY=calculateAngle(downX, event.getX());
				bitmap3=setRotate(bitmap4, angleY);
				
				System.out.println("downX:"+downX+"      "+event.getX());
//				downX=event.getX();
			}else if(event.getX()-downX<-20){
				//向左
			}
			moveX=(int) event.getX()*2;
			
			postInvalidate();
			
			break;
		case MotionEvent.ACTION_UP:
			
			break;

		default:
			break;
		}
		
		return true;
	}
	
	/**
	 * 计算偏移角度
	 */
	private float calculateAngle(float downX,float moveY){
		float angle=0;
		
		float temp=(moveY-downX)/5;
		if (temp==90) {
			temp=89;
		}else {
			angle=temp;
		}
		
		return angle;
	}
	
	

}
