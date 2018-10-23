package com.niles.gaode_map;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by Niles
 * Date 2018/10/23 14:14
 * Email niulinguo@163.com
 */
public class GaoDeMapManager {

    private final AMapLocationClient mClient;

    public GaoDeMapManager(Context context, AMapLocationListener listener, AMapLocationClientOption option) {
        mClient = new AMapLocationClient(context);
        mClient.setLocationListener(listener);
        mClient.setLocationOption(option);
    }

    public void startLocation() {
        mClient.startLocation();
    }

    public void stopLocation() {
        mClient.stopLocation();
    }

    public void destroy() {
        mClient.onDestroy();
    }
}
