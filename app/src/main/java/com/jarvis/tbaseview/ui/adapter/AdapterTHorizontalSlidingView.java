package com.jarvis.tbaseview.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseviewlib.view.horizontalsliding.THorizontalSlidingView;
import com.jarvis.tbaseviewlib.view.horizontalsliding.THorizontalSlidingView.BaseAdapterHorizontalSlidingView;

public class AdapterTHorizontalSlidingView extends BaseAdapterHorizontalSlidingView {

	private Context context;
	private int[] iconPath;

	public AdapterTHorizontalSlidingView(Context context,int[] iconPath) {
		this.context = context;
		this.iconPath=iconPath;
	}

	@Override
	public int getShowCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return iconPath.length;
	}

	@Override
	public View getView(int position, View convertView, THorizontalSlidingView parent) {
		convertView = LayoutInflater.from(context).inflate(R.layout.adapter_horizontal_slidingview, null);
		ImageView icon = (ImageView) convertView.findViewById(R.id.adapter_horizontal_slidingview_icon);
		TextView text = (TextView) convertView.findViewById(R.id.adapter_horizontal_slidingview_text);
		
		icon.setImageResource(iconPath[position]);
		text.setText("位置："+position);
		

		return convertView;
	}

}
