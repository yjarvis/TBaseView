package com.jarvis.tbaseview.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.jarvis.tbaseviewlib.data.CacheData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 图片美颜
 * 以及
 * Paint的setColorFilter用法
 * @author tansheng
 */
public class TBitmapView extends View {
	private Context context;
	private Paint paint;
	private Bitmap bitmap;//原始图片
	private Bitmap newBitmap;// 变换后的图片
	private float redNumber = 1.0f;
	private float redNumberF = 1.0f;
	private float greenNumber = 1.0f;
	private float greenNumberF = 1.0f;
	private float blueNumber = 1.0f;
	private float blueNumberF = 1.0f;
	private float alphaNumber = 1.0f;
	/**图片宽高*/
	private int width, height;

	public float getRedNumber() {
		return redNumber;
	}

	public void setRedNumber(float redNumber) {
		this.redNumber = redNumber;
		initPaint();
	}

	public float getGreenNumber() {
		return greenNumber;
	}

	public void setGreenNumber(float greenNumber) {
		this.greenNumber = greenNumber;
		initPaint();
	}

	public float getBlueNumber() {
		return blueNumber;
	}

	public void setBlueNumber(float blueNumber) {
		this.blueNumber = blueNumber;
		initPaint();
	}

	public float getAlphaNumber() {
		return alphaNumber;
	}

	public void setAlphaNumber(float alphaNumber) {
		this.alphaNumber = alphaNumber;
		initPaint();
	}

	public float getRedNumberF() {
		return redNumberF;
	}

	public void setRedNumberF(float redNumberF) {
		this.redNumberF = redNumberF;
		initPaint();
	}

	public float getGreenNumberF() {
		return greenNumberF;
	}

	public void setGreenNumberF(float greenNumberF) {
		this.greenNumberF = greenNumberF;
		initPaint();
	}

	public float getBlueNumberF() {
		return blueNumberF;
	}

	public void setBlueNumberF(float blueNumberF) {
		this.blueNumberF = blueNumberF;
		initPaint();
	}

	public TBitmapView(Context context) {
		super(context);
		init(context);
	}

