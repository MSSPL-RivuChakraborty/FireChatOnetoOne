package com.massoftind.rnd.firechatonetoone.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.massoftind.rnd.firechatonetoone.R;
import com.massoftind.rnd.firechatonetoone.datamodal.User;
import com.massoftind.rnd.firechatonetoone.utils.ItemOffsetDecoration;
import com.massoftind.rnd.firechatonetoone.utils.LogPrinter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersListFragment extends Fragment {


    private static final String USERS_CHILD = "users";
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

        public UserViewHolder(View itemView) {
            super(itemView);
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
            layoutManager.setStackFromEnd(true);

            auth = FirebaseAuth.getInstance();
            user=auth.getCurrentUser();

            mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
            try {
                mFirebaseDatabaseReference.keepSynced(true);
            } catch (Exception e){}

            DatabaseReference childReference = mFirebaseDatabaseReference.child(USERS_CHILD);
            childReference.orderByChild("firstName");

            mFirebaseAdapter = new FirebaseRecyclerAdapter<User,
                    UserViewHolder>(
                    User.class,
                    R.layout.item_user,
                    UserViewHolder.class,
                    childReference) {



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
            };

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


}
