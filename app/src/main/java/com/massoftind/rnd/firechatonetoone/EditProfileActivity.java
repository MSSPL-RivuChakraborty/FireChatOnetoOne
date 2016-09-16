package com.massoftind.rnd.firechatonetoone;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.massoftind.rnd.firechatonetoone.adapters.LoginRegisterAdapter;
import com.massoftind.rnd.firechatonetoone.datamodal.Device;
import com.massoftind.rnd.firechatonetoone.datamodal.LoginRegisterDatamodel;
import com.massoftind.rnd.firechatonetoone.datamodal.User;
import com.massoftind.rnd.firechatonetoone.utils.LogPrinter;
import com.massoftind.rnd.firechatonetoone.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class EditProfileActivity extends AppCompatActivity {

    private RecyclerView loginRecyclerView;
    private LoginRegisterAdapter adapter;
    private ArrayList<LoginRegisterDatamodel> editItems;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabaseReference;

    private static final int REQUEST_CAMERA = 101;
    private static final int SELECT_FILE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        loginRecyclerView = (RecyclerView) findViewById(R.id.loginRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        loginRecyclerView.setLayoutManager(layoutManager);

        editItems = new ArrayList<LoginRegisterDatamodel>();

        init();

        adapter = new LoginRegisterAdapter(this,editItems);
        loginRecyclerView.setAdapter(adapter);

    }

    private void init() {

        for(int i=0;i<5;i++){
            LoginRegisterDatamodel loginRegisterDatamodel = null;
            switch (i){
                case 0:
                    //profile pic
                    loginRegisterDatamodel = new LoginRegisterDatamodel("", "", "", "",
                            false, false, 0, false, 0, 0, null, false, "", null, true, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Upload Profile Image
//                            Toast.makeText(getBaseContext(),"Yet to Implement",Toast.LENGTH_LONG).show();
                            selectImage();
                        }
                    },null);
                    break;
                case 1:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","First Name","","",true,false, InputType.TYPE_CLASS_TEXT,
                            false, Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 2:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","Last Name","","",true,false, InputType.TYPE_CLASS_TEXT,
                            false,Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 3:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("","Mobile","","",true,false, InputType.TYPE_CLASS_NUMBER,
                            false,Color.WHITE,0,null,false,"",null,false,null,null);
                    break;
                case 4:
                    loginRegisterDatamodel = new LoginRegisterDatamodel("", "", "", "", false, false, 0, false, 0, 0,
                            null, true, "Save Profile", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            validateAndEdit();
                        }
                    }, false, null, null);
                    break;
            }
            if(null != loginRegisterDatamodel){
                editItems.add(loginRegisterDatamodel);
            }
        }

    }

    private void validateAndEdit() {

        final String mobile = editItems.get(3).getTextValue();
        final String fname = editItems.get(1).getTextValue();
        final String lname = editItems.get(2).getTextValue();

        SharedPreferences preferences = getSharedPreferences("fcm",MODE_PRIVATE);
        final String token = preferences.getString("fcm_token","");

        final Bitmap profilePicBmp = editItems.get(0).getBitmapProfile();

        if(fname.trim().equalsIgnoreCase("")){
            editItems.get(1).setError("First Name is required");
            adapter.notifyItemChanged(1);
            return;
        }

        final String userId = auth.getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        
    }

    private void selectImage() {

        Utils.hideKeyboard(this);
        final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utils.checkPermission(EditProfileActivity.this);
                if (items[item].equals("Take Photo")) {
//                    userChoosenTask = "Take Photo";
                    if (result) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CAMERA);
                    }
                } else if (items[item].equals("Choose from Library")) {
//                    userChoosenTask = "Choose from Library";
                    if (result) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
                    }
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }
}
