package com.chinagpay.zhpaysdk.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 2015/6/16.
 */
public class Const {
    public static List<Activity> activityList = new ArrayList<Activity>();

    // finish所有list中的activity
    public static void exit() {
        exitActivity();
    }

    private static void exitActivity() {
        int siz = activityList.size();
        for (int i = 0; i < siz; i++) {
            if (activityList.get(i) != null) {
                activityList.get(i).finish();
            }
        }
    }
}
