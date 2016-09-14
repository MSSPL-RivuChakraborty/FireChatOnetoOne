package com.massoftind.rnd.firechatonetoone;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        loginRecyclerView.setLayoutManager(layoutManager);

        registerItems = new ArrayList<LoginRegisterDatamodel>();
//        registerItems.add(loginRegisterDatamodel);
        init();
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


    private void init(){
        for(int i=0;i<8;i++){
            LoginRegisterDatamodel loginRegisterDatamodel = null;
            switch (i){
                case 0:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("", "", "", "",
                            false, false, 0, false, 0, 0, null, false, "", null, true, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Upload Profile Image
                            Toast.makeText(getBaseContext(),"Yet to Implement",Toast.LENGTH_LONG).show();
                        }
                    },null);
                    break;
                case 1:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","First Name","","",true,false, InputType.TYPE_CLASS_TEXT,
                            false,Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 2:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","Last Name","","",true,false, InputType.TYPE_CLASS_TEXT,
                            false,Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 3:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","Email","","",true,false, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                            false,Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 4:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","Password","","",true,false, InputType.TYPE_TEXT_VARIATION_PASSWORD,
                            true,Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 5:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","Confirm Password","","",true,false, InputType.TYPE_TEXT_VARIATION_PASSWORD,
                            true,Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 6:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","Mobile","","",true,false, InputType.TYPE_CLASS_NUMBER,
                            false,Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 7:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("", "", "", "", false, false, 0, false, 0, 0,
                            null, true, "Sign Up", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getBaseContext(),"Yet to Implement",Toast.LENGTH_LONG).show();
                        }
                    }, false, null, null);
                    break;
            }
            if(null != loginRegisterDatamodel){
                registerItems.add(loginRegisterDatamodel);
            }
        }
    }
}
