package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.customview.TBitmapView;
import com.jarvis.tbaseviewlib.data.CacheData;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.TUtils;

/**
 * 图片美颜界面
 * @author tansheng
 *
 */
public class _3BitmapViewActivity extends TFragmentActivity implements OnClickListener {

	private TitleBackFragment titleBackFragment;
	private TBitmapView customView;// 自定义组件
	private SeekBar seekR;
	private SeekBar seekRF;
	private SeekBar seekG;
	private SeekBar seekGF;
	private SeekBar seekB;
	private SeekBar seekBF;
	private SeekBar seekA;
	private TextView seekRNumber;//R值
	private TextView seekRNumberF;//R分量值
	private TextView seekGNumber;//G值
	private TextView seekGNumberF;//G分量值
	private TextView seekBNumber;//B值
	private TextView seekBNumberF;//B分量值
	private Button btnSave;//保存
	private Button btnYuan;//原图
	private Button btnFuPian;//负片
	private Button btnOld;//怀旧
	private Button btnAutumn;//秋色
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bitmapview);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment=new TitleBackFragment().newInstance(titleName, null);
		addTitleFragment(titleBackFragment);
		
		seekRNumber=(TextView) findViewById(R.id.bitmapView_seekR_number);
		seekGNumber=(TextView) findViewById(R.id.bitmapView_seekG_number);
		seekBNumber=(TextView) findViewById(R.id.bitmapView_seekB_number);
		seekRNumberF=(TextView) findViewById(R.id.bitmapView_seekR_fen_number);
		seekGNumberF=(TextView) findViewById(R.id.bitmapView_seekG_fen_number);
		seekBNumberF=(TextView) findViewById(R.id.bitmapView_seekB_fen_number);
		seekR=(SeekBar) findViewById(R.id.bitmapView_seekR);
		seekRF=(SeekBar) findViewById(R.id.bitmapView_seekR_fen);
		seekG=(SeekBar) findViewById(R.id.bitmapView_seekG);
		seekGF=(SeekBar) findViewById(R.id.bitmapView_seekG_fen);
		seekB=(SeekBar) findViewById(R.id.bitmapView_seekB);
		seekBF=(SeekBar) findViewById(R.id.bitmapView_seekB_fen);
		seekA=(SeekBar) findViewById(R.id.bitmapView_seekA);
		btnSave=(Button) findViewById(R.id.bitmapView_save);
		btnYuan=(Button) findViewById(R.id.bitmapView_yuantu);
		btnFuPian=(Button) findViewById(R.id.bitmapView_fupian);
		btnOld=(Button) findViewById(R.id.bitmapView_old);
		btnAutumn=(Button) findViewById(R.id.bitmapView_autumn);

		// 得到自定义的控件
		customView = (TBitmapView) findViewById(R.id.custom_view);
		//设置图片资源
		customView.setBitmap(R.drawable.meinv2);
		
		
		btnSave.setOnClickListener(this);
		btnYuan.setOnClickListener(this);
		btnFuPian.setOnClickListener(this);
		btnOld.setOnClickListener(this);
		btnAutumn.setOnClickListener(this);
		seekR.setOnSeekBarChangeListener(onSeekBarChangeListener);
		seekG.setOnSeekBarChangeListener(onSeekBarChangeListener);
		seekB.setOnSeekBarChangeListener(onSeekBarChangeListener);
		seekA.setOnSeekBarChangeListener(onSeekBarChangeListener);
		seekRF.setOnSeekBarChangeListener(onSeekBarChangeListener);
		seekGF.setOnSeekBarChangeListener(onSeekBarChangeListener);
		seekBF.setOnSeekBarChangeListener(onSeekBarChangeListener);
	}
	
	private OnSeekBarChangeListener onSeekBarChangeListener=new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean arg2) {
			float temp=(float) ((double)progress/50);
			if (seekBar==seekR) {
				customView.setRedNumber(temp);
				seekRNumber.setText(" "+temp);
			}else if (seekBar==seekG) {
				customView.setGreenNumber(temp);
				seekGNumber.setText(" "+temp);
			}else if (seekBar==seekB) {
				customView.setBlueNumber(temp);
				seekBNumber.setText(" "+temp);
			}else if (seekBar==seekA) {
				customView.setAlpha(temp);
			}else if (seekBar==seekRF) {
				customView.setRedNumberF(progress);
				seekRNumberF.setText(" "+progress);
			}else if (seekBar==seekGF) {
				customView.setGreenNumberF(progress);
				seekGNumberF.setText(" "+progress);
			}else if (seekBar==seekBF) {
				customView.setBlueNumberF(progress);
				seekBNumberF.setText(" "+progress);
			}
			
			
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bitmapView_save://保存
			customView.saveBitmap();
			TUtils.showToast(context, resources.getString(R.string.toast_bitmapView_save)+ CacheData.getImagesCache());
			break;
		case R.id.bitmapView_yuantu://原图
			customView.setReset();
			break;
		case R.id.bitmapView_fupian://负片
			customView.setNegativeEffect(customView.getBitmap());
			break;
		case R.id.bitmapView_old://怀旧
			customView.setOldRemeber(customView.getBitmap());
			break;
		case R.id.bitmapView_autumn://秋色
			customView.setAutumn(customView.getBitmap());
			break;
		}
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
