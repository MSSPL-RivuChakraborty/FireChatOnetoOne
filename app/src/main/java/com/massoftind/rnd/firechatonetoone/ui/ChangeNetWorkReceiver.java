package com.massoftind.rnd.firechatonetoone.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Toast;

import com.massoftind.rnd.firechatonetoone.R;
import com.massoftind.rnd.firechatonetoone.interfaces.NoNetworkInterface;


/**
 * Created by developer on 13/4/16.
 */
public class ChangeNetWorkReceiver {
    private Activity activity;
    public final static String DISPLAY_MESSAGE_ACTION1="com.wave.app.networkchangestate";

    View network_lay;

    View viewParent;
    NoNetworkInterface network;


    public ChangeNetWorkReceiver(Activity activity){
        this.activity=activity;
      // network_lay=activity.findViewById(R.id.imageView1);
    }

    public ChangeNetWorkReceiver(Activity activity, NoNetworkInterface network){
        this.network=network;
        this.activity=activity;
//        network_lay=activity.findViewById(R.id.error_id);
    }


    public ChangeNetWorkReceiver(Activity activity, NoNetworkInterface network, View viewParent){
        this.network=network;
        this.activity=activity;
//        network_lay=activity.findViewById(R.id.error_id);
        this.viewParent=viewParent;
    }


    public ChangeNetWorkReceiver(Activity activity, View fragmentView, NoNetworkInterface network, View viewParent){
        this.activity=activity;
//        network_lay=fragmentView.findViewById(R.id.error_id);
        this.network=network;
        this.viewParent=viewParent;
    }

    public void connect(){
        this.activity.registerReceiver(onNetWorkState, new IntentFilter(
                DISPLAY_MESSAGE_ACTION1));
    }

    private final BroadcastReceiver onNetWorkState= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(activity, "my broadcast", Toast.LENGTH_LONG).show();

            if(intent.getExtras()!=null){
                boolean isnetworkAvailable=intent.getExtras().getBoolean("networkState");

                if(network!=null){
                    network.networkChange(isnetworkAvailable);
                }
                if(network_lay!=null){
                    network.networkChange(isnetworkAvailable);
                }
                if(network_lay!=null){
                    if(viewParent!=null)
                        //   viewParent.startAnimation(animFadeOut);
                        if(network_lay.getVisibility()!= View.GONE ){

                        }
                }
        }
    }

};
}

