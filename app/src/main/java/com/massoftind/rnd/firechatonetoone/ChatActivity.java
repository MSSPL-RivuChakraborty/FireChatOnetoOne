package com.massoftind.rnd.firechatonetoone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.massoftind.rnd.firechatonetoone.utils.LogPrinter;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        if(getIntent().hasExtra("groupID")){
            String groupID = getIntent().getStringExtra("groupID");
            LogPrinter.d("groupID",groupID);
        } else {
            finish();
        }
    }
}
