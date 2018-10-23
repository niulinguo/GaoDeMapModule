package com.niles.gaodemapmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.niles.gaode_map.GaoDeMapManager;

public class MainActivity extends AppCompatActivity implements AMapLocationListener {

    private static final String TAG = "MainActivity";

    private GaoDeMapManager mMapManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AMapLocationClientOption option = new AMapLocationClientOption()
                .setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mMapManager = new GaoDeMapManager(getApplicationContext(), this, option);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapManager.startLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapManager.stopLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        Log.e(TAG, aMapLocation.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapManager.destroy();
    }
}
