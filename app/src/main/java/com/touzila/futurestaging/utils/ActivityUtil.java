package com.touzila.futurestaging.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by liu on 2017/12/27.
 */

public class ActivityUtil {

    private static Stack<Activity> stack;

    /**
     * 添加一个Activity到堆栈中
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (stack == null) {
            stack = new Stack<>();
        }
        stack.add(activity);
    }

    /**
     * 从堆栈中移除指定的Activity
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activity != null) {
            stack.remove(activity);
        }
    }

    /**
     * 获取栈顶的Activity
     * @return
     */
    public static Activity getTopActivity() {
        if (stack.isEmpty()) {
            return null;
        } else {
            return stack.get(stack.size() - 1);
        }
    }
}
