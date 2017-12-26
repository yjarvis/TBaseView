package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.jarvis.tbaseview.R;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.LogUtils;

/**
 * Created by tansheng on 2017/8/29.
 */
public class _27LocationActivity extends TFragmentActivity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;
    private MyLocationListener myListener = new MyLocationListener();
    //是否是第一次定位
    private boolean isFirstLoc = true;
    private Marker marker;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_location);
        initView(true);
    }

    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
        titleBackFragment = new TitleBackFragment().newInstance(titleName, null);
        addTitleFragment(titleBackFragment);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                // 地图状态改变结束
                //target地图操作的中心点。
                LatLng target = mBaiduMap.getMapStatus().target;
                LogUtils.e("屏幕中心点经度:" + target.longitude + "纬度：" + target.latitude);
                //定义Maker坐标点
                LatLng point = new LatLng(target.latitude, target.longitude);
                if (marker != null) {
                    marker.setPosition(point);
//                    try {
//                        List<Address> addressArrayList=geoCoder.getFromLocation(target.latitude, target.longitude,5);
//                        TUtils.showToast(context,addressArrayList.get(0).getCountryName()+addressArrayList.get(0).getFeatureName()+addressArrayList.get(0).getPremises());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    return;
                } else {
                    mBaiduMap.clear();
                    //构建Marker图标  ，这里可以自己替换
                    BitmapDescriptor bitmap = BitmapDescriptorFactory
                            .fromResource(R.drawable.location);
                    //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions()
                            .position(point)
                            .icon(bitmap)
                            .zIndex(12)
                            .draggable(false);
                    //在地图上添加Marker，并显示
                    marker = (Marker) mBaiduMap.addOverlay(option);
                }


            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {

            }
        });
//        geoCoder=new Geocoder(context);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                //将地图移动到定位点
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }
        }
    }
}
