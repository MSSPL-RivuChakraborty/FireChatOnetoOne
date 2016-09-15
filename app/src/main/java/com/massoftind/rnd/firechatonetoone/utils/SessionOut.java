package com.massoftind.rnd.firechatonetoone.utils;


import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;



public class SessionOut {


    public void navigateToHome(Activity activity) {
//			  LogInSharedPrefData data=new LogInSharedPrefData(activity);
//			  data.resetAllData();

        try {
            NotificationManager nMgr = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
            nMgr.cancelAll();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
//            LoginSharedData login = new LoginSharedData(activity);
//            DatabaseHelper db = new DatabaseHelper(activity);
//            db.onTrancate();
//
//            OfflineSharedData offlineData = new OfflineSharedData(activity);
//            offlineData.setDownloading(false);
//            offlineData.setLastDownloaded("");
//
//            LoginApiData login_data = new LoginApiData();
//            login_data = login.getLoginData(login_data);
//
//            String userId = login_data.getLoginData().getUsername();
//
//            LoginApiData demo_login = new LoginApiData();
//            demo_login.getLoginData().setUsername(userId);
//
//            login.setLoginData(demo_login);
//            Intent intent = new Intent(activity, HomeActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            activity.startActivity(intent);

            try {
                //activity.finish();
                String ns = Context.NOTIFICATION_SERVICE;
                NotificationManager nMgr = (NotificationManager) activity.getApplicationContext().getSystemService(ns);
                nMgr.cancelAll();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }


}
