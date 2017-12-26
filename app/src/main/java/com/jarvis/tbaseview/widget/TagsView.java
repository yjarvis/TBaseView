package com.jarvis.tbaseview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;


/**
 * 1.获取原始图片的长宽
 * 2.根据人脸坐标进行定位，并进行缩放拉伸
 *    在mFaceHander中有定位和拉伸实现
 * 3.根据显示算法，进行显示定位
 *    AjustPositionImageView中有实现
 * 4.返回bitmap并显示
 * 5.实现放大缩小移动
 * Created by tansheng on 2017/9/21.
 */
public class TagsView extends ImageView {
    private Bitmap oldBitmap;
    private Bitmap newBitmap;
    private Bitmap resBitmap;
    private Paint paint;
    private Matrix matrix;

    private int bitmapWidth;
    private int bitmapHeight;
    private PointF[] pointface =new PointF[3];
    private PointF[] pointres =new PointF[3];




    /**
     * 当前模式
     */
    private int current_mode;
    /**
     * 缩放模式
     */
    private final int ZOOM_SCALE = 1;
    /**
     * 移动模式
     */
    private final int ZOOM_TRANSTLATE = 2;
    /**
     * 当前图片的matrix
     */
    private Matrix matrix_current=new Matrix();
   
    /**
     * 开启缩放的阈值
     */
    private final float ZOOM_THRESHOLD = 10.0f;
    /**
     * 放大最大倍数
     */
    private final float SCALE_MAX = 5.0f;
    /**
     * 缩小最小倍数
     */
    private final float SCALE_MIN = 1.0f;
    /**
     * 开始缩放时两指的距离
     */
    private float before_point_distance = 0;
    /**
     * 缩放时两指的距离
     */
    private float after_point_distance = 0;
    /**
     * 两指的中间点
     */
    private PointF middle_point = new PointF(0, 0);
    /**
     * 缩放比例
     */
    private float scale = 1;


    public TagsView(Context context) {
        super(context);
        init(context);
    }

