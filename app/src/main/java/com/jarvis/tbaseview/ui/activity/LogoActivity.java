package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;


public class LogoActivity extends TFragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
//		AdManager.getInstance(context).init(Constants.appId, Constants.appSecret, Constants.isTestModel);
//		// 有米加载积分墙数据
//		OffersManager.getInstance(context).onAppLaunch();
//		// 有米加载插屏/开屏数据
//		SpotManager.getInstance(context).loadSpotAds();
//		// 有米开屏启动
//		SpotManager.getInstance(this).showSplashSpotAds(this, MainActivity.class);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
	}

	@Override
	public void setData() {
		// TODO Auto-generated method stub

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
