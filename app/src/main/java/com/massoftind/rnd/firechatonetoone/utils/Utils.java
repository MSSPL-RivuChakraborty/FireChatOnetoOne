package com.massoftind.rnd.firechatonetoone.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * Created by developer on 8/4/16.
 */
public class Utils {


    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 111;

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
    public static void openKeyBoard(Activity activity)
    {
        try
        {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public boolean isNetworkConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }

    public static void hideCheck(ViewGroup viewGroup, Activity activity)
    {

        if (viewGroup instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) viewGroup).getChildCount(); i++) {

                View innerView = ((ViewGroup) viewGroup).getChildAt(i);

                setupUI(innerView,activity);
            }

            setupUI(viewGroup,activity);
        }
    }


    public static void setupUI(View view, final Activity activity) {


        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    hideKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.

    }
    private static String gps_coordinate = "0.0,0.0";
    private static String gps_Lat = "0.0";
    private static String gps_Long = "0.0";

    static GPSTracker gps;

    public static String getLatLong(Activity activity) {
        gps = new GPSTracker(activity);


        gps_coordinate = "" + gps.getLatitude() + " " + gps.getLongitude();
        //Log.d"gps", gps_coordinate);
        return gps_coordinate;
    }

    public static String getLong(Activity activity) {
        gps = new GPSTracker(activity);

            gps_Long =  ""+gps.getLongitude();

            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings

        return gps_Long;
    }

    public static String getLat(Activity activity) {
        gps = new GPSTracker(activity);
        if(gps.canGetLocation()){
            gps_Lat =  ""+gps.getLatitude();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
//            gps.showSettingsAlert();
        }
        return gps_Lat;

    }

    public static void checkGPSSettings(Activity activity){
        gps = new GPSTracker(activity);
        if (gps.canGetLocation()) {
            gps_Long = "" + gps.getLongitude();
            gps_Lat = "" + gps.getLatitude();
        } else {
            gps.showSettingsAlert();
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    /**
     * @param mContext - Activity context
     * @return - device width
     */
    public static int getDeviceWidth(Context mContext)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getDeviceHeight(Context mContext)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, final int reqWidth, final int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        LogPrinter.d("calculateInSampleSize", "height " + height + ", width " + width
                + ", reqWidth " + reqWidth + ", reqHeight " + reqHeight + ", inSampleSize " + inSampleSize);

        return inSampleSize;
    }

    public static boolean checkPermission(final Context context)
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)||ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage and Camera permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}
