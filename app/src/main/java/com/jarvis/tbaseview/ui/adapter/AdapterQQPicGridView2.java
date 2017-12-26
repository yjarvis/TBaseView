package com.jarvis.tbaseview.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseviewlib.utils.AlbumShow;
import com.jarvis.tbaseviewlib.utils.TUtils;

import java.util.ArrayList;

/**
 * QQ发表说说界面，添加图片的adapter
 * 
 * @author tansheng
 * 
 */
public class AdapterQQPicGridView2 extends BaseAdapter {

	private Context context;
	/** 最多可添加的图片数 */
	private final int MAX_NUMBER = 15;
	/** 动态改变图片的宽高，使图片大小适配屏幕 */
	private LayoutParams params;
	private ArrayList<String> list;
	private AlbumShow albumRes;
	public AdapterQQPicGridView2(Context context, ArrayList<String> list, int numColumns) {
		this.context=context;
		this.list=list;
		albumRes=new AlbumShow();
		//计算每列的宽度(屏幕宽-列间距*列数-父组件左右边距)
		params = new LayoutParams((TUtils.getScreenWidth((Activity)context)-TUtils.dipTopx(context, 10)*numColumns-TUtils.dipTopx(context, 40))/numColumns, (TUtils.getScreenWidth((Activity)context)-TUtils.dipTopx(context, 10)*numColumns-TUtils.dipTopx(context, 40))/numColumns);
	}

	@Override
	public int getCount() {
		return (list != null && list.size() < MAX_NUMBER) ? list.size() + 1 : MAX_NUMBER + 1;
	}

	@Override
	public String getItem(int position) {
		return "";
	}

	@Override
	public long getItemId(int arg0) {

		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		HolderView holderView = null;
		if (convertView == null) {
			holderView = new HolderView();
			convertView = LayoutInflater.from(context).inflate(R.layout.adapter_qqpic_gridview, null);
			holderView.icon = (ImageView) convertView.findViewById(R.id.qqPic_add);
			convertView.setTag(holderView);
		} else {
			holderView = (HolderView) convertView.getTag();
		}
		holderView.icon.setLayoutParams(params);
		if (position == getCount() - 1) {
			// 如果是最后一个则显示添加的图标
			holderView.icon.setImageResource(R.drawable.qq_addpic);
			if (position >= MAX_NUMBER) {
				holderView.icon.setVisibility(View.GONE);
			}
		} else {
			// 显示选择的图片
			albumRes.disPlayBitmap(context, holderView.icon, null, list.get(position));
		}
		return convertView;
	}

	private class HolderView {
		private ImageView icon;

	}

}
