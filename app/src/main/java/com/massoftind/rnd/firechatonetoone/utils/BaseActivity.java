package com.massoftind.rnd.firechatonetoone.utils;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by Developer on 7-4-2016.
 */
 public abstract class BaseActivity extends Activity
 {


     public abstract void onCreateBaseActivity(Bundle savedInstanceState);
     public abstract void onStartBaseActivity();
     public abstract void onResumeBaseActivity();
     public abstract void onPauseBaseActivity();
     public abstract void onStopBaseActivity();
     public abstract void onDestroyBaseActivity();


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         onCreateBaseActivity(savedInstanceState);
     }

     @Override
     protected void onStart() {
         super.onStart();
         onStartBaseActivity();
     }

     @Override
     protected void onResume() {
         super.onResume();
         //Toast.makeText(this, "BaseActivity onResume", Toast.LENGTH_SHORT).show();
         onResumeBaseActivity();

     }

     @Override
     protected void onStop() {
         onStopBaseActivity();
         super.onStop();
         //Toast.makeText(this, "BaseActivity onStop", Toast.LENGTH_SHORT).show();
     }


     @Override
     protected void onDestroy() {
         onDestroyBaseActivity();
         super.onDestroy();
     }

     @Override
     protected void onPause() {
         onPauseBaseActivity();
         super.onPause();
     }

     @Override
     public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
     }
 }
