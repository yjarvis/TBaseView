package com.jarvis.tbaseview.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.customview.FaceView;
import com.jarvis.tbaseviewlib.constrans.Constrans;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.AlbumHelpSingle;
import com.jarvis.tbaseviewlib.widget.PopWindowPhoto;

public class _22FaceActivity extends TFragmentActivity {

    private TitleBackFragment titleBackFragment;
    private PopWindowPhoto popWindowPhoto;
    private FaceView imageView;
    /**
     * 原始图片
     */
    private Bitmap bitmapOld;
    /**
     * 转化成可识别的图片格式(RGB_565)后的图片
     */
    private Bitmap bitmapFace;
    /**
     * 人脸识别工具
     */
    private FaceDetector faceDet;
    /**
     * 可识别的最大人脸数
     */
    private int MAX_FACE = 6;
    /**
     * 识别的人脸数据
     */
    private Face[] faceList;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_face);
        super.onCreate(arg0);
        initView(true);
        setData();
    }


    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
        titleBackFragment = new TitleBackFragment().newInstance(titleName, "");
        addTitleFragment(titleBackFragment);
        popWindowPhoto = new PopWindowPhoto(context, null);
        AlbumHelpSingle.removeAllData();

        imageView = (FaceView) findViewById(R.id.face_imageview);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        popWindowPhoto.showAsDropDown(imageView, 0);
    }

    @Override
    public void setData() {

    }

    /**
     * 配置人脸识别参数
     *
     * @author tansheng QQ:717549357
     * @date 2016-1-11 上午11:37:24
     */
    private void setFace() {
        if (PopWindowPhoto.getSelectData().size() == 0) {
            return;
        }

        // 获得选择的图片
        bitmapOld = BitmapFactory.decodeFile(PopWindowPhoto.getSelectData().get(0));
        // 转化成图像识别的图片格式RGB_565
        bitmapFace = bitmapOld.copy(Config.RGB_565, true);
        /*
         * 创建人脸识别FaceDetecor的实例 参数：前两个参数为图片的宽高，最后一个参数为搜索人脸的最大个数
		 */
        faceDet = new FaceDetector(bitmapFace.getWidth(), bitmapFace.getHeight(), MAX_FACE);
        // 创建人脸数据存储数组
        faceList = new Face[MAX_FACE];
        // 通过findFaces函数获得人脸数据并存储在facelist中,并返回识别的人脸个数
        faceDet.findFaces(bitmapFace, faceList);

        imageView.setDispalyPoint(faceList);
        imageView.setImageBitmap(bitmapFace);
    }

    @Override
    public void requestData(boolean isShow) {
        // TODO Auto-generated method stub

    }

    @Override
    public void showFragment(Fragment fragment) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constrans.CODE_CHOICE_PHOTO:// 相册
                setFace();
                break;
            case Constrans.CODE_CHOICE_CAMERA:// 相机
                String path = PopWindowPhoto.getPath();
                if (PopWindowPhoto.isHaveFile(path)) {
                    // 放入拍照生成的图片路径
                    PopWindowPhoto.putSelectData(path, null);
                    setFace();
                }
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
