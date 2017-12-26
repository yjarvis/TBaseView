package com.jarvis.tbaseview.ui.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.LogUtils;
import com.jarvis.tbaseviewlib.utils.NsdServerUtil;
import com.jarvis.tbaseviewlib.utils.SensorUtil;
import com.jarvis.tbaseviewlib.utils.TUtils;

/**
 * Created by tansheng on 2017/7/28.
 */

public class _25SensorActivity extends TFragmentActivity implements SensorEventListener, SensorUtil.SensorCallBackListener {
    private TitleBackFragment titleBackFragment;
    private TextView xTextview;
    private TextView yTextview;
    private TextView zTextview;
    private SensorManager sm;
    private Sensor aSensor;
    private Sensor mSensor;
    private int number;

    private static final String TAG = "sensor";
    private NsdServerUtil nsdServerUtil;
    private SensorUtil sensorUtil;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        LogUtils.e("onCreate");
        setContentView(R.layout.activity_sensor);
//        NsdServerUtil.getInstance(this).registerService().discoverService();
        TUtils.showToast(context,"发现的设备数量:"+ NsdServerUtil.getInstance(this).getServerNumber());
//        nsdServerUtil=new NsdServerUtil(this);
//        nsdServerUtil.registerService();
//        nsdServerUtil.discoverService();
//        nsdServerUtil.setListener(new NsdServerUtil.ResolvedCallBackListener() {
//            @Override
//            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
//
//            }
//
//            @Override
//            public void onServiceResolved(NsdServiceInfo serviceInfo) {
//                Log.e("_25",serviceInfo.getServiceName()+"::"+serviceInfo.getHost());
//                TUtils.showToast(context,"发现的设备数量:"+ nsdServerUtil.getServerNumber());
//            }
//        });


        //传感器
        sensorUtil=new SensorUtil(this);
        sensorUtil.setListener(this);
        initView(true);
        setData();
    }


    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
        titleBackFragment=new TitleBackFragment().newInstance(titleName, "");
        addTitleFragment(titleBackFragment);

        xTextview= (TextView) findViewById(R.id.sensor_x_text);
        yTextview= (TextView) findViewById(R.id.sensor_y_text);
        zTextview= (TextView) findViewById(R.id.sensor_z_text);
        zTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        initSensor();
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



    private void initSensor(){
         sm= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
//         aSensor=sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
         aSensor=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
         mSensor=sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//        sm.getOrientation()
    }
    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.e("onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("onResume");
        sensorUtil.registerSensor();
    }



    /**
     * 第五步：在失去焦点时注销传感器
     */
    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.e("onPause");
        sensorUtil.unregisterSensor();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.e("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.e("onRestart");
    }

    @Override
    public void finish() {
        super.finish();
        LogUtils.e("finish");
//        nsdServerUtil.unregisterService();
        NsdServerUtil.getInstance(this).unregisterService();
    }

    /**
     * 传感器事件值改变时的回调接口：执行此方法的频率与注册传感器时的频率有关
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
//        // 大部分传感器会返回三个轴方x,y,x的event值，值的意义因传感器而异
//        double x = event.values[0];
//        double y = event.values[1];
//        double z = event.values[2];
//        Log.e(TAG,event.accuracy+"");
//
//        BigDecimal bigDecimal=new BigDecimal(x);
//        BigDecimal a= bigDecimal.setScale(5,BigDecimal.ROUND_HALF_UP);
//        BigDecimal bigDecimal2=new BigDecimal(y);
//        BigDecimal b= bigDecimal2.setScale(5,BigDecimal.ROUND_HALF_UP);
//        BigDecimal bigDecimal3=new BigDecimal(z);
//        BigDecimal c= bigDecimal3.setScale(5,BigDecimal.ROUND_HALF_UP);
//////
////////        double zTheta = atan2(gravityZ,sqrtf(gravityX * gravityX + gravityY * gravityY)) / M_PI * 180.0;
////////        double xyTheta = atan2(gravityX, gravityY) / M_PI * 180.0;
//////
////////        double zTh=Math.atan2(z,Math.sqrt(x*x+y*y))/Math.PI*180.0;
////////        double xyTh=Math.atan2(x,y)/Math.PI*180.0;
//////
//        xTextview.setText("x轴的角度"+a.multiply(new BigDecimal(1000)).toString());
//        yTextview.setText("y轴的角度"+b.multiply(new BigDecimal(1000)).toString());
//        zTextview.setText("z轴的角度"+c.multiply(new BigDecimal(1000)).toString());

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //TODO 在传感器精度发生改变时做些操作，accuracy为当前传感器精度
    }


    @Override
    public void onSensorChange(float x, float y, float z, String orientation) {
        xTextview.setText("x轴的角度"+x);
        yTextview.setText("y轴的角度"+y);
        zTextview.setText("z轴的角度:"+orientation+"     "+z);
    }

}
