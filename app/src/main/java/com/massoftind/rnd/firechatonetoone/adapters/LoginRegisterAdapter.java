package com.massoftind.rnd.firechatonetoone.adapters;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.massoftind.rnd.firechatonetoone.R;
import com.massoftind.rnd.firechatonetoone.datamodal.LoginRegisterDatamodel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by developer on 14/9/16.
 */
public class LoginRegisterAdapter extends RecyclerView.Adapter<LoginRegisterAdapter.LoginRegisterViewHolder> {

    Context context;
    ArrayList<LoginRegisterDatamodel> items = new ArrayList<LoginRegisterDatamodel>();

    public LoginRegisterAdapter(Context context, ArrayList<LoginRegisterDatamodel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public LoginRegisterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_login_register, parent, false);
        itemView.getLayoutParams ().width = parent.getWidth ();
        Log.d("recycler_oncreate",""+items.size());
        return new LoginRegisterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LoginRegisterViewHolder holder, int position) {
        holder.init(position);
    }

    @Override
    public int getItemCount() {
        Log.d("recycler_size",""+items.size());
        return items.size();
    }

    public class LoginRegisterViewHolder extends RecyclerView.ViewHolder{

        EditText editPassword;
        EditText editInput;
        TextView textView;
        TextInputLayout textInputLayoutPassword;
        TextInputLayout textInputLayoutInput;
        Button btn;
        CircleImageView profile_image;
        View row;

        public LoginRegisterViewHolder(View itemView) {
            super(itemView);
            row = itemView;
            editPassword = (EditText) itemView.findViewById(R.id.editPassword);
            editInput = (EditText) itemView.findViewById(R.id.editInput);
            textView = (TextView) itemView.findViewById(R.id.textView);
            textInputLayoutPassword = (TextInputLayout) itemView.findViewById(R.id.textInputLayoutPassword);
            textInputLayoutInput = (TextInputLayout) itemView.findViewById(R.id.textInputLayoutInput);
            btn = (Button) itemView.findViewById(R.id.btn);
            profile_image = (CircleImageView) itemView.findViewById(R.id.profile_image);
        }

        public void init(final int position){
            final LoginRegisterDatamodel rowItem = items.get(position);
            Log.d("recycler_init",position+" "+rowItem);
            if(rowItem.isEditTextVisible()){
                if(rowItem.isPassWord()){
                    textInputLayoutPassword.setVisibility(View.VISIBLE);
                    textInputLayoutInput.setVisibility(View.GONE);
                    editPassword.setVisibility(View.VISIBLE);
                    editPassword.setVisibility(View.VISIBLE);
                    editPassword.setText(rowItem.getTextValue());
                    editPassword.setFocusable(true);
                    textInputLayoutPassword.setHint(rowItem.getHint());
//                    textInputLayoutPassword.setHintTextAppearance(R.style.TextLabel);
//                    editPassword.setHint(rowItem.getHint());
                    editPassword.setHintTextColor(rowItem.getEditTextColor());
                    if(rowItem.getError() != null && !rowItem.getError().equalsIgnoreCase("")){
                        textInputLayoutPassword.setError(rowItem.getError());
                    }
                    editPassword.setTextColor(rowItem.getEditTextColor());
                    editPassword.addTextChangedListener(new TextWatcher() {
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
                    Log.d("recycler_init","password "+position+" "+editPassword.getVisibility()+" "+textInputLayoutPassword.getVisibility());
                } else {
                    textInputLayoutPassword.setVisibility(View.GONE);
                    textInputLayoutInput.setVisibility(View.VISIBLE);
                    editInput.setText(rowItem.getTextValue());
                    editInput.setFocusable(true);
                    textInputLayoutInput.setHint(rowItem.getHint());
//                    textInputLayoutInput.setHintTextAppearance(R.style.TextLabel);
//                    editInput.setHint(rowItem.getHint());
                    editInput.setHintTextColor(rowItem.getEditTextColor());
                    if(rowItem.getError() != null && !rowItem.getError().equalsIgnoreCase("")){
                        textInputLayoutInput.setError(rowItem.getError());
                    }
                    editInput.setTextColor(rowItem.getEditTextColor());
                    editInput.setInputType(rowItem.getInputType());
                    editInput.addTextChangedListener(new TextWatcher() {
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
                    Log.d("recycler_init","input "+position+" "+editInput.getVisibility()+" "+textInputLayoutInput.getVisibility());
                }
            } else {
                textInputLayoutPassword.setVisibility(View.GONE);
                textInputLayoutInput.setVisibility(View.GONE);
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
                textView.setVisibility(View.VISIBLE);
                textView.setText(rowItem.getText());
                textView.setTextColor(rowItem.getTextViewColor());
            } else {
                textView.setVisibility(View.GONE);
            }
            if(rowItem.isButtonVisible()){
                btn.setVisibility(View.VISIBLE);
                btn.setText(rowItem.getButtonText());
                if(null != rowItem.getButtonOnClickListener()){
                    btn.setOnClickListener(rowItem.getButtonOnClickListener());
                }
                Log.d("recycler_init","button "+position+" "+btn.getVisibility());
            } else {
                btn.setVisibility(View.GONE);
            }
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
