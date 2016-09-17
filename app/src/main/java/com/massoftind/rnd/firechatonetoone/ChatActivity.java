package com.massoftind.rnd.firechatonetoone;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.massoftind.rnd.firechatonetoone.datamodal.firebase.Message;
import com.massoftind.rnd.firechatonetoone.datamodal.firebase.User;
import com.massoftind.rnd.firechatonetoone.utils.LogPrinter;
import com.massoftind.rnd.firechatonetoone.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ChatActivity extends AppCompatActivity {

    private FirebaseUser currentFirebaseUser;
    private DatabaseReference reference;
    private static final String MESSAGES_CHILD = "messages";
    private DatabaseReference chatReference;

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mMessageRecyclerView;
    private EditText mMessageEditText;
    private String groupID;
    private User currentUser;
    private FirebaseAnalytics firebaseAnalytics;

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;
        public TextView messengerTextView;
        public ImageView messengerImageView;
        public TextView messageTimeView;
        View itemView;

        public MessageViewHolder(View v) {
            super(v);
            itemView= v;
            messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
            messengerTextView = (TextView) itemView.findViewById(R.id.messengerTextView);
            messengerImageView = (ImageView) itemView.findViewById(R.id.messengerImageView);
            messageTimeView = (TextView) itemView.findViewById(R.id.messageTimeView);
        }

//        public void setView(View v){
//            itemView= v;
//            messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
//            messengerTextView = (TextView) itemView.findViewById(R.id.messengerTextView);
//            messengerImageView = (ImageView) itemView.findViewById(R.id.messengerImageView);
//            messageTimeView = (TextView) itemView.findViewById(R.id.messageTimeView);
//        }
    }

    private FirebaseRecyclerAdapter<Message, MessageViewHolder> mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mMessageRecyclerView = (RecyclerView) findViewById(R.id.mMessageRecyclerView);
        mMessageEditText = (EditText) findViewById(R.id.mMessageEditText);

        if(getIntent().hasExtra("groupID")){
            groupID = getIntent().getStringExtra("groupID");
            LogPrinter.d("groupID",groupID);

            currentFirebaseUser =FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference();
            chatReference = reference.child(MESSAGES_CHILD);

            reference.child("users").child(currentFirebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    LogPrinter.d("dataSnapshot",dataSnapshot.toString());
                    LogPrinter.d("dataSnapshot",dataSnapshot.getValue().toString());
                    String id = "",
                            firstName = "",
                            lastName = "",
                            email = "",
                            mobile = "",
                            profilePicUrl = "";
                    for (DataSnapshot single : dataSnapshot.getChildren()) {
                        LogPrinter.d("single",single.toString());

                        switch (single.getKey()){
                            case "id":
                                id = single.getValue().toString();
                                break;
                            case "firstName":
                                firstName = single.getValue().toString();
                                break;
                            case "lastName":
                                lastName = single.getValue().toString();
                                break;
                            case "email":
                                email = single.getValue().toString();
                                break;
                            case "mobile":
                                mobile = single.getValue().toString();
                                break;
                            case "profilePicUrl":
                                profilePicUrl = single.getValue().toString();
                                break;
                        }

                    }
                    currentUser = new User(id,
                            firstName,
                            lastName,
                            email,
                            mobile,
                            profilePicUrl
                    );
//                    if(dataSnapshot.getChildren().iterator().hasNext()){
//                        DataSnapshot userSnapshot = dataSnapshot.getChildren().iterator().next();
//                        LogPrinter.d("userSnapshot",userSnapshot.toString());
//                        LogPrinter.d("userSnapshot",userSnapshot.child("id").toString());
//                        currentUser = new User(userSnapshot.child("id").getValue().toString(),
//                                userSnapshot.child("firstName").getValue().toString(),
//                                userSnapshot.child("lastName").getValue().toString(),
//                                userSnapshot.child("email").getValue().toString(),
//                                userSnapshot.child("mobile").getValue().toString(),
//                                userSnapshot.child("profilePicUrl").getValue().toString()
//                        );
                        LogPrinter.d("currentUser",currentUser.toString());
//                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            ImageButton send = (ImageButton) findViewById(R.id.send);
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Message friendlyMessage = new
                            Message(groupID, currentFirebaseUser.getUid(),""+ System.currentTimeMillis(),mMessageEditText.getText().toString(),
                            "text","",null == currentUser?currentFirebaseUser.getEmail():currentUser.getFirstName(),null == currentUser?"":currentUser.getProfilePicUrl());
                    chatReference.push().setValue(friendlyMessage);
                    mMessageEditText.setText("");
                    Utils.hideKeyboard(ChatActivity.this);
                    mMessageRecyclerView.scrollToPosition(mFirebaseAdapter.getItemCount());
                }
            });

            mLinearLayoutManager = new LinearLayoutManager(this);
            mLinearLayoutManager.setStackFromEnd(true);

            Query chatQuery = chatReference.orderByChild("groupId").equalTo(groupID);

            mFirebaseAdapter = new FirebaseRecyclerAdapter<Message,
                    MessageViewHolder>(
                    Message.class,
                    R.layout.item_message,
                    MessageViewHolder.class,
                    chatQuery) {

                SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, MM-dd HH:mm");

                @Override
                public int getItemViewType(int position) {
                    return position;
                }

                @Override
                public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                    super.onCreateViewHolder(parent, viewType);



                    int viewid = 0;
                    if (currentFirebaseUser.getUid().equalsIgnoreCase(getItem(viewType).getSenderId())) {
                        viewid = R.layout.item_my_message;
                        LogPrinter.d("position",viewType+" my");
                    } else {
                        viewid = R.layout.item_message;
                        LogPrinter.d("position",viewType+" not my");
                    }

                    ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(viewid, parent, false);
                    MessageViewHolder viewHolder = new MessageViewHolder(view);
                    return viewHolder;
                }

                @Override
                protected void populateViewHolder(MessageViewHolder viewHolder,
                                                  Message friendlyMessage, int position) {
//                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
//                    LogPrinter.d("view",viewHolder.x+"");
                    viewHolder.messageTextView.setText(friendlyMessage.getMessage());
                    viewHolder.messengerTextView.setText(friendlyMessage.getSenderName());
                    String sentTime = friendlyMessage.getTimestamp();
                    try{
                        Date sentDate = new Date(Long.parseLong(sentTime));

                        sentTime = DATE_FORMAT.format(sentDate);
                    } catch (Exception e){}
                    viewHolder.messageTimeView.setText(sentTime);
                    if (friendlyMessage.getSenderImageUrl() == null || friendlyMessage.getSenderImageUrl().equalsIgnoreCase("")) {
                        viewHolder.messengerImageView
                                .setImageDrawable(ContextCompat
                                        .getDrawable(ChatActivity.this,
                                                R.drawable.ic_account_circle_black_36dp));
                    } else {
                        Glide.with(ChatActivity.this)
                                .load(friendlyMessage.getSenderImageUrl())
                                .into(viewHolder.messengerImageView);
                    }
                }
            };

            mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    super.onItemRangeInserted(positionStart, itemCount);
                    int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                    int lastVisiblePosition =
                            mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                    // If the recycler view is initially being loaded or the
                    // user is at the bottom of the list, scroll to the bottom
                    // of the list to show the newly added message.
                    if (lastVisiblePosition == -1 ||
                            (positionStart >= (friendlyMessageCount - 1) &&
                                    lastVisiblePosition == (positionStart - 1))) {
                        mMessageRecyclerView.scrollToPosition(positionStart);
                    }
                }
            });

            mMessageRecyclerView.setLayoutManager(mLinearLayoutManager);
            mMessageRecyclerView.setAdapter(mFirebaseAdapter);

            firebaseAnalytics = FirebaseAnalytics.getInstance(ChatActivity.this);
            //Logs an app event.
            firebaseAnalytics.logEvent("chat_open", getIntent().getExtras());
            //Sets whether analytics collection is enabled for this app on this device.
            firebaseAnalytics.setAnalyticsCollectionEnabled(true);
            //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds just for the fun
            firebaseAnalytics.setMinimumSessionDuration(20000);
            //Sets the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
            firebaseAnalytics.setSessionTimeoutDuration(500);
            firebaseAnalytics.setUserId(currentFirebaseUser.getUid());


        } else {
            finish();
        }
    }
}
