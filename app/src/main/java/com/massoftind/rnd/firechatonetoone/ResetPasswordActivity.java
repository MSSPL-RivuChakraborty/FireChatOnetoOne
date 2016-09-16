package com.massoftind.rnd.firechatonetoone;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.massoftind.rnd.firechatonetoone.adapters.LoginRegisterAdapter;
import com.massoftind.rnd.firechatonetoone.datamodal.LoginRegisterDatamodel;

import java.util.ArrayList;

public class ResetPasswordActivity extends AppCompatActivity {

    private RecyclerView loginRecyclerView;
    private LoginRegisterAdapter adapter;
    private ArrayList<LoginRegisterDatamodel> resetPasswordItems;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        loginRecyclerView = (RecyclerView) findViewById(R.id.loginRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        loginRecyclerView.setLayoutManager(layoutManager);

        resetPasswordItems = new ArrayList<LoginRegisterDatamodel>();

        init();

        adapter = new LoginRegisterAdapter(this,resetPasswordItems);
        loginRecyclerView.setAdapter(adapter);

    }

    private void init() {

        for(int i=0;i<2;i++){
            LoginRegisterDatamodel loginRegisterDatamodel = null;
            switch (i){

                case 0:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","Enter Your Registered Email Id","","",true,false, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                            false,Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 1:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("", "", "", "", false, false, 0, false, 0, 0,
                            null, true, "Reset Your Password", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            validateAndReset();
                        }
                    }, false, null, null);
                    break;
            }
            if(null != loginRegisterDatamodel){
                resetPasswordItems.add(loginRegisterDatamodel);
            }
        }

    }

    private void validateAndReset() {


        final String email = resetPasswordItems.get(0).getTextValue();

        SharedPreferences preferences = getSharedPreferences("fcm",MODE_PRIVATE);
        final String token = preferences.getString("fcm_token","");

        if(email.trim().equalsIgnoreCase("")){
            resetPasswordItems.get(0).setError("EMail is required");
            adapter.notifyItemChanged(0);
            return;
        }

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
