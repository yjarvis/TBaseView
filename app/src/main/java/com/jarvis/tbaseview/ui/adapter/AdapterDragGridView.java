package com.jarvis.tbaseview.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseviewlib.view.dragview.DragGridBaseAdapter;

/**
 * 拖动添加效果Adapter
 * 
 * @author tansheng
 * 
 */
public class AdapterDragGridView extends BaseAdapter implements DragGridBaseAdapter {

	private Context context;
	private List<String> list;
	private int mHidePosition = -1;

	public AdapterDragGridView(Context context, List<String> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public String getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int arg0) {

		return 0;
	}

	/**
	 * 由于复用convertView导致某些item消失了，所以这里不复用item，
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		convertView = LayoutInflater.from(context).inflate(R.layout.adapter_drag_gridview, null);
		TextView text = (TextView) convertView.findViewById(R.id.adapter_drag_gridview_text);
		if (position == mHidePosition) {
			text.setVisibility(View.INVISIBLE);
		}
		text.setText(list.get(position));
		return convertView;
	}
	
//	@Override
//	public View getView(int position, View convertView, ViewGroup arg2) {
//		HolderView holderView = null;
//		if (convertView == null) {
//			holderView = new HolderView();
//			convertView = LayoutInflater.from(context).inflate(R.layout.adapter_drag_gridview, null);
//			holderView.text = (TextView) convertView.findViewById(R.id.adapter_drag_gridview_text);
//			convertView.setTag(holderView);
//		} else {
//			holderView = (HolderView) convertView.getTag();
//		}
//		if (position==mHidePosition) {
//			holderView.text.setVisibility(View.INVISIBLE);
//		}else {
//			holderView.text.setVisibility(View.VISIBLE);
//		}
//		holderView.text.setText(list.get(position));
//		return convertView;
//	}
//
//	private class HolderView {
//		private TextView text;
//
//	}


	@Override
	public void reorderItems(int oldPosition, int newPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHideItem(int hidePosition) {
		this.mHidePosition = hidePosition;
		notifyDataSetInvalidated();
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return list;
	}

}
