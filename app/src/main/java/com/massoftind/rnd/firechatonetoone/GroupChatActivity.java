package com.massoftind.rnd.firechatonetoone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.massoftind.rnd.firechatonetoone.adapters.GroupChatUserAdapter;
import com.massoftind.rnd.firechatonetoone.datamodal.LoginRegisterDatamodel;
import com.massoftind.rnd.firechatonetoone.datamodal.firebase.Group;
import com.massoftind.rnd.firechatonetoone.datamodal.firebase.GroupMembers;
import com.massoftind.rnd.firechatonetoone.datamodal.firebase.User;
import com.massoftind.rnd.firechatonetoone.fragments.UsersListFragment;
import com.massoftind.rnd.firechatonetoone.interfaces.OnRecyclerViewCellClick;
import com.massoftind.rnd.firechatonetoone.utils.ItemOffsetDecoration;
import com.massoftind.rnd.firechatonetoone.utils.LogPrinter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupChatActivity extends AppCompatActivity {

    private RecyclerView loginRecyclerView;
    private GroupChatUserAdapter adapter;
    private ArrayList<LoginRegisterDatamodel> groupChatItems;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener authListener;
    private String token;
    private DatabaseReference mFirebaseDatabaseReference;

    private static final String USERS_CHILD = "users";
    private static final String GROUPS_CHILD = "groups";
    private static View view;
    private RecyclerView usersListRecyclerView;
    private FirebaseRecyclerAdapter<User, GroupChatUserAdapter.GroupUserViewHolder> mFirebaseAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        auth = FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        SharedPreferences preferences = getSharedPreferences("fcm",MODE_PRIVATE);
        token = preferences.getString("fcm_token","");

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity

                    mFirebaseDatabaseReference.child("devices")
                            .child(token).removeValue();


                    startActivity(new Intent(GroupChatActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        loginRecyclerView = (RecyclerView) findViewById(R.id.loginRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        loginRecyclerView.setLayoutManager(layoutManager);

        groupChatItems = new ArrayList<LoginRegisterDatamodel>();

        adapter = new GroupChatUserAdapter(this,groupChatItems);
        loginRecyclerView.setAdapter(adapter);

    }
}
