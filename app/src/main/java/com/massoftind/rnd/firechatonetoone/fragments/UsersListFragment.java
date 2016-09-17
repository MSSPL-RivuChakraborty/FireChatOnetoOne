package com.massoftind.rnd.firechatonetoone.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.massoftind.rnd.firechatonetoone.ChatActivity;
import com.massoftind.rnd.firechatonetoone.R;
import com.massoftind.rnd.firechatonetoone.datamodal.firebase.Group;
import com.massoftind.rnd.firechatonetoone.datamodal.firebase.GroupMembers;
import com.massoftind.rnd.firechatonetoone.datamodal.firebase.User;
import com.massoftind.rnd.firechatonetoone.utils.ItemOffsetDecoration;
import com.massoftind.rnd.firechatonetoone.utils.LogPrinter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersListFragment extends Fragment {


    private static final String USERS_CHILD = "users";
    private static final String GROUPS_CHILD = "groups";
    private static UsersListFragment instance = null;
    private static View view;
    private RecyclerView usersListRecyclerView;
    private FragmentActivity activity;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<User, UserViewHolder> mFirebaseAdapter;
    private LinearLayoutManager layoutManager;

    public UsersListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_users_list, container, false);
        return view;
    }

    public static UsersListFragment getInstance() {
        if(null == instance){
            instance = new UsersListFragment();
        }
        return instance;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        de.hdodenhof.circleimageview.CircleImageView profileImage;
        TextView userName;
        TextView userEmail;
        View itemView;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            profileImage = (CircleImageView) itemView.findViewById(R.id.profileImage);
            userName = (TextView) itemView.findViewById(R.id.userName);
            userEmail = (TextView) itemView.findViewById(R.id.userEmail);

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
        activity = getActivity();

        if(null != view){
            usersListRecyclerView = (RecyclerView) view.findViewById(R.id.usersListRecyclerView);
            usersListRecyclerView.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.dimen.margin_10_unit));


            layoutManager = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);
//            usersListRecyclerView.setLayoutManager(layoutManager);

            auth = FirebaseAuth.getInstance();
            user=auth.getCurrentUser();

            mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
            try {
                mFirebaseDatabaseReference.keepSynced(true);
            } catch (Exception e){}

            Query childReference =  mFirebaseDatabaseReference.child(USERS_CHILD).
            orderByChild("firstName");

            mFirebaseAdapter = new MyFirebaseAdapter(
                    User.class,
                    R.layout.item_user,
                    UserViewHolder.class,
                    childReference) /*{



                @Override
                protected void populateViewHolder(UserViewHolder viewHolder,
                                                  User user, int position) {
                    LogPrinter.w("populateViewHolder","populateViewHolder");
//                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    viewHolder.userName.setText(user.getFirstName()+" "+user.getLastName());
                    viewHolder.userEmail.setText(user.getEmail());
                    if (user.getProfilePicUrl() == null || user.getProfilePicUrl().equalsIgnoreCase("")) {
                        viewHolder.profileImage
                                .setImageResource(R.drawable.ic_account_circle_black_36dp);
                    } else {
                        Glide.with(activity)
                                .load(user.getProfilePicUrl())
                                .into(viewHolder.profileImage);
                    }
                }
            }*/;
            mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    super.onItemRangeInserted(positionStart, itemCount);
//                    int friendlyMessageCount = mFirebaseAdapter.getItemCount();
//                    int lastVisiblePosition =
//                            layoutManager.findLastCompletelyVisibleItemPosition();
//                    // If the recycler view is initially being loaded or the
//                    // user is at the bottom of the list, scroll to the bottom
//                    // of the list to show the newly added message.
//                    if (lastVisiblePosition == -1 ||
//                            (positionStart >= (friendlyMessageCount - 1) &&
//                                    lastVisiblePosition == (positionStart - 1))) {
//                        usersListRecyclerView.scrollToPosition(positionStart);
//                    }
                }
            });

            usersListRecyclerView.setLayoutManager(layoutManager);
            usersListRecyclerView.setAdapter(mFirebaseAdapter);
        }

    }

    public class MyFirebaseAdapter extends FirebaseRecyclerAdapter<User,
            UserViewHolder>{



        public MyFirebaseAdapter(Class<User> modelClass, int modelLayout, Class<UserViewHolder> viewHolderClass, DatabaseReference ref) {
            super(modelClass, modelLayout, viewHolderClass, ref);
        }

        public MyFirebaseAdapter(Class<User> modelClass, int modelLayout, Class<UserViewHolder> viewHolderClass, Query ref) {
            super(modelClass, modelLayout, viewHolderClass, ref);
        }

        @Override
        protected void populateViewHolder(UserViewHolder viewHolder,
                                          final User user, int position) {
            LogPrinter.w("populateViewHolder","populateViewHolder"+user.getId());

//                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            if(user.getId().equalsIgnoreCase(UsersListFragment.this.user.getUid())){
                viewHolder.userName.setText(user.getFirstName() + " " + user.getLastName()+" (me)");
            } else {
                viewHolder.userName.setText(user.getFirstName() + " " + user.getLastName());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setTitle("Loading");
                        progressDialog.setMessage("Please Wait!");
                        progressDialog.show();

                        final DatabaseReference groupsRef = mFirebaseDatabaseReference.child(GROUPS_CHILD);
                        groupsRef.addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final String alternateGroupID = user.getId()+"-"+UsersListFragment.this.user.getUid();
                                final String groupID = UsersListFragment.this.user.getUid()+"-"+user.getId();
                                if(dataSnapshot.hasChild(groupID)){
                                    if(null != progressDialog && progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                    Intent chatIntent = new Intent(activity, ChatActivity.class);
                                    chatIntent.putExtra("groupID",groupID);
                                    startActivity(chatIntent);
                                } else if (dataSnapshot.hasChild(alternateGroupID)){
                                    if(null != progressDialog && progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                    Intent chatIntent = new Intent(activity, ChatActivity.class);
                                    chatIntent.putExtra("groupID",alternateGroupID);
                                    startActivity(chatIntent);
                                } else {
                                    ArrayList<String> users = new ArrayList<String>();
                                    users.add(UsersListFragment.this.user.getUid());
                                    users.add(user.getId());
                                    groupsRef.child(groupID).setValue(new Group(groupID,
                                            false, "", System.currentTimeMillis()+"", UsersListFragment.this.user.getUid(), "")/*, new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {

                                        }
                                    }*/);
                                    mFirebaseDatabaseReference.child("group_members").child(groupID+"_"+UsersListFragment.this.user.getUid()).setValue(new GroupMembers(groupID+"_"+UsersListFragment.this.user.getUid(),groupID,
                                            UsersListFragment.this.user.getUid(),true));
                                    mFirebaseDatabaseReference.child("group_members").child(groupID+"_"+user.getId()).setValue(new GroupMembers(groupID+"_"+user.getId(),groupID,
                                            user.getId(),true));

                                    if(null != progressDialog && progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                    Intent chatIntent = new Intent(activity, ChatActivity.class);
                                    chatIntent.putExtra("groupID",groupID);
                                    startActivity(chatIntent);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }


                        });
                    }
                });
            }
            viewHolder.userEmail.setText(user.getEmail());
            if (user.getProfilePicUrl() == null || user.getProfilePicUrl().equalsIgnoreCase("")) {
                viewHolder.profileImage
                        .setImageResource(R.drawable.ic_account_circle_black_36dp);
            } else {
                Glide.with(activity)
                        .load(user.getProfilePicUrl())
                        .into(viewHolder.profileImage);
            }
        }


    }


}
