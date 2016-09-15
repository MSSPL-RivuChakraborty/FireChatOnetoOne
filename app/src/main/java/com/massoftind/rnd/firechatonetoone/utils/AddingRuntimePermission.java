package com.massoftind.rnd.firechatonetoone.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by developer on 29/4/16.
 */
public class AddingRuntimePermission {


    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 2;
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 3;


    Activity activity;


    /* To check permission enabled or not */
    public boolean checkHasPermission(Activity activity, String permission){

        /* activity is the current activity, permission is the permission that has to be checked enabled or not like(Manifest.permission.WRITE_CALENDAR) */

        if (ContextCompat.checkSelfPermission(activity,permission)
                != PackageManager.PERMISSION_GRANTED){


            return false;
        }
        else{
            return true;
        }
    }

    /* To check permission enabled or not */
    public boolean checkHasPermissionMultiple(Activity activity, String[] requestedPermissions) {

        /* activity is the current activity, permission is the permission that has to be checked enabled or not like(Manifest.permission.WRITE_CALENDAR) */
        boolean checkstatus = false;

        for (int i = 0; i < requestedPermissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, requestedPermissions[i])
                    != PackageManager.PERMISSION_GRANTED) {
                checkstatus= false;
                break;
            } else {
                checkstatus= true;
            }
        }
        return checkstatus;
    }

    public boolean requestPermission(Activity activity, String permission){

            // Should we show an explanation?
            //shouldShowRequestPermissionRationale(). This method returns true if the app has requested this permission previously
            // and the user denied the request.
        /*
            Note: If the user turned down the permission request in the past and chose the
            Don't ask again option in the permission request system dialog, this method returns false.
            The method also returns false if a device policy prohibits the app from having that permission.
         *//*

      */
        boolean hasPermission = false;
        if(checkHasPermission(activity, permission)) {

            hasPermission = true;
            //Toast.makeText(activity, "Permission given!!!", Toast.LENGTH_SHORT).show();
        }
        /*
      */
        else{
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    permission)) {
                Log.d("path","hasPermission"+hasPermission);

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.


                ActivityCompat.requestPermissions(activity,
                        new String[]{permission},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
            else {
                Log.d("path","hasPermission"+hasPermission);

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        new String[]{permission},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);


                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            //Toast.makeText(activity, "Permission denied!!!", Toast.LENGTH_SHORT).show();
        }
        /**/
Log.d("path","hasPermission"+hasPermission);
        return hasPermission;
        
    }

    /*
    public boolean requestAppPermission(Activity activity,final String[] requestedPermissions){

        // Should we show an explanation?
        //shouldShowRequestPermissionRationale(). This method returns true if the app has requested this permission previously
        // and the user denied the request.
        /*
            Note: If the user turned down the permission request in the past and chose the
            Don't ask again option in the permission request system dialog, this method returns false.
            The method also returns false if a device policy prohibits the app from having that permission.
         *//*


        boolean hasPermission = false;

        if(checkHasPermissionMultiple(activity, requestedPermissions)) {

            hasPermission = true;
            //Toast.makeText(activity, "Permission given!!!", Toast.LENGTH_SHORT).show();
        }

        else{


            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    requestedPermissions[0]) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(activity,
                            requestedPermissions[1]) ) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(activity,
                        new String[]{requestedPermissions[0],requestedPermissions[1]},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        new String[]{requestedPermissions[0],requestedPermissions[1]},
                        MY_PERMISSIONS_REQUEST_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            Toast.makeText(activity, "Permission denied!!!", Toast.LENGTH_SHORT).show();
        }


        return hasPermission;

    }*/

    public boolean requestAppPermission(Activity activity, final String[] requestedPermissions){

        // Should we show an explanation?
        //shouldShowRequestPermissionRationale(). This method returns true if the app has requested this permission previously
        // and the user denied the request.
        /*
            Note: If the user turned down the permission request in the past and chose the
            Don't ask again option in the permission request system dialog, this method returns false.
            The method also returns false if a device policy prohibits the app from having that permission.
         *//*

      */
        boolean hasPermission = false;

        if(checkHasPermissionMultiple(activity, requestedPermissions)) {

            hasPermission = true;
            //Toast.makeText(activity, "Permission given!!!", Toast.LENGTH_SHORT).show();
        }
        /*
      */
        else{


            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    requestedPermissions[0]) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(activity,
                            requestedPermissions[1]) ) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(activity,
                        requestedPermissions,
                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        requestedPermissions,
                        MY_PERMISSIONS_REQUEST_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
           // Toast.makeText(activity, "Permission denied!!!", Toast.LENGTH_SHORT).show();
        }
        /**/

        return hasPermission;

    }


}