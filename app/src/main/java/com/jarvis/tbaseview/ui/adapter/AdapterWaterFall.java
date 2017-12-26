package com.jarvis.tbaseview.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseviewlib.utils.TUtils;
import com.jarvis.tbaseviewlib.view.mullayout.TWaterFallBaseAdapter;

import java.util.ArrayList;

public class AdapterWaterFall extends TWaterFallBaseAdapter {

	private Context context;
	/**瀑布流列数*/
	private int columnNum;
	private ArrayList<Integer> data;

	public AdapterWaterFall(Context context, ArrayList<Integer> data,int columnNum) {
		this.context = context;
		this.columnNum = columnNum;
		this.data=data;
	}

	@Override
	public int getCount() {
		//设置显示总数
		return data.size();
	}

	@Override
	public int getColumnNum() {
		// 设置每行的列数，此处一定要设置，不能默认0
		return columnNum;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public View getView(View converView, final int position) {
		HolderView holderView = null;
		if (converView==null) {
			holderView=new HolderView();
			converView = LayoutInflater.from(context).inflate(R.layout.item_waterfall, null);
			holderView.icon=(ImageView) converView.findViewById(R.id.item_waterfall_img);
			converView.setTag(holderView);
		}else {
			holderView=(HolderView) converView.getTag();
		}
		holderView.icon.setImageResource(data.get(position));
		holderView.icon.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View arg0) {
				TUtils.showToast(context, "图片位置：" +position);
			}
		});
		return converView;
	}
	
	private class HolderView{
		ImageView icon;
		
	}


}
