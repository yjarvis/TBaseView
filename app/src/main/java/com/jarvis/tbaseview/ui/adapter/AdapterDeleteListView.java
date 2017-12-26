package com.jarvis.tbaseview.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.customview.THorizontalScrollView;
import com.jarvis.tbaseviewlib.utils.TUtils;

/**
 * 滑动删除的ListView的Adapter
 * 
 * @author tansheng
 * 
 */
public class AdapterDeleteListView extends BaseAdapter {

	private Context context;
	private LayoutParams params;
	/** 是否滑动过，true表示滑动过，false表示未滑动过 */
	private boolean isScroll = false;

	private float downX = 0;
	private float downY = 0;

	public AdapterDeleteListView(Context context) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.adapter_delete_listview, null);
			holderView.itemLayout = (LinearLayout) convertView.findViewById(R.id.adapter_delete_listview_item_layout);
			holderView.itemOtherLayout = (LinearLayout) convertView.findViewById(R.id.adapter_delete_listview_item2_layout);
			holderView.scrollView = (THorizontalScrollView) convertView.findViewById(R.id.adapter_delete_listview_scrollView);
			holderView.button = (Button) convertView.findViewById(R.id.adapter_delete_listview_btn);
			holderView.textView = (TextView) convertView.findViewById(R.id.adapter_delete_listview_item_Text);
			convertView.setTag(holderView);
		} else {
			holderView = (HolderView) convertView.getTag();
		}
		// 设置显示部分为屏幕宽度
		holderView.itemLayout.setLayoutParams(params);
		// 还原状态
		holderView.scrollView.fullScroll(ScrollView.FOCUS_LEFT);
//		holderView.scrollView.setAadapter(AdapterDeleteListView.this, holderView.itemOtherLayout);
		holderView.scrollView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					downX = event.getX();
					downY = event.getY();
					if (isScroll) {
						// 先还原所有状态，才能滑动
						isScroll = false;
						// 还原
						AdapterDeleteListView.this.notifyDataSetChanged();
					}

					break;
				case MotionEvent.ACTION_MOVE:
					holderView.scrollView.scrollTo((int) (downX - event.getX()), 0);
					break;
				case MotionEvent.ACTION_UP:
					if (Math.abs(downX - event.getX()) < 10) {
						// Item的点击事件
						if (listener != null) {
							listener.OnItemClickListener(position);
						}
						break;
					}

					// 滑动超过隐藏的控件一半的位置的时候展开全部
					if (downX - event.getX() > holderView.itemOtherLayout.getWidth() / 3) {
						holderView.scrollView.scrollTo(holderView.scrollView.getRight(), 0);
						isScroll = true;
					} else if (downX - event.getX() < holderView.itemOtherLayout.getWidth() / 3) {
						// 否则收回
						AdapterDeleteListView.this.notifyDataSetChanged();
						isScroll = false;
					} else {
						isScroll = false;
					}

					break;
				}
				return false;
			}
		});

		holderView.button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				TUtils.showToast(context, "你点击了" + position + "处的删除");
			}
		});

		holderView.textView.setText("测试位置 " + position);

		return convertView;
	}

	private class HolderView {
		private LinearLayout itemLayout;
		private LinearLayout itemOtherLayout;
		private THorizontalScrollView scrollView;
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
