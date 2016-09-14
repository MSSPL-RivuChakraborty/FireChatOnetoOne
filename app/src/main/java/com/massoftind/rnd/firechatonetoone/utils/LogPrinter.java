package com.massoftind.rnd.firechatonetoone.utils;

import android.util.Log;

/**
 * Created by developer on 11/7/16.
 */
public class LogPrinter {

    private static boolean debuggable = false;

    public static void d(String tag, String message){
        if(isDebuggable()) {
            Log.d(tag, message);
        }
    }

    public static void w(String tag, String message){
        if(isDebuggable()) {
            Log.w(tag, message);
        }
    }

    public static void e(String tag, String message){
        if(isDebuggable()) {
            Log.e(tag, message);
        }
    }

    public static void d(String tag, String message, Exception e){
        if(isDebuggable()) {
            Log.d(tag, message,e);
        }
    }

    public static void w(String tag, String message, Exception e){
        if(isDebuggable()) {
            Log.w(tag, message,e);
        }
    }

    public static void e(String tag, String message, Exception e){
        if(isDebuggable()) {
            Log.e(tag, message,e);
        }
    }

    public static void printStackTrace( Exception e){
        if(isDebuggable()) {
            e.printStackTrace();
        }
    }

    public static boolean isDebuggable() {
//        try {
//            return (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
//        } catch (Exception e){
//            return false;
//        }
        return debuggable;
    }

    public static void v(String tag, String message) {
        if(isDebuggable()) {
            Log.v(tag, message);
        }
    }

    public static void i(String tag, String message) {

    }

    public static void printStackTrace(OutOfMemoryError ome) {
        if(isDebuggable()) {
            ome.printStackTrace();
        }
    }
}