    public TagsView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TagsView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0f);
        paint.setColor(Color.BLUE);
        PointF pointA=new PointF();
        pointA.x=32.727272f;
        pointA.y=36.9f;
        PointF pointB=new PointF();
        pointB.x=4;
        pointB.y=2;
        PointF pointC=new PointF();
        pointC.x=3;
        pointC.y=4;
        pointface =new PointF[]{pointA,pointB,pointC};


        PointF pointA2=new PointF();
        pointA2.x=1;
        pointA2.y=3;
        PointF pointB2=new PointF();
        pointB2.x=2;
        pointB2.y=4;
        PointF pointC2=new PointF();
        pointC2.x=100;
        pointC2.y=300;
        pointres=new PointF[]{pointA2,pointB2,pointC2};
    }

    public void setImageBitmap(Bitmap originalBitmap,Bitmap resBitmap){
        oldBitmap=originalBitmap;
        this.resBitmap=resBitmap;
        bitmapWidth=originalBitmap.getWidth();
        bitmapHeight=originalBitmap.getHeight();

        getImageHandleMatrix(resBitmap,pointface,pointres,true);

        postInvalidate();


//        newBitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight,Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(newBitmap);
//        canvas.drawColor(Color.YELLOW);
//        canvas.drawLine(0,0,300,300,paint);
//        canvas.save();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawBitmap(newBitmap, 0, 0, null);

        if (matrix==null||resBitmap==null){
            return;
        }
        canvas.drawBitmap(newBitmap, matrix, paint);
    }




    public void getImageHandleMatrix(Bitmap bitmapRes, PointF[] facePoints, PointF[] partPoints, boolean stretch) {
//        Bitmap faceMap = m_Handlebmp;              //待处理的图片
        int faceMapWidth = bitmapWidth;      //脸部图片宽度
        int faceMapHeight = bitmapHeight;    //脸部图片高度
        int partMapWidth = bitmapRes.getWidth();      //素材图片宽度
        int partMapHeight = bitmapRes.getHeight();    //素材图片高度
        //相对坐标 -》绝对坐标
        PointF faceA = facePoints[0];
//        faceA.x = faceA.x * faceMapWidth / 100.f;
//        faceA.y = faceA.y * faceMapHeight / 100.f;
//        PointF faceB = facePoints[1];
//        faceB.x = faceB.x * faceMapWidth / 100.f;
//        faceB.y = faceB.y * faceMapHeight / 100.f;
//        PointF partA = partPoints[0];
//        partA.x = partA.x * partMapWidth / 100.f;
//        partA.y = partA.y * partMapHeight / 100.f;
//        PointF partB = partPoints[1];
//        partB.x = partB.x * partMapWidth / 100.f;
//        partB.y = partB.y * partMapHeight / 100.f;
        faceA.x = 144.0f;
        faceA.y = 271.0f;
        PointF faceB = facePoints[1];
        faceB.x = 262.0f;
        faceB.y = 244.99998f;
        PointF partA = partPoints[0];
        partA.x = 124.98f;
        partA.y = 336.0f;
        PointF partB = partPoints[1];
        partB.x = 469.98f;
        partB.y = 336.0f;
        //构建变换矩阵
         matrix = new Matrix();
        float xScale = Math.abs(faceA.x - faceB.x) / (Math.abs(partA.x - partB.x));   //x上缩放系数
        float yScale = xScale;
        PointF contour_chin = facePoints[2];
        contour_chin.y = contour_chin.y * faceMapHeight / 100.f;
        float FHFW = ((contour_chin.y - (faceA.y + faceB.y) / 2) * 1.0f) / ((faceA.x - faceB.x));
        //根据脸长拉伸头发素材
        if (stretch){
//            yScale = Math.abs(FHFW) / 0.76f;
            yScale =1.0481713f;
        } else{
            yScale = 1.0f;
        }
        // Log.d("scale2Y:", "yScale:" + yScale + " xScale:" + xScale + " FHFW:" + FHFW);
        matrix.postScale(xScale, xScale * yScale);
        //第二步rotate
        float xoffset = faceA.x - partA.x * xScale;
        float yoffset = faceA.y - partA.y * xScale * yScale;
        matrix.postTranslate(xoffset, yoffset);
//        //渲染到rrt上
//        int width =bitmapWidth;
//        int height =bitmapHeight;
//        BitmapFactory.Options bfoOptions = new BitmapFactory.Options();
//        bfoOptions.inScaled = false;
//        Paint paint = new Paint();
//        Canvas canvas = new Canvas(bitmapRes);
//        canvas.drawBitmap(bitmapRes, matrix, paint);

        newBitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
//        canvas.drawColor(Color.YELLOW);
//        canvas.drawBitmap(oldBitmap,new Matrix(),paint);
        canvas.drawBitmap(bitmapRes,matrix,paint);
        change();
//        canvas.drawLine(0,0,300,300,paint);
//        canvas.save();
    }

    private void change(){
            int avaterWidth =bitmapWidth;
            int avaterHight = bitmapHeight;

//            float contour_leftright = avaterWidth*(mAdjustBean.contour_right1.x-mAdjustBean.contour_left1.x);
            float contour_leftright = 11800.001f;
            float horizontal_scale = 48*bitmapWidth/contour_leftright;


            if(matrix==null) {
                matrix = new Matrix();
                setScaleType(ScaleType.MATRIX);
            }

            matrix.setScale(horizontal_scale,horizontal_scale);

//            float contour_center_x = horizontal_scale*mAdjustBean.nose_tip.x*avaterWidth/100;
//            float contour_center_y = horizontal_scale*mAdjustBean.nose_tip.y*avaterHight/100;
            float contour_center_x =411.66098f;
            float contour_center_y =519.05084f;

            matrix.postTranslate(bitmapWidth/2-contour_center_x,bitmapHeight*0.45f-contour_center_y);
            setImageMatrix(matrix);
    }












    float downX,downY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                downX=event.getX();
                downY=event.getY();
                current_mode=ZOOM_TRANSTLATE;
                //获得当前图片的matrix矩阵
                matrix_current.set(matrix);
                break;
            case MotionEvent.ACTION_POINTER_DOWN://两指的时候触发
                current_mode = ZOOM_SCALE;
                //计算开始缩放时两指的距离
                before_point_distance = getTwoPointsDistance(event);
                if (before_point_distance >= ZOOM_THRESHOLD) {
                    //计算两指之间的中心点，用于控制缩放中心
                    middle_point = getMiddlePoint(event);
                }
                //获得当前图片的matrix矩阵
                matrix_current.set(matrix);
                break;
            case MotionEvent.ACTION_MOVE:
                switch (current_mode){
                    case ZOOM_SCALE://放大缩小
                        //计算缩放后的两指间距离
                        after_point_distance = getTwoPointsDistance(event);
                        if (after_point_distance > ZOOM_THRESHOLD) {
                            //计算缩放比例
                            scale = after_point_distance / before_point_distance;
                            zoomToScale(scale);
                        }
                        break;
                    case ZOOM_TRANSTLATE://移动
                        float dx=event.getX()-downX;
                        float dy=event.getY()-downY;
//                        dx=checkDxBound(dx);
//                        dy=checkDyBound(dy);

                        zoomToTranslate(dx,dy);
                        break;
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                //还原模式
                current_mode = 0;
                //获得当前图片的matrix矩阵
                matrix_current.set(matrix);
                //判断是否是点击事件
                if (Math.sqrt((event.getX()-downX)*(event.getX()-downX)+(event.getY()-downY)*(event.getY()-downY))<10f){

                }else{
                    //控制放大缩小移动的边界值
                    reScale();
                }
                break;
        }
        //设置缩放后的图片矩阵
        invalidate();
        return true;
    }

    //计算两点之间的距离
    private float getTwoPointsDistance(MotionEvent event) {
        float disX = event.getX(1) - event.getX(0);
        float disY = event.getY(1) - event.getY(0);
        return (float) Math.sqrt(disX * disX + disY * disY);
    }

    //计算两点之间的中间点
    private PointF getMiddlePoint(MotionEvent event) {
        float midX = (event.getX(0) + event.getX(1)) / 2;
        float midY = (event.getY(0) + event.getY(1)) / 2;
        return new PointF(midX, midY);
    }

    /**
     * 放大缩小
     */
    private float zoomToScale(float scale){
        matrix.set(matrix_current);
        //计算缩放后图片的matrix矩阵
        matrix.postScale(scale, scale, middle_point.x, middle_point.y);
        return scale;
    }

    /**
     * 拖拽移动
     */
    private void zoomToTranslate(float dragX,float dragY){
        matrix.set(matrix_current);
        matrix.postTranslate(dragX, dragY);
    }

    /**
     * 还原缩放到最大倍数
     */
    private boolean reScale(){
        float[] values=new float[9];
        matrix.getValues(values);
        //超过最大倍数
        if (scale*values[Matrix.MSCALE_X] > SCALE_MAX){
            scale=SCALE_MAX/values[Matrix.MSCALE_X];
            zoomToScale(scale);
            invalidate();
            return reScale();
        }else if (scale*values[Matrix.MSCALE_X] < SCALE_MIN){
            //超过最小倍数
            scale=SCALE_MIN/values[Matrix.MSCALE_X];
            zoomToScale(scale);
            invalidate();
            return reScale();
        }
        return false;
    }

    /**
     * 重置移动边界，使图像移动后不会超出ImageView边界
     */
    private void reTranslate(){
        float width=getWidth();
    }

    /**
     *  和当前矩阵对比，检验dy，使图像移动后不会超出ImageView边界
     *  @param dy
     *  @return
     */
    private float checkDyBound(float dy) {
        float[] values=new float[9];
        matrix.getValues(values);
        float height=getHeight();
        if(bitmapHeight*values[Matrix.MSCALE_Y]<height)
            return 0;
        if(values[Matrix.MTRANS_Y]+dy>0)
            dy=-values[Matrix.MTRANS_Y];
        else if(values[Matrix.MTRANS_Y]+dy<-(bitmapHeight*values[Matrix.MSCALE_Y]-height))
            dy=-(bitmapHeight*values[Matrix.MSCALE_Y]-height)-values[Matrix.MTRANS_Y];
        return dy;
    }

    /**
     *和当前矩阵对比，检验dx，使图像移动后不会超出ImageView边界
     *  @param dx
     *  @return
     */
    private float checkDxBound(float dx) {
        float[] values=new float[9];
        matrix_current.getValues(values);
        float width=getWidth();
        if(bitmapWidth*values[Matrix.MSCALE_X]<width)
            return 0;
        if(values[Matrix.MTRANS_X]+dx>0)
            dx=-values[Matrix.MTRANS_X];
        else if(values[Matrix.MTRANS_X]+dx<-(bitmapWidth*values[Matrix.MSCALE_X]-width))
            dx=-(bitmapWidth*values[Matrix.MSCALE_X]-width)-values[Matrix.MTRANS_X];
        return dx;
    }



}
