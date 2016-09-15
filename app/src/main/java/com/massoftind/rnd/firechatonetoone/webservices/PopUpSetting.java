package com.massoftind.rnd.firechatonetoone.webservices;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.massoftind.rnd.firechatonetoone.R;
import com.massoftind.rnd.firechatonetoone.interfaces.PopUpOk;
import com.massoftind.rnd.firechatonetoone.interfaces.YesNoCallBack;
import com.massoftind.rnd.firechatonetoone.ui.SetTypeFace;


/**
 * Created by developer on 15/4/16.
 */
public class PopUpSetting {
    SetTypeFace typeFace;
    Activity activity;

    public  void popup(final Activity activity, String msg)
    {
        final Dialog dialog = new Dialog(activity);
        // Include dialog.xml file
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setContentView(R.layout.custom);

        TextView text=(TextView)dialog.findViewById(R.id.text);

        text.setText(msg);

        TextView ok=(TextView)dialog.findViewById(R.id.dismiss);

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();

                //activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
            }
        });

        dialog.setTitle("Custom Dialog");

        dialog.show();
    }

    public  void popupDublicateEmail(final Activity activity, String msg, final PopUpOk popUpOk)
    {
        final Dialog dialog = new Dialog(activity);
        // Include dialog.xml file
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setContentView(R.layout.custom_nearly);

        TextView text=(TextView)dialog.findViewById(R.id.text);

        text.setText(msg);

        TextView ok=(TextView)dialog.findViewById(R.id.dismiss);

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popUpOk.OkClick();
                dialog.dismiss();

                //activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
            }
        });

        dialog.setTitle("Custom Dialog");

        dialog.show();
    }

    public  void popupSuccess(final Activity activity, String msg)
    {
        final Dialog dialog = new Dialog(activity);
        // Include dialog.xml file
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setContentView(R.layout.custom);

        TextView text=(TextView)dialog.findViewById(R.id.text);
        TextView popupheading=(TextView)dialog.findViewById(R.id.popupheading);

        popupheading.setVisibility(View.GONE);

        text.setText(msg);

        final TextView ok=(TextView)dialog.findViewById(R.id.dismiss);

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                activity.finish();

                //activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
            }
        });

        dialog.setTitle("Custom Dialog");

        dialog.show();
    }

  /*  public  void popup(final Context activity,String msg,)
    {
        final Dialog dialog = new Dialog(activity);
        // Include dialog.xml file
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setContentView(R.layout.custom);

        TextView text=(TextView)dialog.findViewById(R.id.text);

        text.setText(msg);

        TextView ok=(TextView)dialog.findViewById(R.id.dismiss);

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(activity, ActivateUserAfterLogin.class);
                activity.startActivity(intent);
                //activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
            }
        });

        dialog.setTitle("Custom Dialog");

        dialog.show();
    }*/
    public  void popup(final Activity activity, String msg, final PopUpOk popUpOk)
    {
        final Dialog dialog = new Dialog(activity);
        // Include dialog.xml file
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setContentView(R.layout.custom);

        TextView text=(TextView)dialog.findViewById(R.id.text);

        text.setText(msg);

        TextView ok=(TextView)dialog.findViewById(R.id.dismiss);

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                popUpOk.OkClick();
                //activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
            }
        });


        dialog.setTitle("Custom Dialog");

        dialog.show();
    }
    public  void YesNoCustomTextPopup(final Activity activity, String text, final boolean isFinish, final YesNoCallBack callback){
        typeFace = new SetTypeFace(activity);

        final Dialog dialog = new Dialog(activity);
        // Include dialog.xml file
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setContentView(R.layout.custom_yes_no);
        //TextView text=(TextView)dialog.findViewById(R.id.text);
//        text.setText(msg);
//        typeFace.setTypeFace(text,"r");
        LinearLayout lower = (LinearLayout)dialog.findViewById(R.id.lower);
        TextView ok = (TextView)dialog.findViewById(R.id.report);
        TextView txt = (TextView)dialog.findViewById(R.id.text);
        txt.setText(text);


        // final EditText edit_txt=(EditText)dialog.findViewById(R.id.edit_txt);

        // typeFace.setTypeFace(ok,"b");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                callback.yesCallback();
            }
        });


        lower.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                if(isFinish)
                    activity.finish();
                //activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
            }
        });
        // Set dialog title
        dialog.setTitle("Custom Dialog");

        dialog.show();

    }

    public  void YesNoCustomTextPopupdelete(final Activity activity, String text, final boolean isFinish, final YesNoCallBack callback){
        typeFace = new SetTypeFace(activity);

        final Dialog dialog = new Dialog(activity);
        // Include dialog.xml file
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setContentView(R.layout.custom_yes_no_delete);
        //TextView text=(TextView)dialog.findViewById(R.id.text);
//        text.setText(msg);
//        typeFace.setTypeFace(text,"r");
        LinearLayout lower = (LinearLayout)dialog.findViewById(R.id.lower);
        TextView ok = (TextView)dialog.findViewById(R.id.report);
        TextView txt = (TextView)dialog.findViewById(R.id.text);
        txt.setText(text);


        // final EditText edit_txt=(EditText)dialog.findViewById(R.id.edit_txt);

        // typeFace.setTypeFace(ok,"b");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(isFinish)
                    activity.finish();

            }
        });


        lower.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                callback.yesCallback();

                //activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
            }
        });
        // Set dialog title
        dialog.setTitle("Custom Dialog");

        dialog.show();

    }

