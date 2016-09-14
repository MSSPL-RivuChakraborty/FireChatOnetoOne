package com.massoftind.rnd.firechatonetoone;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.massoftind.rnd.firechatonetoone.adapters.LoginRegisterAdapter;
import com.massoftind.rnd.firechatonetoone.datamodal.LoginRegisterDatamodel;
import com.massoftind.rnd.firechatonetoone.interfaces.OnRecyclerViewCellClick;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private RecyclerView loginRecyclerView;
    private LoginRegisterAdapter adapter;
    private ArrayList<LoginRegisterDatamodel> registerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginRecyclerView = (RecyclerView) findViewById(R.id.loginRecyclerView);
        LoginRegisterDatamodel loginRegisterDatamodel = new LoginRegisterDatamodel("Register", "", "", "",
                false, true, 0, false, Color.WHITE, Color.WHITE, null, false, "", null);
        registerItems = new ArrayList<LoginRegisterDatamodel>();
        registerItems.add(loginRegisterDatamodel);
        adapter = new LoginRegisterAdapter(this,registerItems);
        loginRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    
}
