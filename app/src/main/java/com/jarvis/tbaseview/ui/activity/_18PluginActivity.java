package com.jarvis.tbaseview.ui.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.model.plugin.PluginData;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.PluginManager;
import com.jarvis.tbaseviewlib.utils.TUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 插件式开发
 * 
 * @author tansheng
 * 
 */
public class _18PluginActivity extends TFragmentActivity {
	private TitleBackFragment titleBackFragment;

	private ImageView mainBg;
	private ImageView homeImg;
	private ImageView personImg;
	private ImageView moreImg;
	private Button changeBtn;
	/**插件管理器*/
	private PluginManager manager;
	/**插件协议数据*/
	private ArrayList<PluginData> pluginData;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_plugin);
		//初始化插件管理器
		manager=PluginManager.newInstance(context);
		//查找插件资源
		pluginData=(ArrayList<PluginData>) manager.findPluginResource(PluginData.class);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment = new TitleBackFragment().newInstance(titleName, "");
		addTitleFragment(titleBackFragment);

		mainBg = (ImageView) findViewById(R.id.plugin_main_bg);
		homeImg = (ImageView) findViewById(R.id.plugin_home_img);
		personImg = (ImageView) findViewById(R.id.plugin_person_img);
		moreImg = (ImageView) findViewById(R.id.plugin_more_img);
		changeBtn = (Button) findViewById(R.id.plugin_change_btn);

		changeBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		 showDialog();

	}

	@Override
	public void setData() {
	}

	@Override
	public void requestData(boolean isShow) {

	}

	@Override
	public void showFragment(Fragment fragment) {

	}

	//弹窗显示已安装的插件
	private void showDialog() {
		Builder builder = new Builder(context);
		//获得已安装的插件列表
		final List<PackageInfo> list=manager.getPluginAppList();
		
		if (list.size()<=0) {
			TUtils.showToast(context, "你还没有皮肤，请先下载皮肤");
			return;
		}
		String[] items=new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			items[i]=list.get(i).applicationInfo.loadLabel(getPackageManager()).toString();
		}
		builder.setItems(items, new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int position) {
				//设置资源
				mainBg.setImageDrawable(manager.getPluginDrawable(pluginData.get(position).getMainbg()));
				homeImg.setImageDrawable(manager.getPluginDrawable(pluginData.get(position).getPlugin_home()));
				personImg.setImageDrawable(manager.getPluginDrawable(pluginData.get(position).getPlugin_person()));
				moreImg.setImageDrawable(manager.getPluginDrawable(pluginData.get(position).getPlugin_more()));
			}
		});
		builder.create().show();
	}

}
