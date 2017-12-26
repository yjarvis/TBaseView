package com.jarvis.tbaseview.ui.adapter;

import com.jarvis.tbaseview.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterMainListView extends BaseAdapter {

	private Context context;
	private String[] strs;
	
	public AdapterMainListView(Context context) {
		this.context=context;
		strs=context.getResources().getStringArray(R.array.main_listview_strs);
	}
	
	@Override
	public int getCount() {
		return strs.length;
	}

	@Override
	public String getItem(int position) {
		return strs[position].split("#")[0];
	}

	@Override
	public long getItemId(int arg0) {
		
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		HolderView holderView=null;
		if (convertView==null) {
			holderView=new HolderView();
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_mainlistview, null);
			holderView.item=(TextView) convertView.findViewById(R.id.adapter_main_item);
			holderView.content=(TextView) convertView.findViewById(R.id.adapter_main_content);
			convertView.setTag(holderView);
		}else {
			holderView=(HolderView) convertView.getTag();
		}
		holderView.item.setText(position+1+". "+strs[position].split("#")[0]);
		holderView.content.setText(strs[position].split("#")[1]);
		
		return convertView;
	}

	private class HolderView{
		private TextView item;
		private TextView content;
		
	}
	
}
