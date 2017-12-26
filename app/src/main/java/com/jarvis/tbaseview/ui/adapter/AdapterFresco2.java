package com.jarvis.tbaseview.ui.adapter;


//public class AdapterFresco2 extends BaseAdapter {
//
//	private Context context;
//	private ArrayList<String> list;
//	
//	
//	
//	public AdapterFresco2(Context context) {
//		this.context=context;
//		addUrl();
//	}
//	
//	@Override
//	public int getCount() {
//		return list.size();
//	}
//
//	@Override
//	public String getItem(int position) {
//		return "";
//	}
//
//	@Override
//	public long getItemId(int arg0) {
//		
//		return 0;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		
//		SimpleDraweeView view = SimpleDraweeView.class.isInstance(convertView) ? (SimpleDraweeView) convertView : createView();
//		int size = calcDesiredSize(parent.getWidth(), parent.getHeight());
//		updateViewLayoutParams(view, size, size);
//
//		String uri =list.get(position);
////		view.initInstrumentation(uri, mPerfListener);
//		ImageRequest imageRequest = ConfigConstants.getImageRequest(view,uri);
//		DraweeController draweeController = ConfigConstants.getDraweeController(imageRequest, view);
//		view.setController(draweeController);
//		
//		return view;
//	}
//
//	private class HolderView{
//		private SimpleDraweeView item;
//	}
//	
//	private void addUrl(){
//		list=new ArrayList<String>();
//		list.add("http://img32.mtime.cn/up/2013/07/20/142428.27146212_500.jpg");
//		list.add("http://g.hiphotos.baidu.com/baike/w%3D268/sign=66d17ed667380cd7e61ea5eb9945ad14/e61190ef76c6a7ef18b42940fffaaf51f2de66c2.jpg");
//		list.add("http://s1.dwstatic.com/group1/M00/B7/A5/9e17c82bae2fc22df427b09ae317eaaa.gif");
//		list.add("http://img32.mtime.cn/up/2013/07/20/142420.11265268_500.jpg");
//		list.add("http://img32.mtime.cn/up/2013/07/20/142352.84233298_500.jpg");
//		list.add("http://img32.mtime.cn/up/2013/07/20/142234.22690934_500.jpg");
//		list.add("http://img32.mtime.cn/up/2013/07/20/142140.58842929_500.jpg");
//		list.add("http://img32.mtime.cn/up/2013/07/20/142204.46977964_500.jpg");
//		list.add("http://img32.mtime.cn/up/2013/07/20/142406.96541771_500.jpg");
//		list.add("http://img32.mtime.cn/up/2013/07/20/142315.72377310_500.jpg");
//		list.add("http://img32.mtime.cn/up/2013/07/20/142303.81804449_500.jpg");
//		list.add("http://img32.mtime.cn/up/2013/07/20/142251.40035406_500.jpg");
//		list.add("http://img32.mtime.cn/up/2013/07/20/142329.63833494_500.jpg");
//		
//		list.add("http://i.imgur.com/oxd50YX.jpg");
//		list.add("http://i.imgur.com/lbCh3Fb.jpg");
//		list.add("http://i.imgur.com/0dqdq3m.jpg");
//		list.add("http://i.imgur.com/QLli9TA.jpg");
//		list.add("http://i.imgur.com/umvz87K.png");
//		list.add("http://i.imgur.com/J1WSoc6.png");
//		list.add("http://i.imgur.com/oRKydEF.jpg");
//		list.add("http://i.imgur.com/Vahvfem.jpg");
//		list.add("http://i.imgur.com/KYrt0ui.jpg");
//		list.add("http://i.imgur.com/OCsSN7k.jpg");
//		list.add("http://i.imgur.com/prC3c7n.jpg");
//		list.add("http://i.imgur.com/9pudjYz.jpg");
//		list.add("http://i.imgur.com/KXqCHZC.jpg");
//		list.add("http://i.imgur.com/jSE47Tc.jpg");
//		list.add("http://i.imgur.com/1iIypoP.jpg");
//	}
//	
//	protected SimpleDraweeView createView() {
//		GenericDraweeHierarchy gdh = ConfigConstants.getGenericDraweeHierarchy(context);
//		
//		return new SimpleDraweeView(context, gdh);
//	}
//	
//	//横竖屏计算图片宽高
//	private int calcDesiredSize(int parentWidth, int parentHeight) {
//		int orientation = context.getResources().getConfiguration().orientation;
//		int desiredSize = (orientation == Configuration.ORIENTATION_LANDSCAPE) ? parentHeight / 2 : parentHeight / 3;
//		return Math.min(desiredSize, parentWidth);
//	}
//	//横竖屏设置图片宽高
//	private static void updateViewLayoutParams(View view, int width, int height) {
//		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//		if (layoutParams == null || layoutParams.height != width || layoutParams.width != height) {
//			layoutParams = new AbsListView.LayoutParams(width, height);
//			view.setLayoutParams(layoutParams);
//		}
//	}
//	
//}
