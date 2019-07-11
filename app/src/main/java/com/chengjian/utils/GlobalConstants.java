package com.chengjian.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

public class GlobalConstants {
    private final static String TAG = GlobalConstants.class.getCanonicalName();
    public static final String REQUEST_URL = "http://trash.lhsr.cn/sites/feiguan/trashTypes_2/TrashQuery.aspx?kw=";
    public static final String MY_SERVER_GOODS_JSON_ADDR = "http://106.52.25.193:8088/Dustbins/webInfo.json";
    public static final String MY_SERVER_ADDR_PREFIX = "http://106.52.25.193:8088/Dustbins/";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };


    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static final String IMG_SHARE_NAME = "ShareImg.jpg";
    public static String getSharedImagePath(){
        String externalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d(TAG, externalPath);
        return externalPath + "/" + IMG_SHARE_NAME;
    }
}
