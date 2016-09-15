package com.massoftind.rnd.firechatonetoone.ui;

import android.app.Activity;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class SetTypeFace {

    private TextView mTextView;
    private Typeface mTypeFace;
    private Activity activity;
    private Typeface light;
    private Typeface medium;
    private Typeface regularType;
    private Typeface mediumSecondType;
    private Typeface lightType;

    private Button mButton;

    public SetTypeFace(Activity activity){
        light= Typeface.createFromAsset(activity.getAssets(), "fonts/light.otf");
        regularType= Typeface.createFromAsset(activity.getAssets(), "fonts/regular.otf");
        medium= Typeface.createFromAsset(activity.getAssets(), "fonts/medium.otf");
        mediumSecondType= Typeface.createFromAsset(activity.getAssets(), "fonts/mediumsecond.otf");

    }

    public SetTypeFace(Activity a, String type){
        light= Typeface.createFromAsset(activity.getAssets(), "fonts/light.otf");
        regularType= Typeface.createFromAsset(activity.getAssets(), "fonts/regular.otf");
        medium= Typeface.createFromAsset(activity.getAssets(), "fonts/medium.otf");
        mediumSecondType= Typeface.createFromAsset(activity.getAssets(), "fonts/mediumsecond.otf");

    }

    public void setTypeFace(TextView text, String type){

        if(type.equalsIgnoreCase("l")){

            text.setTypeface(light);
        }else if(type.equalsIgnoreCase("r")){

            text.setTypeface(regularType);
        }else if(type.equalsIgnoreCase("m")){

            text.setTypeface(medium);
        }else if(type.equalsIgnoreCase("ms")){
            text.setTypeface(mediumSecondType);
        }

        //text.setTypeface(mTypeFace);
    }
    public void setTypeFace(EditText text, String type){

        if(type.equalsIgnoreCase("l")){

            text.setTypeface(light);

        }else if(type.equalsIgnoreCase("r")){

            text.setTypeface(regularType);
        }else if(type.equalsIgnoreCase("m")){

            text.setTypeface(medium);
        }else if(type.equalsIgnoreCase("ms")){
            text.setTypeface(mediumSecondType);
        }

        //text.setTypeface(mTypeFace);
    }

    public void setTypeFace(Button mButton, String type){

        if(type.equalsIgnoreCase("l")){

            mButton.setTypeface(light);
        }else if(type.equalsIgnoreCase("r")){

            mButton.setTypeface(regularType);
        }else if(type.equalsIgnoreCase("m")){

            mButton.setTypeface(medium);
        }else if(type.equalsIgnoreCase("ms")){
            mButton.setTypeface(mediumSecondType);
        }

        //text.setTypeface(mTypeFace);
    }

    public void setTypeFace(RadioButton mButton, String type){

        if(type.equalsIgnoreCase("l")){

            mButton.setTypeface(light);
        }else if(type.equalsIgnoreCase("r")){

            mButton.setTypeface(regularType);
        }else if(type.equalsIgnoreCase("m")){

            mButton.setTypeface(medium);
        }else if(type.equalsIgnoreCase("ms")){
            mButton.setTypeface(mediumSecondType);
        }

        //text.setTypeface(mTypeFace);
    }
    public Typeface getFont(String type){

        Typeface typeTypeFace=null;

        if(type.equalsIgnoreCase("l")){

            typeTypeFace=light;

        }else if(type.equalsIgnoreCase("r")){

            typeTypeFace=regularType;
        }else if(type.equalsIgnoreCase("m")){
            typeTypeFace=medium;
        }else if(type.equalsIgnoreCase("ms")){

            typeTypeFace=mediumSecondType;
        }else{
            typeTypeFace=lightType;
        }

        return typeTypeFace;
    }

}
