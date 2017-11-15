package liu.chj.mbabygo.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import org.xutils.x;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2017/1/9 - 9:56
 * 注释：高德地图所有界面Fragemnt
 */
public class GdAllMapFragment extends Fragment {
    private UiSettings mUiSettings;//定义一个UiSettings对象
    View view;

    private MapView mMapView;
    private AMap aMap;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private double lat;
    private double lon;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gdmap_all, null);
        mMapView = (MapView) view.findViewById(R.id.gdmap_all);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        x.view().inject(this, view);
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        init();
        return view;
    }

    /**
     * 设置地图系统控件位置（加号，log）
     */
    private void setUiSettings() {
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        //是否取消地图缩放控件
        mUiSettings.setZoomControlsEnabled(false);
        /**
         * 系统提供了AMapOptions.ZOOM_POSITION_RIGHT_CENTER:设置缩放控件在右边中间位置。
         * AMapOptions.ZOOM_POSITION_RIGHT_BUTTOM:设置缩放控件在右下角的位置。这个也是默认设置。
         */
        mUiSettings.setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_CENTER);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        Log.e("lchj","onResume()");
        super.onResume();
        mMapView.onResume();
        if ( mLocationClient==null){
            init();
        }else{
            mLocationClient.startLocation();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
        Log.e("lchj","onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("lchj","onStop()");
        mLocationClient.stopLocation();//停止定位
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端。
    }
    /**
     * * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            setUiSettings();
        }else{
            aMap.clear();
            aMap = mMapView.getMap();
            setUiSettings();
            setUpMap();
        }
    }
    private void setUpMap() {
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
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
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    /**
     * 声明定位回调监听器
     */
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    amapLocation.getLatitude();//获取纬度
                    amapLocation.getLongitude();//获取经度
                    amapLocation.getAccuracy();//获取精度信息
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date date = new Date(amapLocation.getTime());
//                    df.format(date);//定位时间
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    amapLocation.getProvince();//省信息
                    amapLocation.getCity();//城市信息
                    amapLocation.getDistrict();//城区信息
                    amapLocation.getStreet();//街道信息
                    amapLocation.getStreetNum();//街道门牌号信息
                    amapLocation.getCityCode();//城市编码
                    amapLocation.getAdCode();//地区编码
                    amapLocation.getAoiName();//获取当前定位点的AOI信息
                    lat = amapLocation.getLatitude();
                    lon = amapLocation.getLongitude();
                    Log.v("pcw", "lat : " + lat + " lon : " + lon);
                    Log.v("pcw", "Country : " + amapLocation.getCountry() + " province : " + amapLocation.getProvince() + " City : " + amapLocation.getCity() + " District : " + amapLocation.getDistrict());

//                    // 设置当前地图显示为当前位置
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 19));
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(lat, lon));
                    markerOptions.title("当前位置");
                    markerOptions.visible(true);
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.gdmap_icon_food_outdoor));
                    markerOptions.icon(bitmapDescriptor);
//                    aMap.getMapScreenMarkers().clear();
                    aMap.addMarker(markerOptions);
//                    LatLng latLng = new LatLng(lat,lon);
//                    MarkerOptions markerOption = new MarkerOptions();
//                    final Marker marker = aMap.addMarker(markerOption);
//                    markerOption.position(latLng);
//                    markerOption.visible(true);
//                    markerOption.draggable(true);//设置Marker可拖动
//                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
//                            .decodeResource(getResources(),R.mipmap.gdmap_icon_food_outdoor)));
//// 将Marker设置为贴地显示，可以双指下拉地图查看效果
//                    markerOption.setFlat(true);//设置marker平贴地图效果
//                    aMap.addMarker(markerOption);

                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

}
