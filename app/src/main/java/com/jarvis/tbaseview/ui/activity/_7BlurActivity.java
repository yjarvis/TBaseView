package com.jarvis.tbaseview.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.BlurUtils;

/**
 * 高斯模糊效果
 *
 * @author tansheng
 */
public class _7BlurActivity extends TFragmentActivity implements OnClickListener {

    private TitleBackFragment titleBackFragment;

    private ImageView imgDefaultShow;// 展示原图
    private ImageView imgDefault;// 叠放在模糊图片下的原图
    private ImageView imgBlur;// 模糊后的图
    private BlurUtils blurUtils;// 模糊工具类

    private Bitmap bmpDefaul;// 原始图片资源
    private Bitmap bmpBlur;// 模糊后的图片资源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);
        blurUtils = new BlurUtils();
        initView(true);
        setData();
    }


    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
        titleBackFragment = new TitleBackFragment().newInstance(titleName, null);
        addTitleFragment(titleBackFragment);

        imgDefaultShow = (ImageView) findViewById(R.id.blur_default_show);
        imgDefault = (ImageView) findViewById(R.id.blur_default_img);
        imgBlur = (ImageView) findViewById(R.id.blur_new_img);

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void setData() {
        // 获取原始图片
        bmpDefaul = BitmapFactory.decodeResource(resources, R.drawable.meinv6);
        // 使用方法一，得到模糊的图片
        // bmpBlur=blurUtils.blurBitmap(context, bmpDefaul, 10);
        // 使用方法二，得到模糊的图片
        bmpBlur = blurUtils.blurBitmap(bmpDefaul, 20, false);

        imgDefaultShow.setImageBitmap(bmpDefaul);
        imgDefault.setImageBitmap(bmpDefaul);
        imgBlur.setImageBitmap(bmpBlur);

    }

    private float lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                float alphaDelt = (y - lastY) / 10000;
                System.out.println("alphaDelt:" + alphaDelt);
                float alpha = imgBlur.getAlpha() + alphaDelt;
                if (alpha > 1.0) {
                    alpha = 1.0f;
                } else if (alpha < 0.0) {
                    alpha = 0.0f;
                }
                imgBlur.setAlpha(alpha);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

    @Override
    public void requestData(boolean isShow) {
        // TODO Auto-generated method stub

    }

    @Override
    public void showFragment(Fragment fragment) {
        // TODO Auto-generated method stub

    }
}