	public TBitmapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		this.context = context;
		paint = new Paint();
		paint.setColorFilter(new ColorMatrixColorFilter(new float[] { 
				redNumber, 0, 0, 0, redNumberF,
				0, greenNumber, 0, 0, greenNumberF, 
				0, 0, blueNumber, 0, blueNumberF, 
				0, 0, 0, alphaNumber, 0,
		}));
	}
	
	/**设置一张图片*/
	public void setBitmap(int res){
		bitmap = BitmapFactory.decodeResource(context.getResources(), res);
		width = bitmap.getWidth();
		height = bitmap.getHeight();
	}
	/**获取设置的图片Bitmap*/
	public Bitmap getBitmap(){
		return bitmap;
	}

	private void initPaint() {
		paint.reset();
		paint.setColorFilter(new ColorMatrixColorFilter(new float[] { 
				redNumber, 0, 0, 0, redNumberF,
				0, greenNumber, 0, 0, greenNumberF, 
				0, 0, blueNumber, 0, blueNumberF, 
				0, 0, 0, alphaNumber, 0,

		}));
		postInvalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (newBitmap!=null) {
			canvas.drawBitmap(newBitmap, 0, 0, paint);
		}else if (bitmap!=null) {
			canvas.drawBitmap(bitmap, 0, 0, paint);
		}

	}

	/** 保存新图片，如果使用效果，就保存原图片 */
	public void saveBitmap() {
		File file = new File(CacheData.getImagesCache(), System.currentTimeMillis()+".png");
		try {
			FileOutputStream stream = new FileOutputStream(file);
			if (newBitmap!=null) {
				newBitmap.compress(CompressFormat.PNG, 100, stream);
			}else if (bitmap!=null) {
				bitmap.compress(CompressFormat.PNG, 100, stream);
			}
			stream.flush();
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 设置原图*/
	public void setReset(){
		newBitmap=null;
		postInvalidate();
	}

	/** 生成负片效果 */
	public Bitmap setNegativeEffect(Bitmap bitmap) {
		
		if (bitmap==null) {
			return null;
		}
		newBitmap=null;
		// 新创建一张图片
		Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		// 原图片所有像素长度
		int[] oldPixels = new int[bitmap.getWidth() * bitmap.getHeight()];
		// 新图片所有像素长度
		int[] newPixels = new int[bitmap.getWidth() * bitmap.getHeight()];
		// 获取原图片各点的像素
		bitmap.getPixels(oldPixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

		for (int i = 0; i < oldPixels.length; i++) {
			//获取各点的RGB值
			int color = oldPixels[i];
//			int pixelsA = Color.alpha(color);
			int pixelsR = Color.red(color);
			int pixelsG = Color.green(color);
			int pixelsB = Color.blue(color);

			// 反向(负片效果)
			pixelsR = 255 - pixelsR;
			pixelsG = 255 - pixelsG;
			pixelsB = 255 - pixelsB;

			// 确保反向后的值不超过255，并且不低于0(没什么效果)
			if (pixelsR > 255) {
				pixelsR = 255;
			} else if (pixelsR < 0) {
				pixelsR = 0;
			}

			if (pixelsG > 255) {
				pixelsG = 255;
			} else if (pixelsG < 0) {
				pixelsG = 0;
			}
			if (pixelsB > 255) {
				pixelsB = 255;
			} else if (pixelsB < 0) {
				pixelsB = 0;
			}
			// 根据反向后的值生成新的像素
			newPixels[i] = Color.rgb(pixelsR, pixelsG, pixelsB);
		}
		// 根据新的像素生成图片
		newBitmap.setPixels(newPixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
		this.newBitmap=newBitmap;
		postInvalidate();
		return newBitmap;
	}
	
	
    /** 
     * 怀旧效果
     * @param bmp 
     * @return  返回新的bitmap
     */  
    public Bitmap setOldRemeber(Bitmap bmp)  
    {  
    	if (bmp==null) {
			return null;
		}
    	
    	newBitmap=null;
        int width = bmp.getWidth();  
        int height = bmp.getHeight();  
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);  
        int pixColor = 0;  
        int pixR = 0;  
        int pixG = 0;  
        int pixB = 0;
        
        int newR = 0;  
        int newG = 0;  
        int newB = 0;  
        int[] pixels = new int[width * height];  
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);  
        for (int i = 0; i < pixels.length; i++)  
        {  
                pixColor = pixels[i];  
                pixR = Color.red(pixColor);  
                pixG = Color.green(pixColor);  
                pixB = Color.blue(pixColor);  
                //转换
                newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);  
                newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);  
                newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);  
                int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);  
                pixels[i] = newColor;  
        }  
          
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);  
        this.newBitmap=bitmap;
        postInvalidate();
        return bitmap;  
    }  
    /** 
     * 秋色效果
     * @param bmp 
     * @return  返回新的bitmap
     */  
    public Bitmap setAutumn(Bitmap bmp)  
    {  
    	if (bmp==null) {
    		return null;
    	}
    	
    	newBitmap=null;
    	int width = bmp.getWidth();  
    	int height = bmp.getHeight();  
    	Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);  
    	int pixColor = 0;  
    	int pixR = 0;  
    	int pixG = 0;  
    	int pixB = 0;
    	
    	int newR = 0;  
    	int newG = 0;  
    	int newB = 0;  
    	int[] pixels = new int[width * height];  
    	bmp.getPixels(pixels, 0, width, 0, 0, width, height);  
    	for (int i = 0; i < pixels.length; i++)  
    	{  
    		pixColor = pixels[i];  
    		pixR = Color.red(pixColor);  
    		pixG = Color.green(pixColor);  
    		pixB = Color.blue(pixColor);  
    		//转换
    		newR = (int) (0.88 * pixR +0.1*pixG+88);  
    		newG = (int) (0.8 * pixG + 77);  
    		newB = (int) (0.86 * pixB+73);  
    		int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);  
    		pixels[i] = newColor;  
    	}  
    	
    	bitmap.setPixels(pixels, 0, width, 0, 0, width, height);  
    	this.newBitmap=bitmap;
    	postInvalidate();
    	return bitmap;  
    }  
	
	
	

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//获取测量的宽高
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		//获取测量模式
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		//自定义view的宽高根据测量模式判断
	    setMeasuredDimension((modeWidth==MeasureSpec.EXACTLY)?sizeWidth:width, (modeHeight==MeasureSpec.EXACTLY)?sizeHeight:height); 
	}
}
