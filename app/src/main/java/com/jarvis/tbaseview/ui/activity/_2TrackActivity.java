package com.jarvis.tbaseview.ui.activity;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.customview.TrackView;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

import java.util.Random;

/**
 * 沿贝塞尔曲线轨迹动画
 * @author tansheng
 */
public class _2TrackActivity extends TFragmentActivity implements OnClickListener {

	private TitleBackFragment titleBackFragment;
	private TrackView trackView;// 自定义组件
	private Button button;
	private ValueAnimator valueAnimator;
	/**开始点的Y坐标*/
	float startY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment = new TitleBackFragment().newInstance(titleName, null);
		addTitleFragment(titleBackFragment);
		// 得到自定义的控件
		trackView = (TrackView) findViewById(R.id.custom_view);
		button = (Button) findViewById(R.id.track_btn);
		button.setOnClickListener(this);
		initAnimation();
	}

	@Override
	public void onClick(View v) {
		initAnimation();
		//开始动画
		valueAnimator.start();
		trackView.setPoint(500, startY, 300, 150, 100, 800);
		}

	/**
	 * 初始化动画
	 */
	@SuppressWarnings("static-access")
	private void initAnimation(){
		startY = new Random().nextInt(300)+200;
		valueAnimator = new ValueAnimator().ofObject(new BezierEvaluator(), new PointF(500, startY), new PointF(100, 800));
		//动画持续时间
		valueAnimator.setDuration(1000);
		//执行动画的控件
		valueAnimator.setTarget(button);
		//监听动画
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				PointF point = (PointF) valueAnimator.getAnimatedValue();
				button.setX(point.x);
				button.setY(point.y);
			}
			
			
		});
	}
	
	/**
	 *  贝塞尔动画
	 * @author tansheng
	 */
	private class BezierEvaluator implements TypeEvaluator<PointF> {

		@Override
		public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
			float t = fraction;
			float oneMinusT = 1 - t;
			//记录整个贝塞尔曲线的运行轨迹坐标
			PointF point = new PointF();
			//开始点
			PointF point0 = (PointF) startValue;
			//控制点
			PointF point1 = new PointF();
			point1.set(300, 150);
			//结束点
			PointF point2 = (PointF) endValue;

			//二次方贝塞尔曲线
			point.x = oneMinusT * oneMinusT * point0.x + 2 * t * oneMinusT * point1.x + t * t * point2.x;
			point.y = oneMinusT * oneMinusT * point0.y + 2 * t * oneMinusT * point1.y + t * t * point2.y;

			//返回整个贝塞尔曲线运行的轨迹坐标
			return point;
		}
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

	
	
}
