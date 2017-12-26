package com.jarvis.tbaseview.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.TUtils;
import com.jarvis.tbaseviewlib.view.floatlayout.TCloudLayout;
import com.jarvis.tbaseviewlib.view.floatlayout.TCloudLayout.OnClickCloudLayoutListner;

import java.util.ArrayList;

/**
 * 标签云界面
 * 
 * @author tansheng
 * 
 */
public class _6CloudActivity extends TFragmentActivity implements OnClickListener {

	private TitleBackFragment titleBackFragment;
	private TCloudLayout cloudLayout;// 标签云组件
	private Button btnVertical;// 竖向模式
	private Button btnHorizontal;// 横向模式
	private Button btnText;
	private Button btnPic;
	private Button btnTextPic;

	private ArrayList<String> middletextList = new ArrayList<String>();
	private ArrayList<String> leftextList = new ArrayList<String>();
	private ArrayList<String> righttextList = new ArrayList<String>();
	private ArrayList<Bitmap> imageData = new ArrayList<Bitmap>();

	private int[] imagePath = { R.drawable.meinv1, R.drawable.meinv2, R.drawable.meinv3, R.drawable.meinv4, R.drawable.meinv5, R.drawable.meinv6, R.drawable.meinv1, R.drawable.meinv2, R.drawable.meinv3, R.drawable.meinv4, R.drawable.meinv5, R.drawable.meinv6 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cloud);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment = new TitleBackFragment().newInstance(titleName, null);
		addTitleFragment(titleBackFragment);

		// 得到自定义的控件
		cloudLayout = (TCloudLayout) findViewById(R.id.cloud_layout);

		btnVertical = (Button) findViewById(R.id.cloud_vertical);
		btnHorizontal = (Button) findViewById(R.id.cloud_horizontal);
		btnText = (Button) findViewById(R.id.cloud_text);
		btnPic = (Button) findViewById(R.id.cloud_pic);
		btnTextPic = (Button) findViewById(R.id.cloud_text_pic);

		btnVertical.setOnClickListener(this);
		btnHorizontal.setOnClickListener(this);
		btnText.setOnClickListener(this);
		btnPic.setOnClickListener(this);
		btnTextPic.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cloud_vertical:// 竖向
			cloudLayout.setVertical(true);
			break;
		case R.id.cloud_horizontal:// 横向
			cloudLayout.setVertical(false);
			break;
		case R.id.cloud_text:// 文字
			cloudLayout.setTextMiddle(middletextList);
			cloudLayout.setTextLeftOrTop(leftextList);
			cloudLayout.setTextRightOrDown(righttextList);
			break;
		case R.id.cloud_pic:// 图片
			//方法1：添加单个
//			for (int i = 0; i < imagePath.length; i++) {
//				Bitmap bmp = BitmapFactory.decodeResource(resources, imagePath[i]);
//				cloudLayout.setImageLeftOrTop(bmp, i);
//				cloudLayout.setImageMiddle(bmp, i);
//				cloudLayout.setImageRightOrDown(bmp, i);
//			}
			
			//方法2：添加多个
			for (int i = 0; i < imagePath.length; i++) {
				Bitmap bmp = BitmapFactory.decodeResource(resources, imagePath[i]);
				imageData.add(bmp);
			}
			cloudLayout.setImageLeftOrTop(imageData);
			cloudLayout.setImageMiddle(imageData);
			cloudLayout.setImageRightOrDown(imageData);
			

			break;
		case R.id.cloud_text_pic:// 文字加图片
			for (int i = 0; i < imagePath.length; i++) {
				Bitmap bmp = BitmapFactory.decodeResource(resources, imagePath[i]);
				imageData.add(bmp);
			}
			cloudLayout.setTextImageLeftOrTop(leftextList, imageData);
			cloudLayout.setTextImageMiddle(middletextList, imageData);
			cloudLayout.setTextImageRightOrDown(righttextList, imageData);
			break;

		default:
			break;
		}
	}

	@Override
	public void setData() {
		leftextList.add("宝贝计划");
		leftextList.add("乡村老尸");
		leftextList.add("我的机器人女友");
		leftextList.add("捉妖记");
		leftextList.add("煎饼侠");
		leftextList.add("复仇者联盟");
		leftextList.add("蜘蛛侠");
		leftextList.add("超人归来");
		leftextList.add("闪电侠");
		leftextList.add("爸爸去哪儿");
		leftextList.add("奔跑吧兄弟");
		leftextList.add("明日世界");

		middletextList.add("大圣归来");
		middletextList.add("终结者1");
		middletextList.add("回到未来");
		middletextList.add("纳尼亚传奇");
		middletextList.add("功夫小蝇");
		middletextList.add("微爱");
		middletextList.add("世界末日");
		middletextList.add("婚前试爱");
		middletextList.add("匆匆那年");
		middletextList.add("迷城");
		middletextList.add("新警察故事");
		middletextList.add("甜蜜十八岁");

		righttextList.add("明日边缘");
		righttextList.add("侏罗纪公园");
		righttextList.add("博物馆奇妙夜");
		righttextList.add("星际穿越");
		righttextList.add("地球停转之日");
		righttextList.add("地心引力");
		righttextList.add("星际迷航");
		righttextList.add("苹果派");
		righttextList.add("3D肉蒲团");
		righttextList.add("超时空旅恋人");
		righttextList.add("生化危机");
		righttextList.add("101次求婚");

		cloudLayout.setOnClickCloudLayoutListner(new OnClickCloudLayoutListner() {

			@Override
			public void cloudOnClick(View v, int positonLeft, int positonMiddle, int positonRight) {
				TUtils.showToast(context, "点击位置：左/上边：" + positonLeft + "中间：" + positonMiddle + "右/下边：" + positonRight);

			}
		});

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
