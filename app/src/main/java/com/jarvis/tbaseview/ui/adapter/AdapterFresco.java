package com.jarvis.tbaseview.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.support.fresco.config.ConfigConstants;

import java.util.ArrayList;

public class AdapterFresco extends BaseAdapter {

	private Context context;
	private ArrayList<String> list;
	
	
	
	public AdapterFresco(Context context) {
		this.context=context;
		addUrl();
	}
	
	@Override
	public int getCount() {
		return list.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holderView=null;
		if (convertView==null) {
			holderView=new HolderView();
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_fresco, null);
			holderView.item=(SimpleDraweeView) convertView.findViewById(R.id.adapter_fresco_icon);
			convertView.setTag(holderView);
		}else {
			holderView=(HolderView) convertView.getTag();
		}
		
		ImageRequest imageRequest= ConfigConstants.getImageRequest(holderView.item, list.get(position));
		DraweeController controller=ConfigConstants.getDraweeController(imageRequest, holderView.item);
		holderView.item.setController(controller);
//		 holderView.item.setImageURI(Uri.parse(list.get(position)));
		
		return convertView;
	}

	private class HolderView{
		private SimpleDraweeView item;
	}
	
	private void addUrl(){
		list=new ArrayList<String>();
		list.add("http://img32.mtime.cn/up/2013/07/20/142428.27146212_500.jpg");
		list.add("http://g.hiphotos.baidu.com/baike/w%3D268/sign=66d17ed667380cd7e61ea5eb9945ad14/e61190ef76c6a7ef18b42940fffaaf51f2de66c2.jpg");
		list.add("http://s1.dwstatic.com/group1/M00/B7/A5/9e17c82bae2fc22df427b09ae317eaaa.gif");
		list.add("http://img32.mtime.cn/up/2013/07/20/142420.11265268_500.jpg");
		list.add("http://img32.mtime.cn/up/2013/07/20/142352.84233298_500.jpg");
		list.add("http://img32.mtime.cn/up/2013/07/20/142234.22690934_500.jpg");
		list.add("http://img32.mtime.cn/up/2013/07/20/142140.58842929_500.jpg");
		list.add("http://img32.mtime.cn/up/2013/07/20/142204.46977964_500.jpg");
		list.add("http://img32.mtime.cn/up/2013/07/20/142406.96541771_500.jpg");
		list.add("http://img32.mtime.cn/up/2013/07/20/142315.72377310_500.jpg");
		list.add("http://img32.mtime.cn/up/2013/07/20/142303.81804449_500.jpg");
		list.add("http://img32.mtime.cn/up/2013/07/20/142251.40035406_500.jpg");
		list.add("http://img32.mtime.cn/up/2013/07/20/142329.63833494_500.jpg");
		
		list.add("http://i.imgur.com/oxd50YX.jpg");
		list.add("http://i.imgur.com/lbCh3Fb.jpg");
		list.add("http://i.imgur.com/0dqdq3m.jpg");
		list.add("http://i.imgur.com/QLli9TA.jpg");
		list.add("http://i.imgur.com/umvz87K.png");
		list.add("http://i.imgur.com/J1WSoc6.png");
		list.add("http://i.imgur.com/oRKydEF.jpg");
		list.add("http://i.imgur.com/Vahvfem.jpg");
		list.add("http://i.imgur.com/KYrt0ui.jpg");
		list.add("http://i.imgur.com/OCsSN7k.jpg");
		list.add("http://i.imgur.com/prC3c7n.jpg");
		list.add("http://i.imgur.com/9pudjYz.jpg");
		list.add("http://i.imgur.com/KXqCHZC.jpg");
		list.add("http://i.imgur.com/jSE47Tc.jpg");
		list.add("http://i.imgur.com/1iIypoP.jpg");
		
	}
	
	
}
