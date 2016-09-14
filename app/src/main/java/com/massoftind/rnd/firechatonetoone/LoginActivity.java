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

public class LoginActivity extends AppCompatActivity {

    private RecyclerView loginRecyclerView;
    private LoginRegisterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginRecyclerView = (RecyclerView) findViewById(R.id.loginRecyclerView);
        LoginRegisterDatamodel loginRegisterDatamodel = new LoginRegisterDatamodel("new user?", "", "", "",
                false, true, 0, false, Color.WHITE, Color.WHITE, new OnRecyclerViewCellClick() {
            @Override
            public void onCellClick(View clickedView, int position) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        }, false, "", null);
        ArrayList<LoginRegisterDatamodel> loginItems = new ArrayList<LoginRegisterDatamodel>();
        loginItems.add(loginRegisterDatamodel);
        adapter = new LoginRegisterAdapter(this,loginItems);
        loginRecyclerView.setAdapter(adapter);

    }
}
