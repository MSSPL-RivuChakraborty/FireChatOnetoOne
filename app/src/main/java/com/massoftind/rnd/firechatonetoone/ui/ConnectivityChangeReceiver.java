/*
package co.nz.quantiful.wave.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

import co.nz.quantiful.wave.R;
import co.nz.quantiful.wave.interfaces.NoNetworkInterface;
import co.nz.quantiful.wave.webservices.Constant;

*/
/**
 * Created by developer on 13/4/16.
 *//*

public class ConnectivityChangeReceiver extends BroadcastReceiver {

    private Activity activity;
    public final static String DISPLAY_MESSAGE_ACTION1="com.wave.app.networkchangestate";

    View network_lay;

    View viewParent;
    NoNetworkInterface network;
    public ConnectivityChangeReceiver(){
        this.activity=activity;
        //  network_lay=activity.findViewById(R.id.imageView1);
    }

    public ConnectivityChangeReceiver(Activity activity){
        this.network=network;
        this.activity=activity;
        network_lay=this.activity.findViewById(R.id.error_id);
        //  Toast.makeText(this.activity, "Connection changed "+": saroj", Toast.LENGTH_SHORT).show();
    }
    public ConnectivityChangeReceiver(Activity activity,NoNetworkInterface network){
        this.network=network;
        this.activity=activity;
        network_lay=activity.findViewById(R.id.error_id);
    }


    public ConnectivityChangeReceiver(Activity activity,NoNetworkInterface network,View viewParent){
        this.network=network;
        this.activity=activity;
        network_lay=activity.findViewById(R.id.error_id);
        this.viewParent=viewParent;
    }

    public ConnectivityChangeReceiver(Activity activity,View fragmentView,NoNetworkInterface network,View viewParent){
        this.activity=activity;
        network_lay=fragmentView.findViewById(R.id.error_id);
        this.network=network;
        this.viewParent=viewParent;
    }
    public void connect(){
        this.activity.registerReceiver(ConnectivityChangeReceiver.this, new IntentFilter(
                DISPLAY_MESSAGE_ACTION1));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //System.out.println("*** Action: " + intent.getAction());
        if(intent.getAction().equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
            //Toast.makeText(context, "Connection changed "+intent.getAction(), Toast.LENGTH_SHORT).show();

            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = manager.getActiveNetworkInfo();

            String action = intent.getAction();
            if (!action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                return;
            }
            NetworkInfo networkInfo =
                    (NetworkInfo)intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (networkInfo.isConnected()) {
<<<<<<< HEAD
                Log.e("Network Change", "Network connection Available");
                Intent intnt = new Intent(Constant.NETWORK_SWITCH_FILTER);
                intnt.putExtra("is_connected",true);
                context.sendBroadcast(intnt);
=======
                //Log.d"Network Change", "Network connection Available: ");
>>>>>>> Fixv1.0.1
            }
            else {
    */
/* Log.e("Network Change", "Network connection lost");
                Intent intnt = new Intent(Constant.NETWORK_SWITCH_FILTER);
                intnt.putExtra("is_connected",false);
                context.sendBroadcast(intent);*//*

            }





            */
/*if(ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                Toast.makeText(context, "Connection changed " + ": sanjeev", Toast.LENGTH_SHORT).show();
            } else if(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
                Toast.makeText(context, "Connection changed "+": saroj", Toast.LENGTH_SHORT).show();
            }*//*

           */
/* if(intent.getExtras()!=null){
                boolean isnetworkAvailable=intent.getExtras().getBoolean("networkState");

                if(network!=null){
                    network.networkChange(isnetworkAvailable);
                }
                if(network_lay!=null){
                    network.networkChange(isnetworkAvailable);
                }
                if(network_lay!=null){
                    if(viewParent!=null){
                        network_lay.setVisibility(View.GONE);
                    }
                    else {
                        network_lay.setVisibility(View.VISIBLE);
                    }
                        //   viewParent.startAnimation(animFadeOut);
                        if(network_lay.getVisibility()!=View.GONE ){

                        }

                }
            }*//*

            if(intent.getExtras()!=null){
                boolean isnetworkAvailable=intent.getExtras().getBoolean("networkState");
                if(isnetworkAvailable){
                  //  network_lay.setVisibility(View.VISIBLE);
                }else {
                   // network_lay.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}
*/package com.massoftind.rnd.firechatonetoone.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.massoftind.rnd.firechatonetoone.R;
import com.massoftind.rnd.firechatonetoone.interfaces.NoNetworkInterface;
import com.massoftind.rnd.firechatonetoone.webservices.Constant;


