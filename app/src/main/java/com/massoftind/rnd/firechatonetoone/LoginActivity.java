package com.massoftind.rnd.firechatonetoone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.massoftind.rnd.firechatonetoone.adapters.LoginRegisterAdapter;
import com.massoftind.rnd.firechatonetoone.datamodal.firebase.Device;
import com.massoftind.rnd.firechatonetoone.datamodal.LoginRegisterDatamodel;
import com.massoftind.rnd.firechatonetoone.utils.LogPrinter;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private RecyclerView loginRecyclerView;
    private LoginRegisterAdapter adapter;
    private ArrayList<LoginRegisterDatamodel> loginItems;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        loginRecyclerView = (RecyclerView) findViewById(R.id.loginRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        loginRecyclerView.setLayoutManager(layoutManager);

        loginItems = new ArrayList<LoginRegisterDatamodel>();

        init();

        adapter = new LoginRegisterAdapter(this,loginItems);
        loginRecyclerView.setAdapter(adapter);

    }

    private void init() {

        for(int i=0;i<5;i++){
            LoginRegisterDatamodel loginRegisterDatamodel = null;
            switch (i){

                case 0:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","Email","","",true,false, 0,
                            false,Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 1:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","Password","","",true,false, 0,
                            true,Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 2:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("", "", "", "", false, false, 0, false, 0, 0,
                            null, true, "Log In", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            validateAndLogin();
                        }
                    }, false, null, null);
                    break;
                case 3:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("Forgot Password?", "", "", "", false, true, 0, false, 0, Color.WHITE,
                            null, false, "", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(LoginActivity.this,ResetPasswordActivity.class);
                            startActivity(i);
                        }
                    }, false, null, null);
                    break;
                case 4:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("New User?", "", "", "", false, true, 0, false, 0, Color.WHITE,
                            null, false, "", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                            startActivity(i);
                        }
                    }, false, null, null);
                    break;
            }
            if(null != loginRegisterDatamodel){
                loginItems.add(loginRegisterDatamodel);
            }
        }

    }

    private void validateAndLogin() {

        final String email = loginItems.get(0).getTextValue();
        final String password = loginItems.get(1).getTextValue();

        SharedPreferences preferences = getSharedPreferences("fcm",MODE_PRIVATE);
        final String token = preferences.getString("fcm_token","");

        if(email.trim().equalsIgnoreCase("")){
            loginItems.get(0).setError("EMail is required");
            adapter.notifyItemChanged(0);
            return;
        }
        if(password.trim().equalsIgnoreCase("")){
            loginItems.get(1).setError("Password is required");
            adapter.notifyItemChanged(1);
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed." + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            LogPrinter.e("LoginActivity","Authentication failed - " + task.getException().getMessage(),task.getException());

                        } else {

                            mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

                            final String userId = auth.getCurrentUser().getUid();

                            mFirebaseDatabaseReference.child("devices")
                                    .child(token).setValue(new Device(LoginActivity.this,userId,token));

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finishAffinity();
                        }
                    }
                });


    }
}
