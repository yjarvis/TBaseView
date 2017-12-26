package com.jarvis.tbaseview.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseviewlib.utils.TUtils;

/**
 * 滑动删除的ListView的Adapter
 * 
 * @author tansheng
 * 
 */
public class AdapterDeleteListView2 extends BaseAdapter {

	private Context context;
	private LayoutParams params;
	/** 是否滑动过，true表示滑动过，false表示未滑动过 */
	private boolean isScroll = false;

	private float downX = 0;
	private float downY = 0;

	public AdapterDeleteListView2(Context context) {
		this.context = context;
		// 设置显示部分为屏幕宽度
		params = new LayoutParams(TUtils.getScreenWidth((Activity) context), LayoutParams.WRAP_CONTENT);
	}

	@Override
	public int getCount() {
		return 1000;
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
	public View getView(final int position, View convertView, ViewGroup arg2) {
		final HolderView holderView;
		if (convertView == null) {
			holderView = new HolderView();
			convertView = LayoutInflater.from(context).inflate(R.layout.adapter_delete_listview2, null);
			holderView.itemLayout = (LinearLayout) convertView.findViewById(R.id.adapter_delete_listview_item_layout);
			holderView.itemOtherLayout = (LinearLayout) convertView.findViewById(R.id.adapter_delete_listview_item2_layout);
			holderView.button = (Button) convertView.findViewById(R.id.adapter_delete_listview_btn);
			holderView.textView = (TextView) convertView.findViewById(R.id.adapter_delete_listview_item_Text);
			convertView.setTag(holderView);
		} else {
			holderView = (HolderView) convertView.getTag();
		}

		holderView.textView.setText("测试位置 " + position);
		holderView.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TUtils.showToast(context,"位置："+position);
			}
		});

		return convertView;
	}

	private class HolderView {
		private LinearLayout itemLayout;
		private LinearLayout itemOtherLayout;
		private Button button;
		private TextView textView;

	}

	private OnItemClickCallBack listener;

	public interface OnItemClickCallBack {
		public void OnItemClickListener(int position);
	}

	public void setOnItemClickListener(OnItemClickCallBack listener) {
		this.listener = listener;
	}

}
