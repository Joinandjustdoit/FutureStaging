package com.touzila.futurestaging.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.touzila.futurestaging.FSApplication;

import java.util.List;

/**
 * Created by liu on 2017/12/22.
 */

public class AppUtil {

    /**
     * 获取MetaData
     * @param metaData
     * @return
     */
    public static String getMetaData(String metaData) {
        try {
            ApplicationInfo info = FSApplication.getContext().getPackageManager().getApplicationInfo(FSApplication.getContext().getPackageName(), PackageManager.GET_META_DATA);
            return info.metaData.getString(metaData);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "official";
    }

    /**
     * 获取应用程序名称
     * @return
     */
    public static String getAppName() {
        try {
            PackageInfo info = FSApplication.getContext().getPackageManager().getPackageInfo(FSApplication.getContext().getPackageName(), 0);
            return FSApplication.getContext().getResources().getString(info.applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取versionCode
     * @return
     */
    public static int getVersionCode() {
        try {
            return FSApplication.getContext().getPackageManager().getPackageInfo(FSApplication.getContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取versionName
     * @return
     */
    public static String getVersionName() {
        try {
            return FSApplication.getContext().getPackageManager().getPackageInfo(FSApplication.getContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * @param processName
     * @return
     */
    public static boolean isNamedProcess(String processName) {
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) FSApplication.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = manager.getRunningAppProcesses();
        if (processInfoList == null || processInfoList.size() == 0) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo info : processInfoList) {
            if (info != null && info.pid == pid && processName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 应用程序是否在后台运行
     * @return
     */
    public static boolean isApplicationInBackground() {
        ActivityManager manager = (ActivityManager) FSApplication.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfoList = manager.getRunningTasks(1);
        if (taskInfoList != null && !taskInfoList.isEmpty()) {
            ComponentName topActivity = taskInfoList.get(0).topActivity;
            if (topActivity != null && !topActivity.getPackageName().equals(FSApplication.getContext().getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
