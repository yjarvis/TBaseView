package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.ui.adapter.AdapterDragGridView;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.TUtils;
import com.jarvis.tbaseviewlib.view.dragview.DragGridView;
import com.jarvis.tbaseviewlib.view.dragview.DragGridView.OnDragListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 拖动添加效果
 * 
 * @author ts QQ:717549357
 * 
 */
public class _21DragActivity extends TFragmentActivity {
	private TitleBackFragment titleBackFragment;
	private DragGridView gridView1;
	private DragGridView gridView2;
	private AdapterDragGridView adapter1;
	private AdapterDragGridView adapter2;
	private String[] strs = new String[] { "重庆", "成都", "达州", "西安", "上海", "北京", "广州", "深圳", "天津", "南京", "西藏", "拉萨", "绵竹", "安康", "湖北", "山西", "湖南", "广西", "苏州", "杭州", "哈尔滨", "云南", "昆明", "丽江", "洱海", "大理" };
	List<String> list1 = new ArrayList<String>();
	List<String> list2 = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_drag);
		super.onCreate(savedInstanceState);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment = new TitleBackFragment().newInstance(titleName, "");
		addTitleFragment(titleBackFragment);

		gridView1 = (DragGridView) findViewById(R.id.drag_gridview1);
		gridView2 = (DragGridView) findViewById(R.id.drag_gridview2);

		gridView2.setOnDragListener(new OnDragListener() {

			@Override
			public void dragUp(int mItemPosition, float x, float y) {
				TUtils.showToast(context, "x坐标：" + x + " y坐标：" + y + "grid1上：" + gridView1.getTop() + "下：" + gridView1.getBottom() + "左：" + gridView1.getLeft());
				// 判断控件是否到gridview1的范围，如果在，就添加此条数据
				if (TUtils.isRect(x, y, gridView1.getLeft(), gridView1.getTop(), gridView1.getRight(), gridView1.getBottom())) {

					list1.add(list2.get(mItemPosition));
					adapter1.notifyDataSetInvalidated();
				}
			}

			@Override
			public void dragDown(int mItemPosition, float x, float y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragMove(int mItemPosition, float x, float y) {
				// TODO Auto-generated method stub

			}

		});
	}

	

	@Override
	public void setData() {
		for (int i = 0; i < 10; i++) {
			list1.add(strs[i]);
		}
		for (int i = 0; i < strs.length; i++) {
			list2.add(strs[i]);
		}

		adapter1 = new AdapterDragGridView(context, list1);
		gridView1.setAdapter(adapter1);
		adapter2 = new AdapterDragGridView(context, list2);
		gridView2.setAdapter(adapter2);
	}

	@Override
	public void requestData(boolean isShow) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showFragment(Fragment fragment) {
		// TODO Auto-generated method stub

	}

}
