package com.massoftind.rnd.firechatonetoone.push;

import android.app.IntentService;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String CHAT_ENGAGE_TOPIC = "chat_engage";

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        SharedPreferences preferences = getSharedPreferences("fcm",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("fcm_token",refreshedToken);
        editor.commit();

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        FirebaseMessaging.getInstance()
                .subscribeToTopic(CHAT_ENGAGE_TOPIC);

    }

    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }

}
