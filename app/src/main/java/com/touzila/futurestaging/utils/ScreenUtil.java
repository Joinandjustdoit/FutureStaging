package com.touzila.futurestaging.utils;

import android.content.Context;

/**
 * Created by liu on 2017/12/25.
 */

public class ScreenUtil {

    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }

        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1;
        }

        return px / context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPxInt(Context context, float dp) {
        return (int)(dpToPx(context, dp) + 0.5f);
    }

    public static int pxToDpCeilInt(Context context, float px) {
        return (int)(pxToDp(context, px) + 0.5f);
    }
}
