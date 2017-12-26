package com.jarvis.tbaseview.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.ui.adapter.AdapterQQPicGridView;
import com.jarvis.tbaseviewlib.bitmap.BitmapCompress;
import com.jarvis.tbaseviewlib.constrans.Constrans;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.AlbumHelpSingle;
import com.jarvis.tbaseviewlib.widget.PopWindowPhoto;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 搞仿QQ空间说说发表界面
 *
 * @author tansheng
 */
public class _11QQPictureActivity2 extends TFragmentActivity {

    private TitleBackFragment titleBackFragment;
    private GridView gridView;
    private AdapterQQPicGridView adapter;

    private PopWindowPhoto popWindowPhoto;
    /**
     * 选中的图片数据
     */
    private ArrayList<String> picList = new ArrayList<String>();
    private ArrayList<String> picList2 = new ArrayList<String>();

    String path = "";

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_qq_picture);

        initView(true);
        setData();
    }


    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
        titleBackFragment = new TitleBackFragment().newInstance(titleName, "");
        titleBackFragment.setTitleOnClikListener(new TitleBackFragment.TitleOnClikListener() {
            @Override
            public void onClikRight() {

            }

            @Override
            public void onClikMiddle() {

            }

            @Override
            public void onClikLeft() {
                // 清空选择的图片数据
                AlbumHelpSingle.removeAllData();
            }
        });

        addTitleFragment(titleBackFragment);
        gridView = (GridView) findViewById(R.id.qqPic_gridview);
        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if (position == adapter.getCount() - 1) {
                    // //保存1、2的数据
                    // AlbumHelper.getHelper().saveSelectData(1);
                    // AlbumHelper.getHelper().saveSelectData(2);
                    // //清空数据
                    // AlbumHelper.removeAllData();
                    // //恢复1的数据
                    // AlbumHelper.getHelper().restoreSelectData(picList2, 1);

                    // 如果点击的最后一个位置，那么就弹窗选择框
                    popWindowPhoto.showAsDropDown(gridView, 1);
                } else {
                    // 否则进入大图模式
                }

            }
        });
        popWindowPhoto = new PopWindowPhoto(context, null);

    }

    @Override
    public void setData() {
//        adapter = new AdapterQQPicGridView(context, picList, 4);
//        gridView.setAdapter(adapter);
    }

    @Override
    public void onClick(View arg0) {
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
    protected void onActivityResult(int request, int result, Intent data) {
        switch (request) {
            case Constrans.CODE_CHOICE_PHOTO:

                picList.clear();
                // 得到选择的图片路径
                picList.addAll(PopWindowPhoto.getSelectData());
                setData();
                try {
                    for (int j = 0; j < PopWindowPhoto.getSelectData().size(); j++) {
                        BitmapCompress.CompressImageWightAndSize(PopWindowPhoto.getSelectData().get(j), 200);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case Constrans.CODE_CHOICE_CAMERA:
                path = PopWindowPhoto.getPath();
                if (PopWindowPhoto.isHaveFile(path)) {
                    // 放入拍照生成的图片路径
                    PopWindowPhoto.putSelectData(path, null);
                    picList.clear();
                    picList.addAll(PopWindowPhoto.getSelectData());
                    setData();
                }

//			 try {
//			 //这个是压缩图片
//			 Bitmap bitmap=BitmapCompress.CompressImageSizeShow(path);
//			 img.setImageBitmap(bitmap);
//			 //这个是保存压缩后的图片
//			 BitmapCompress.saveBitmap(bitmap, CacheData.IMAGES_CACHE, "");
//			 } catch (IOException e) {
//			 e.printStackTrace();
//			 }

                break;

            default:
                break;
        }
        super.onActivityResult(request, result, data);
    }
}