/**
 * Created by developer on 13/4/16.
 */
public class ConnectivityChangeReceiver extends BroadcastReceiver {

    private Activity activity;
    public final static String DISPLAY_MESSAGE_ACTION1="com.wave.app.networkchangestate";

//    View network_lay;

    View viewParent;
    NoNetworkInterface network;
    public ConnectivityChangeReceiver(){
        this.activity=activity;
        //  network_lay=activity.findViewById(R.id.imageView1);
    }

    public ConnectivityChangeReceiver(Activity activity){
        this.network=network;
        this.activity=activity;
//        network_lay=this.activity.findViewById(R.id.error_id);
        //  Toast.makeText(this.activity, "Connection changed "+": saroj", Toast.LENGTH_SHORT).show();
    }
    public ConnectivityChangeReceiver(Activity activity, NoNetworkInterface network){
        this.network=network;
        this.activity=activity;
//        network_lay=activity.findViewById(R.id.error_id);
    }


    public ConnectivityChangeReceiver(Activity activity, NoNetworkInterface network, View viewParent){
        this.network=network;
        this.activity=activity;
//        network_lay=activity.findViewById(R.id.error_id);
        this.viewParent=viewParent;
    }

    public ConnectivityChangeReceiver(Activity activity, View fragmentView, NoNetworkInterface network, View viewParent){
        this.activity=activity;
//        network_lay=fragmentView.findViewById(R.id.error_id);
        this.network=network;
        this.viewParent=viewParent;
    }
    public void connect(){
        this.activity.registerReceiver(ConnectivityChangeReceiver.this, new IntentFilter(
                DISPLAY_MESSAGE_ACTION1));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //System.out.println("*** Action: " + intent.getAction());
        if(intent.getAction().equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
            //Toast.makeText(context, "Connection changed "+intent.getAction(), Toast.LENGTH_SHORT).show();

            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = manager.getActiveNetworkInfo();

            String action = intent.getAction();
            if (!action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                return;
            }
            NetworkInfo networkInfo =
                    (NetworkInfo)intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (networkInfo.isConnected()) {
                //Log.d("Network Change", "Network connection Available");
                Intent intnt = new Intent(Constant.NETWORK_SWITCH_FILTER);
                intnt.putExtra("is_connected",true);
                context.sendBroadcast(intnt);
            }
            else {

                //Log.d("Network Change", "Network connection lost");
                Intent intnt = new Intent(Constant.NETWORK_SWITCH_FILTER);
                intnt.putExtra("is_connected",false);
                context.sendBroadcast(intnt);
            }





            /*if(ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                Toast.makeText(context, "Connection changed " + ": sanjeev", Toast.LENGTH_SHORT).show();
            } else if(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
                Toast.makeText(context, "Connection changed "+": saroj", Toast.LENGTH_SHORT).show();
            }*/
           /* if(intent.getExtras()!=null){
                boolean isnetworkAvailable=intent.getExtras().getBoolean("networkState");

                if(network!=null){
                    network.networkChange(isnetworkAvailable);
                }
                if(network_lay!=null){
                    network.networkChange(isnetworkAvailable);
                }
                if(network_lay!=null){
                    if(viewParent!=null){
                        network_lay.setVisibility(View.GONE);
                    }
                    else {
                        network_lay.setVisibility(View.VISIBLE);
                    }
                        //   viewParent.startAnimation(animFadeOut);
                        if(network_lay.getVisibility()!=View.GONE ){

                        }

                }
            }*/
            if(intent.getExtras()!=null){
                boolean isnetworkAvailable=intent.getExtras().getBoolean("networkState");
                if(isnetworkAvailable){
                    //  network_lay.setVisibility(View.VISIBLE);
                }else {
                    // network_lay.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}
