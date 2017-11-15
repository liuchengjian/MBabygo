package liu.chj.mbabygo.fragment;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2017/1/13 - 16:41
 * 注释：
 */
public class GDMapPlayFragment extends Fragment implements LocationSource,
        AMapLocationListener, View.OnClickListener ,AMap.InfoWindowAdapter,AMap.OnMapClickListener{
    private MapView mapView;
    private AMap map;
    @ViewInject(R.id.iv_gdmap_location_again_play)
    private ImageView iv_gdmap_location_again_play;

    //定位功能
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private View view;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private double lat;
    private double lon;
    LatLng latLng;
    LatLng latLng1;
    MarkerOptions markerOptions;
    Marker Mymarker;
    Marker marker;
    Marker currentMarker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gdmap_play, null);
        mapView = (MapView) view.findViewById(R.id.gdmap_play);
        mapView.onCreate(savedInstanceState);
        x.view().inject(this, view);
        init();
        Log.e("lchj", " onCreateView()");
        map.setTrafficEnabled(false);
        //修改地图的中心点位置
        CameraPosition cp = map.getCameraPosition();
        //cp.zoom
        CameraPosition cpNew = CameraPosition.fromLatLngZoom(new LatLng(29.589178, 106.573167), cp.zoom);
        CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cpNew);
        map.moveCamera(cu);

        Log.e("lchj", "ddddddddd" + lat + "dddddddd" + lon);

        iv_gdmap_location_again_play.setOnClickListener(this);

        return view;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_gdmap_location_again_play:
                if (mlocationClient != null) {
                    mlocationClient.startLocation();
                }
//                Toast.makeText(getActivity(),"你点了我",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    /**
     * * 初始化AMap对象
     */
    private void init() {
        if (map == null) {
            map = mapView.getMap();
        } else {
            map.clear();
            map = mapView.getMap();
        }
    }

    //初始化定位服务,这个地方有错误，这个地方map已经初始化
    //因此定位的初始化代码就进不去了。
    private void initLocationService() {
        if (map != null) {
            MyLocationStyle locationStyle = new MyLocationStyle();
            locationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.gdmap_icon_play_indoor));
            //圆边框颜色
            locationStyle.strokeColor(Color.WHITE);
            //圆的颜色
            locationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
            //圆半径
            locationStyle.strokeWidth(0f);

            Log.e("lchj", "ffffffffffffffffff");
            map.setMyLocationStyle(locationStyle);
            map.setLocationSource(this);
            mUiSettings = map.getUiSettings();//实例化UiSettings类对象
            //是否取消地图缩放控件
            mUiSettings.setZoomControlsEnabled(false);
            //显示一键定位按钮
            mUiSettings.setMyLocationButtonEnabled(false);
            map.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        mapView.onResume();
        //由于fragment的生命周期，再次初始化定位
        initLocationService();
        Log.e("lchj", " onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        mapView.onPause();

        Log.e("lchj", " mapView.onPause()");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        mapView.onDestroy();
        super.onDestroy();
        deactivate();
//        //退出界面的时候停止定位
//        if (mlocationClient != null) {
//            mlocationClient.stopLocation();
//        }

        Log.e("lchj", " mapView.onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        mapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation amaplocation) {
        // TODO Auto-generated method stub
        if (amaplocation != null && mListener != null) {
            if (amaplocation != null && amaplocation.getErrorCode() == 0) {
//                mListener.onLocationChanged(amaplocation);

                lat = amaplocation.getLatitude();
                lon = amaplocation.getLongitude();
                latLng = new LatLng(lat, lon);

                //初始化定位服务
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
                markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("当前位置");
                markerOptions.visible(true);
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.gdmap_icon_study_indoor));
                markerOptions.icon(bitmapDescriptor);
                map.addMarker(markerOptions);
                if (Mymarker == null) {
                    //如果是空的添加一个新的,icon方法就是设置定位图标，可以自定义
                    Mymarker = map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("当前位置")
//                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.gdmap_icon_food_outdoor)));
                            .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),
                                    R.mipmap.gdmap_icon_food_outdoor))));
                } else {
                    //已经添加过了，修改位置即可
                    Mymarker.setPosition(latLng);
                }

                //然后可以移动到定位点,使用animateCamera就有动画效果
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                setMarker();
            } else {
                String errText = "failed to locate," + amaplocation.getErrorCode() + ": "
                        + amaplocation.getErrorInfo();
                Log.e("error", errText);
            }

        }
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        // TODO Auto-generated method stub
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getActivity());
            mLocationOption = new AMapLocationClientOption();
            mlocationClient.setLocationListener(this);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            //设置是否只定位一次,默认为false
            mLocationOption.setOnceLocation(true);
            //设置是否强制刷新WIFI，默认为强制刷新
//        mLocationOption.setWifiActiveScan(false);
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            mLocationOption.setMockEnable(false);
            //设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(10000);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }
    }

    /**
     * 销毁和暂停定位
     */
    @Override
    public void deactivate() {
        // TODO Auto-generated method stub
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
        mLocationOption = null;
    }
    /**
     * Marker 点击事件
     */
    public void setMarker() {
        // 定义 Marker 点击事件监听
        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
            // marker 对象被点击时回调的接口
            // 返回 true 则表示接口已响应事件，否则返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                currentMarker = marker;
//                Toast.makeText(getActivity(), "你点了我的", Toast.LENGTH_LONG).show();
                marker.showInfoWindow();
                Log.e("lchj","sssssss"+marker);
                return false;
            }
        };
        // 绑定 Marker 被点击事件
        map.setOnMarkerClickListener(markerClickListener);
        map.setInfoWindowAdapter(this);
        map.setOnMapClickListener(this);

    }
    @Override
    public View getInfoContents(Marker marker) {
        Log.e("marker",
                marker.getObject() + "getInfoContents: " + marker.getId());
        return null;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        Log.e("marker", marker.getObject() + "getInfoWindow: " + marker.getId());
        View infoWindow = getActivity().getLayoutInflater().inflate(R.layout.infowindow_gdmap_layout_play, null);
        render(marker, infoWindow);
        infoWindow.isShown();
        return infoWindow;
    }

    /**
     * 自定义infowinfow窗口，动态修改内容的
     */
    public void render(Marker marker, View view) {

    }
    // 点击非marker区域，将显示的InfoWindow隐藏
    @Override
    public void onMapClick(LatLng latLng) {
        if (currentMarker != null) {
            currentMarker.hideInfoWindow();
        }
    }

}

