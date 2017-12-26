package com.jarvis.tbaseview.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RectF;
import android.media.FaceDetector.Face;
import android.util.AttributeSet;
import android.widget.ImageView;

public class FaceView extends ImageView {
	private Context context;
	private Paint paint;
	private Bitmap bitmap;
	private Face[] faceList;
	
	public FaceView(Context context) {
		super(context);
		init(context);
	}
	public FaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public FaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context){
		this.context=context;
		paint=new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(3);
		paint.setStyle(Style.STROKE);
		
	}

	public void setDispalyPoint(Face[] faceList){
		this.faceList=faceList;
	}
	
	@Override
	public void setImageBitmap(Bitmap bm) {
		super.setImageBitmap(bm);
		bitmap=bm;
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (bitmap==null) {
			return;
		}
		if (faceList==null) {
			return;
		}
		
		
		
		for (int i = 0; i < faceList.length; i++) {
			if (faceList[i]!=null) {
			//计算每个脸的位置
			Face face=faceList[i];
			//得到两眼的中心点的位置
			PointF pf=new PointF();
			face.getMidPoint(pf);
			//绘制识别框的区域
			RectF r = new RectF();
            r.left = pf.x - face.eyesDistance() / 2;
            r.right = pf.x + face.eyesDistance() / 2;
            r.top = pf.y - face.eyesDistance() / 2;
            r.bottom = pf.y + face.eyesDistance() / 2;
			canvas.drawRect(r, paint);
			}
		}
		
		
	}
	

}
