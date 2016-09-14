package com.massoftind.rnd.firechatonetoone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
import com.massoftind.rnd.firechatonetoone.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private RecyclerView loginRecyclerView;
    private LoginRegisterAdapter adapter;
    private ArrayList<LoginRegisterDatamodel> registerItems;

    private static final int REQUEST_CAMERA = 101;
    private static final int SELECT_FILE = 102;

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
//                            Toast.makeText(getBaseContext(),"Yet to Implement",Toast.LENGTH_LONG).show();
                            selectImage();
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

    public String getRealPathFromURI(Context context, Uri contentUri) { // get absolute file path from the galary image uri
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        "UsersApp_" + System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String fileAddress = destination.getAbsolutePath();
                registerItems.get(0).setBitmapProfile(thumbnail);
                adapter.notifyItemChanged(0);
            } else if (requestCode == SELECT_FILE) {
                Bitmap bm = null;
                if (data != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                        String fileAddress = getRealPathFromURI(this, data.getData());
                        if(null != bm){
                            registerItems.get(0).setBitmapProfile(bm);
                            adapter.notifyItemChanged(0);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                attachImg.setImageBitmap(bm);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        selectImage();
    }

    public void selectImage(){
        Utils.hideKeyboard(this);
        final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utils.checkPermission(RegisterActivity.this);
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
