package com.massoftind.rnd.firechatonetoone.utils;

import android.app.Activity;

import com.massoftind.rnd.firechatonetoone.R;


/**
 * Created by developer on 1/7/16.
 */
public class ScreenTransitionAnimation
{
    public static void startActivityTransition(Activity activity)
    {
        activity.overridePendingTransition(R.anim.slide_righttoleft, R.anim.slide_lefttoright);
    }

    public static void finishActivityTransition(Activity activity)
    {
        activity.overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
    }
}
