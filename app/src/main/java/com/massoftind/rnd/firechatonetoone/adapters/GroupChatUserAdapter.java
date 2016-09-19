package com.massoftind.rnd.firechatonetoone.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.massoftind.rnd.firechatonetoone.R;
import com.massoftind.rnd.firechatonetoone.datamodal.LoginRegisterDatamodel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by developer on 17/9/16.
 */
public class GroupChatUserAdapter extends RecyclerView.Adapter<GroupChatUserAdapter.GroupUserViewHolder> {

    Context context;
    ArrayList<LoginRegisterDatamodel> items = new ArrayList<LoginRegisterDatamodel>();

    public GroupChatUserAdapter(Context context, ArrayList<LoginRegisterDatamodel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public GroupUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_group_chat, parent, false);
        itemView.getLayoutParams ().width = parent.getWidth ();
        Log.d("recycler_oncreate",""+items.size());
        return new GroupUserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroupUserViewHolder holder, int position) {
        holder.init(position);
    }

    @Override
    public int getItemCount() {
        Log.d("recycler_size",""+items.size());
        return items.size();
    }

    public class GroupUserViewHolder extends RecyclerView.ViewHolder{

        EditText edit_group_name;
        TextView textView_group_name;
        CircleImageView profile_image;
        ImageView image_add_user;
        View row;

        public GroupUserViewHolder(View itemView) {
            super(itemView);
            row = itemView;
            edit_group_name = (EditText) itemView.findViewById(R.id.edit_group_name);
            textView_group_name = (TextView) itemView.findViewById(R.id.textView_group_name);
            profile_image = (CircleImageView) itemView.findViewById(R.id.profile_image);
            image_add_user = (ImageView)itemView.findViewById(R.id.image_add_user);
        }

        public void init(final int position){
            final LoginRegisterDatamodel rowItem = items.get(position);
            Log.d("recycler_init",position+" "+rowItem);
            if(rowItem.isEditTextVisible()){
                if(rowItem.isPassWord()){
                    textView_group_name.setVisibility(View.VISIBLE);
                    textView_group_name.setText(rowItem.getTextValue());
                    edit_group_name.setVisibility(View.VISIBLE);
                    edit_group_name.setFocusable(true);
                    edit_group_name.setHintTextColor(rowItem.getEditTextColor());
                    if(rowItem.getError() != null && !rowItem.getError().equalsIgnoreCase("")){
                    }
                    edit_group_name.setTextColor(rowItem.getEditTextColor());
                    edit_group_name.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            rowItem.setTextValue(editable.toString());
                        }
                    });
//                    Log.d("recycler_init","password "+position+" "+editPassword.getVisibility()+" "+textInputLayoutPassword.getVisibility());
                } else {
                    edit_group_name.setText(rowItem.getTextValue());
                    edit_group_name.setFocusable(true);
                    edit_group_name.setHintTextColor(rowItem.getEditTextColor());
                    if(rowItem.getError() != null && !rowItem.getError().equalsIgnoreCase("")){
                    }
                    edit_group_name.setTextColor(rowItem.getEditTextColor());
                    edit_group_name.setInputType(rowItem.getInputType());
                    edit_group_name.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            rowItem.setTextValue(editable.toString());
                        }
                    });
//                    Log.d("recycler_init","input "+position+" "+editInput.getVisibility()+" "+textInputLayoutInput.getVisibility());
                }
            } else {
                if(rowItem.getOnClick() != null){
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rowItem.getOnClick().onCellClick(view,position);
                        }
                    });
                }
            }
            if(rowItem.isTextViewVisible()){
                textView_group_name.setVisibility(View.VISIBLE);
                textView_group_name.setText(rowItem.getText());
                textView_group_name.setTextColor(rowItem.getTextViewColor());
            } else {
                textView_group_name.setVisibility(View.GONE);
            }
            /*if(rowItem.isButtonVisible()){
                btn.setVisibility(View.VISIBLE);
                btn.setText(rowItem.getButtonText());
                if(null != rowItem.getButtonOnClickListener()){
                    btn.setOnClickListener(rowItem.getButtonOnClickListener());
                }
//                Log.d("recycler_init","button "+position+" "+btn.getVisibility());
            } else {
//                btn.setVisibility(View.GONE);
            }*/
            if(rowItem.isProfileImageVisible()){
                profile_image.setVisibility(View.VISIBLE);
                if(rowItem.getBitmapProfile() != null){
                    profile_image.setImageBitmap(rowItem.getBitmapProfile());
                }
                profile_image.setOnClickListener(rowItem.getProfileImageOnClickListener());
            } else {
                profile_image.setVisibility(View.GONE);
            }
        }
    }
}
