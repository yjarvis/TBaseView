package com.jarvis.tbaseview.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;

import com.amnix.skinsmoothness.AmniXSkinSmooth;
import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.ui.adapter.AdapterQQPicGridView;
import com.jarvis.tbaseviewlib.bitmap.takephoto.TPhoto;
import com.jarvis.tbaseviewlib.bitmap.takephoto.TPhotoActivity;
import com.jarvis.tbaseviewlib.bitmap.takephoto.model.TResult;
import com.jarvis.tbaseviewlib.bitmap.takephoto.utils.CompressImp;
import com.jarvis.tbaseviewlib.bitmap.takephoto.utils.OptionConfig;
import com.jarvis.tbaseviewlib.data.CacheData;

import java.io.File;
import java.util.ArrayList;

/**
 * 搞仿QQ空间说说发表界面
 *
 * @author tansheng
 */
public class _11QQPictureActivity1 extends TPhotoActivity {
    private Context context;
    private GridView gridView;
    private EditText editText1;
    private AdapterQQPicGridView adapter;
    private TPhoto tPhoto;

    /**
     * 选中的图片数据
     */
    private ArrayList<String> picList = new ArrayList<String>();
    private ArrayList<String> picList2 = new ArrayList<String>();

    String path = "";
    private final AmniXSkinSmooth amniXSkinSmooth = AmniXSkinSmooth.getInstance();
    private Bitmap bitmap,processed;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_qq_picture);
        OptionConfig config=new OptionConfig();
        config.setHaveCache(true);
        tPhoto=getTPhoto();
        tPhoto.setCompressConfig(new CompressImp());
//        tPhoto.setOptionConfig(config);
        context=this;
        initView(true);
        setData();
    }


    public void initView(boolean isStatusBar) {
        gridView = (GridView) findViewById(R.id.qqPic_gridview);
        editText1 = (EditText) findViewById(R.id.edit1);
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
                    showPhotoDialog(gridView);
                } else {
                    // 否则进入大图模式
                }

            }
        });

    }

    public void setData() {
        adapter = new AdapterQQPicGridView(context, picList,5, 4);
        gridView.setAdapter(adapter);
    }

//    @Override
//    protected void onActivityResult(int request, int result, Intent data) {
//        switch (request) {
//            case Constrans.CODE_CHOICE_PHOTO:
//
//                picList.clear();
//                // 得到选择的图片路径
//                picList.addAll(PopWindowPhoto.getSelectData());
//                setData();
//                try {
//                    for (int j = 0; j < PopWindowPhoto.getSelectData().size(); j++) {
//                        BitmapCompress.CompressImageWightAndSize(PopWindowPhoto.getSelectData().get(j), 200);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//
//            case Constrans.CODE_CHOICE_CAMERA:
//                path = PopWindowPhoto.getPath();
//                if (PopWindowPhoto.isHaveFile(path)) {
//                    // 放入拍照生成的图片路径
//                    PopWindowPhoto.putSelectData(path, null);
//                    picList.clear();
//                    picList.addAll(PopWindowPhoto.getSelectData());
//                    setData();
//                }
//
////			 try {
////			 //这个是压缩图片
////			 Bitmap bitmap=BitmapCompress.CompressImageSizeShow(path);
////			 img.setImageBitmap(bitmap);
////			 //这个是保存压缩后的图片
////			 BitmapCompress.saveBitmap(bitmap, CacheData.IMAGES_CACHE, "");
////			 } catch (IOException e) {
////			 e.printStackTrace();
////			 }
//
//                break;
//
//            default:
//                break;
//        }
//        super.onActivityResult(request, result, data);
//    }

    @Override
    public void onPhoto(int flag, int requestCode) {
        tPhoto.onPickMuliple(5);
    }

    @Override
    public void onCamera(int flag, int requestCode) {
        File file=new File(CacheData.getImagesCache()+"test.jpg");
        tPhoto.onPickCamera(file);
    }

    @Override
    public void onSuccess(TResult result) {
        super.onSuccess(result);
        picList.clear();
        for (int i = 0; i < result.getImages().size(); i++) {
            // 得到选择的图片路径
            picList.add(result.getImages().get(i).getImagePath());
        }
        adapter.notifyDataSetChanged();
    }

}
