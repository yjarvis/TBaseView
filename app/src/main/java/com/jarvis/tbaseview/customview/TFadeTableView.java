package com.jarvis.tbaseview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.jarvis.tbaseview.R;

/***
 * 滑动时淡入淡出的导航栏
 * 
 * @author tansheng
 * 
 */
public class TFadeTableView extends View {

	private Context mContext;
	private Paint paintText;
	/**文字*/
	private String mTextStr;
	/**文字大小*/
	private int mTextSize=25;
	/**绘制图片的位置*/
	private Rect mIconRect;
	/**剩下的可绘制文本的区域*/
	private Rect mTextRect;
	/**设置的资源图片*/
	private Bitmap mIconBitmap;
	/**创建一个渐变背景图片*/
	private Bitmap mBgBitmap;
	/**绘制创建的背景图片*/
	private Canvas mBgCanvas;
	private final int DEFAULT_COLOR=0xFF38383A;
	/**渐变背景颜色*/
	private int mChangeColor=DEFAULT_COLOR;
	/**默认文字颜色*/
	private int mTextColor=DEFAULT_COLOR;
	/**渐变的透明度*/
	private float iconAlpha=0;
	/**控件宽*/
	private int mWidth;
	/**控件高*/
	private int mHeight;
	
	private Paint paintTextDefault;
	private Paint mBgPaint;

	public TFadeTableView(Context context) {
		super(context);
	}

