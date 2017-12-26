package com.jarvis.tbaseview.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.jarvis.tbaseview.ui.adapter.AdapterDeleteListView;


public class THorizontalScrollView extends HorizontalScrollView {

	private BaseAdapter adapter;
	private LinearLayout otherLayout;
	private AdapterDeleteListView.OnItemClickCallBack listener;
	private static boolean isScroll=false;
	private float downX = 0;
	private float downY = 0;
	
	public THorizontalScrollView(Context context) {
		super(context);
	}
	public THorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public THorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public void fling(int velocityX) {
		//禁止惯性滚动
//		super.fling(velocityX);
	}
	
	public void setAadapter(BaseAdapter adapter,LinearLayout otherLayout){
		this.adapter=adapter;
		this.otherLayout=otherLayout;
	}
	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			downX = event.getX();
//			downY = event.getY();
//			if (isScroll) {
//				// 先还原所有状态，才能滑动
//				isScroll = false;
//				// 还原
//				adapter.notifyDataSetChanged();
//				System.out.println("按下还原");
//			}
//
//			break;
//		case MotionEvent.ACTION_MOVE:
//			scrollTo((int) (downX - event.getX()), 0);
//			break;
//		case MotionEvent.ACTION_UP:
//			if (Math.abs(downX - event.getX()) < 10) {
//				// Item的点击事件
////				if (listener != null) {
////					listener.OnItemClickListener(position);
////				}
//				break;
//			}
//
//			// 滑动超过隐藏的控件一半的位置的时候展开全部
//			if (downX - event.getX() > otherLayout.getWidth() / 3) {
//				scrollTo(getRight(), 0);
//				isScroll = true;
//				System.out.println("松开"+isScroll);
//			} else if (downX - event.getX() < otherLayout.getWidth() / 3) {
//				// 否则收回
//				adapter.notifyDataSetChanged();
//				System.out.println("松开还原");
//				isScroll = false;
//			} else {
//				isScroll = false;
//			}
//
//			break;
//		}
//		
//		return true;
//	}
	
	
	
	
	
	
	
//	private void setOnTouchListener(OnTouchListener l,BaseAdapter adapter,OnItemClickCallBack listener){
//		this.adapter=adapter;
//		this.listener=listener;
//		setOnTouchListener(l);
//	}
	
}
