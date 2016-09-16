package com.massoftind.rnd.firechatonetoone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.massoftind.rnd.firechatonetoone.datamodal.firebase.Device;
import com.massoftind.rnd.firechatonetoone.datamodal.LoginRegisterDatamodel;
import com.massoftind.rnd.firechatonetoone.datamodal.firebase.User;
import com.massoftind.rnd.firechatonetoone.utils.LogPrinter;
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
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

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
                            validateAndRegister();
                        }
                    }, false, null, null);
                    break;
            }
            if(null != loginRegisterDatamodel){
                registerItems.add(loginRegisterDatamodel);
            }
        }
    }

    private void validateAndRegister() {
        final String email = registerItems.get(3).getTextValue();
        final String password = registerItems.get(4).getTextValue();
        final String confirmPassword = registerItems.get(5).getTextValue();
        final String mobile = registerItems.get(6).getTextValue();
        final String fname = registerItems.get(1).getTextValue();
        final String lname = registerItems.get(2).getTextValue();

        SharedPreferences preferences = getSharedPreferences("fcm",MODE_PRIVATE);
        final String token = preferences.getString("fcm_token","");

        final Bitmap profilePicBmp = registerItems.get(0).getBitmapProfile();

        if(fname.trim().equalsIgnoreCase("")){
            registerItems.get(1).setError("First Name is required");
            adapter.notifyItemChanged(1);
            return;
        }
        if(email.trim().equalsIgnoreCase("")){
            registerItems.get(3).setError("EMail is required");
            adapter.notifyItemChanged(3);
            return;
        }
        if(password.trim().equalsIgnoreCase("")){
            registerItems.get(4).setError("Password is required");
            adapter.notifyItemChanged(4);
            return;
        }
        if(confirmPassword.trim().equalsIgnoreCase("")){
            registerItems.get(5).setError("Confirm your Password");
            adapter.notifyItemChanged(5);
            return;
        }
        if(password.compareTo(confirmPassword)!=0){
            registerItems.get(5).setError("Password and Confirm Password doesn't match");
            adapter.notifyItemChanged(5);
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()){
            registerItems.get(3).setError("Please Enter a valid EMail Address");
            adapter.notifyItemChanged(3);
            return;
        }

        if(password.trim().length()<6){
            registerItems.get(4).setError("Password must be atleat 6 characters long");
            adapter.notifyItemChanged(4);
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {

                    public String profilePicUrl = "";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            LogPrinter.e("RegisterActivity","Authentication failed - " + task.getException().getMessage(),task.getException());

                        } else {

                            mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

                            final String userId = auth.getCurrentUser().getUid();


                            if(profilePicBmp != null){
                                profilePicUrl = uploadBmpToStorage(userId);

                                FirebaseStorage storage = FirebaseStorage.getInstance();
                                StorageReference storageRef = storage.getReferenceFromUrl("gs://firechat-one-to-one.appspot.com");

                                StorageReference profileImageRef = storageRef.child("profile_images/profile_"+userId+".jpg");


                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                profilePicBmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                byte[] data = baos.toByteArray();

                                UploadTask uploadTask = profileImageRef.putBytes(data);
                                uploadTask.addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Handle unsuccessful uploads
                                        Toast.makeText(getBaseContext(),"Invalid Picture",Toast.LENGTH_LONG).show();
                                        LogPrinter.e("RegisterActivity","Pic Upload Error "+exception.getMessage(),exception);
                                        mFirebaseDatabaseReference.child("users")
                                                .child(userId).setValue(new User(userId, fname, lname, email, mobile, profilePicUrl));
                                        mFirebaseDatabaseReference.child("devices")
                                                .child(token).setValue(new Device(RegisterActivity.this,userId,token));
                                    }
                                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                        profilePicUrl = downloadUrl.toString();
                                        mFirebaseDatabaseReference.child("users")
                                                .child(userId).setValue(new User(userId, fname, lname, email, mobile, profilePicUrl));
                                        mFirebaseDatabaseReference.child("devices")
                                                .child(token).setValue(new Device(RegisterActivity.this,userId,token));

                                    }
                                });
                            } else {
                                mFirebaseDatabaseReference.child("users")
                                        .child(userId).setValue(new User(userId, fname, lname, email, mobile, profilePicUrl));
                                mFirebaseDatabaseReference.child("devices")
                                        .child(token).setValue(new Device(RegisterActivity.this,userId,token));
                            }

                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finishAffinity();
                        }
                    }
                });

    }

    private String uploadBmpToStorage(String userId) {

        return null;
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