	public TFadeTableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initParams(attrs);
		init(context);
	}


	private void init(Context context) {
		mContext = context;
		paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintText.setTextSize(mTextSize);
		//以中间为绘制点
		paintText.setTextAlign(Align.CENTER);
		
		paintTextDefault=new Paint(Paint.ANTI_ALIAS_FLAG);
		paintTextDefault.setTextSize(mTextSize);
		paintTextDefault.setColor(mTextColor);
		paintTextDefault.setTextAlign(Align.CENTER);
		
	}

	private void initParams(AttributeSet attrs) {
		TypedArray typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.TableView_Style);

		for (int i = 0; i < typeArray.getIndexCount(); i++) {
			int attr = typeArray.getIndex(i);
			switch (attr) {
			case R.styleable.TableView_Style_table_icon://获取xml中的图片资源
				BitmapDrawable bitmapDrawable=(BitmapDrawable) typeArray.getDrawable(attr);
				mIconBitmap=bitmapDrawable.getBitmap();
				break;
			case R.styleable.TableView_Style_table_text://获取xml中的文字
				mTextStr=typeArray.getString(attr);
				break;
			case R.styleable.TableView_Style_table_text_size://获取xml中的文字大小
				mTextSize=typeArray.getIndex(attr);
				break;
			case R.styleable.TableView_Style_table_text_color://获取xml中的文字颜色
				mTextColor=typeArray.getColor(attr, DEFAULT_COLOR);
				break;
			case R.styleable.TableView_Style_table_change_color://获取xml中的渐变的颜色
				mChangeColor=typeArray.getColor(attr, DEFAULT_COLOR);
				break;
			}
		}
		typeArray.recycle();
	}

	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		//绘制文字的大小
		mTextRect=new Rect(0,0,w,(int)(h*0.3f));
		// 绘制图片的区域
		int bitmapWidth = (int) (h * 0.6f);
		int Left = (w - bitmapWidth) / 2;
		int Top = (h - bitmapWidth-mTextRect.height()) / 2;
		int Right = Left + bitmapWidth;
		int Bottom = Top + bitmapWidth;
		//获得控件的宽高
		mWidth = w;
		mHeight = h;
		//得到需要创建的图片背景的大小
		mIconRect=new Rect(Left, Top, Right, Bottom);
		System.out.println("onSizeChanged");
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//1.清空画板
		canvasClear(canvas);
		//2.画图片
		canvasBitmap(canvas);
		//3.画文字
		canvasText(canvas);
		
	}
	

	/**
	 * 清空画板
	 *@author tansheng
	 * @param canvas
	 */
	private void canvasClear(Canvas canvas){
		if (mIconBitmap==null) {
			return;
		}
		canvas.drawBitmap(mIconBitmap, null, mIconRect, null);
	}
	
	/**
	 * 画文字
	 *@author tansheng
	 * @param canvas
	 */
	private void canvasText(Canvas canvas) {
		if (TextUtils.isEmpty(mTextStr)) {
			return;
		}
		// 计算透明度
		int alpha = (int) Math.ceil(255 * iconAlpha);
		/*
		 * 如果先setAlpha再setColor那么透明度将失效，原因如下：
		 * setColor中传的参数是包含了alpha值的。因此我们等于是先设置了alpha值，然后再setColor又设回了不透明。 当然就不会体现alpha值啦。 
		 */
		paintText.setColor(mChangeColor);
		paintText.setAlpha(alpha);
		paintTextDefault.setAlpha(255-alpha);
		//绘制默认颜色的文字
		canvas.drawText(mTextStr, mTextRect.centerX(), (mHeight-mIconRect.height())/2+mIconRect.height()+10, paintTextDefault);
				
		//绘制渐变颜色的文字
		canvas.drawText(mTextStr, mTextRect.centerX(), (mHeight-mIconRect.height())/2+mIconRect.height()+10, paintText);
	
	}
	
	/**
	 * 画图片
	 *@author tansheng
	 * @param canvas
	 */
	private void canvasBitmap(Canvas canvas){
		if (mIconBitmap==null) {
			return;
		}
		// 计算透明度
		int alpha = (int) Math.ceil(255 * iconAlpha);
		//1.画背景
		mBgPaint = new Paint();
		/*
		 * 如果先setAlpha再setColor那么透明度将失效，原因如下：
		 * setColor中传的参数是包含了alpha值的。因此我们等于是先设置了alpha值，然后再setColor又设回了不透明。 当然就不会体现alpha值啦。 
		 */
		mBgPaint.setColor(mChangeColor);
		mBgPaint.setAlpha(alpha);
		// 防锯齿
		mBgPaint.setAntiAlias(true);
		// 防抖动
		mBgPaint.setDither(true);
		// 创建一个背景图片
		mBgBitmap = Bitmap.createBitmap(mIconRect.width(), mIconRect.height(), Config.ARGB_8888);
		// 绘制背景
		mBgCanvas = new Canvas(mBgBitmap);
		mBgCanvas.drawRect(new Rect(0, 0, mIconRect.width(), mIconRect.height()), mBgPaint);
		
		//2.绘制自己设置的图片
		//设置绘制模式
		mBgPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		//设置透明度
		mBgPaint.setAlpha(255);
		//绘制自己定义的图片
		mBgCanvas.drawBitmap(mIconBitmap, null, new Rect(0,0,mIconRect.width(),mIconRect.height()), mBgPaint);
		
		//3.将绘制的图片绘制到界面上
		canvas.drawBitmap(mBgBitmap,null ,mIconRect,null);
		
	}

	public Bitmap getmIconBitmap() {
		return mIconBitmap;
	}

	public void setmIconBitmap(Bitmap mIconBitmap) {
		this.mIconBitmap = mIconBitmap;
	}

	public String getmTextStr() {
		return mTextStr;
	}

	public void setmTextStr(String mTextStr) {
		this.mTextStr = mTextStr;
	}

	public int getmTextSize() {
		return mTextSize;
	}

	public void setmTextSize(int mTextSize) {
		this.mTextSize = mTextSize;
	}

	public float getIconAlpha() {
		return iconAlpha;
	}
	/**
	 * 设置透明度
	 *@author tansheng
	 * @param iconAlpha  范围是0~1
	 */
	public void setIconAlpha(float iconAlpha) {
		this.iconAlpha = iconAlpha;
		invalidate();
	}
	
}
