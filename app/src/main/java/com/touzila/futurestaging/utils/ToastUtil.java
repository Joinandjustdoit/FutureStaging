package com.touzila.futurestaging.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.touzila.futurestaging.FSApplication;
import com.touzila.futurestaging.R;

/**
 * Created by liu on 2017/12/25.
 */

public class ToastUtil {

    private static Toast toast;

    /**
     * 短时间底部显示吐司
     * @param msg
     */
    public static void showShortToastBottom(String msg) {
        if (FSApplication.getContext() != null) {
            if (toast == null) {
                toast = Toast.makeText(FSApplication.getContext(), msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(FSApplication.getContext().getResources().getInteger(R.integer.config_toastDefaultGravity),
                    0,
                    FSApplication.getContext().getResources().getDimensionPixelSize(R.dimen.toast_y_offset));
            toast.show();
        }
    }

    /**
     * 短时间中间显示吐司
     * @param msg
     */
    public static void showShortToastCenter(String msg) {
        if (FSApplication.getContext() != null) {
            if (toast == null) {
                toast = Toast.makeText(FSApplication.getContext(), msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    /**
     * 短时间顶部显示吐司
     * @param msg
     */
    public static void showShortToastTop(String msg) {
        if (FSApplication.getContext() != null) {
            if (toast == null) {
                toast = Toast.makeText(FSApplication.getContext(), msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 0);
            toast.show();
        }
    }

    /**
     * 长时间底部显示吐司
     * @param msg
     */
    public static void showLongToastBottom(String msg) {
        if (FSApplication.getContext() != null) {
            if (toast == null) {
                toast = Toast.makeText(FSApplication.getContext(), msg, Toast.LENGTH_LONG);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,
                    0,
                    FSApplication.getContext().getResources().getDimensionPixelSize(R.dimen.toast_y_offset));
            toast.show();
        }
    }

    /**
     *
     * @param msg
     */
    public static void showLongToastCenter(String msg) {
        if (FSApplication.getContext() != null) {
            if (toast == null) {
                toast = Toast.makeText(FSApplication.getContext(), msg, Toast.LENGTH_LONG);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static void showLongToastTop(String msg) {
        if (FSApplication.getContext() != null) {
            if (toast == null) {
                toast = Toast.makeText(FSApplication.getContext(), msg, Toast.LENGTH_LONG);
            } else {
                toast.setText(msg);
            }
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 0);
            toast.show();
        }
    }
}
