package com.niles.gaodemapmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.niles.gaode_map.GaoDeMapManager;

public class MainActivity extends AppCompatActivity implements AMapLocationListener {

    private static final String TAG = "MainActivity";

    private GaoDeMapManager mMapManager;

    private MapView mMapView;
    private AMap mMap;
    private Marker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AMapLocationClientOption option = new AMapLocationClientOption()
                .setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mMapManager = new GaoDeMapManager(getApplicationContext(), this, option);

        mMapView = findViewById(R.id.mv_map);
        mMapView.onCreate(savedInstanceState);
        mMap = mMapView.getMap();

        mMap.setTrafficEnabled(false);
        mMap.setMapType(AMap.MAP_TYPE_NORMAL);

        MyLocationStyle locationStyle = new MyLocationStyle();
        locationStyle.interval(2000);
        locationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        mMap.setMyLocationStyle(locationStyle);
        mMap.setMyLocationEnabled(true);

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setScaleControlsEnabled(true);
        uiSettings.setAllGesturesEnabled(true);

        CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(17);
        mMap.animateCamera(cameraUpdate);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
        mMapManager.startLocation();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
        mMapManager.stopLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        Log.e(TAG, aMapLocation.toString());

//        if (mMap != null) {
//            LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
//            if (mMarker == null) {
//                mMarker = mMap.addMarker(new MarkerOptions()
//                        .position(latLng));
//            } else {
//                mMarker.setPosition(latLng);
//            }
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mMapManager.destroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}