//    public  void CustomTextPopupedit(final Activity activity, String text, final boolean isFinish, final SaveCommentCallBack callback){
//        typeFace = new SetTypeFace(activity);
//
//        final Dialog dialog = new Dialog(activity);
//        // Include dialog.xml file
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//
//        dialog.setContentView(R.layout.custom_edit_popup);
//        //TextView text=(TextView)dialog.findViewById(R.id.text);
////        text.setText(msg);
////        typeFace.setTypeFace(text,"r");
//        LinearLayout lower = (LinearLayout)dialog.findViewById(R.id.lower);
//        TextView save = (TextView)dialog.findViewById(R.id.save);
//      final EditText edit =(EditText)dialog.findViewById(R.id.edit);
//        edit.setText(text);
//        TextView cancel = (TextView)dialog.findViewById(R.id.cancel);
//
//        // final EditText edit_txt=(EditText)dialog.findViewById(R.id.edit_txt);
//
//        // typeFace.setTypeFace(ok,"b");
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(edit.getText().toString().trim().length()>0){
//                    dialog.dismiss();
//
//                    callback.saveComment(edit.getText().toString().trim());
//                }
//
//
//            }
//        });
//
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                dialog.dismiss();
//                if(isFinish)
//                    activity.finish();
//
//                //activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
//            }
//        });
//        // Set dialog title
//        dialog.setTitle("Custom Dialog");
//
//        dialog.show();
//
//    }
/*  public  void CustomTextPopupedit(final Activity activity,String text,final boolean isFinish, final YesNoCallBack callback){
        typeFace = new SetTypeFace(activity);

        final Dialog dialog = new Dialog(activity);
        // Include dialog.xml file
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setContentView(R.layout.custom_edit_popup);
        //TextView text=(TextView)dialog.findViewById(R.id.text);
//        text.setText(msg);
//        typeFace.setTypeFace(text,"r");
        LinearLayout lower = (LinearLayout)dialog.findViewById(R.id.lower);
        TextView save = (TextView)dialog.findViewById(R.id.save);
      final EditText edit =(EditText)dialog.findViewById(R.id.edit);
        edit.setText(text);
        TextView cancel = (TextView)dialog.findViewById(R.id.cancel);

        // final EditText edit_txt=(EditText)dialog.findViewById(R.id.edit_txt);

        // typeFace.setTypeFace(ok,"b");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                callback.yesCallback();

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                if(isFinish)
                    activity.finish();

                //activity.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
            }
        });
        // Set dialog title
        dialog.setTitle("Custom Dialog");

        dialog.show();

    }*/
}
