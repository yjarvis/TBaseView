package com.jarvis.tbaseview.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.widget.AjustPositionImageView;
import com.jarvis.tbaseview.widget.TagsView;
import com.jarvis.tbaseviewlib.bitmap.BitmapCompress;
import com.jarvis.tbaseviewlib.data.CacheData;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

/**
 * 贴纸功能
 * Created by tansheng on 2017/9/14.
 */

public class _29TagsActivity extends TFragmentActivity {
    private FrameLayout frameLayout;
    private AjustPositionImageView originalImg;
    private TagsView tagsImg;
    private Button button;
    float downX, downY;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_tags);
        initView(true);
        setData();
    }

    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
        titleBackFragment = new TitleBackFragment().newInstance(titleName, null);
        addTitleFragment(titleBackFragment);

        frameLayout = (FrameLayout) findViewById(R.id.tags_frame_layout);
        originalImg = (AjustPositionImageView) findViewById(R.id.tags_original_img);
        originalImg.setImageResource(R.drawable.meinv2);
        originalImg.setmAdjustBean();
        tagsImg = (TagsView) findViewById(R.id.tags_tags_img);
        button = (Button) findViewById(R.id.tags_btn);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.meinv2);
        Bitmap bitmapres = BitmapFactory.decodeResource(getResources(),
                R.drawable.faxing);
//        tagsImg.setWaterMark(bitmap);
        tagsImg.setImageBitmap(bitmap,bitmapres);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(frameLayout.getWidth(),frameLayout.getHeight());
                tagsImg.setLayoutParams(params);
            }
        });
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0) {
        Bitmap bitmap=convertViewToBitmap(frameLayout);
        BitmapCompress.saveBitmap(bitmap, CacheData.getImagesCache(),"");
    }

    @Override
    public void setData() {
    }

    public Bitmap convertViewToBitmap(View view){
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        //利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);
        //把view中的内容绘制在画布上
        view.draw(canvas);
        return bitmap;
    }


    @Override
    public void requestData(boolean isShow) {

    }

    @Override
    public void showFragment(Fragment fragment) {

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX=event.getX();
//                downY=event.getY();
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                tagsImg.setX(event.getX());
//                tagsImg.setY(event.getY());
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//        return false;
//    }


    /**
     * 旋转
     *
     * @param angle
     * @param bitmap
     * @return
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }



}
