package com.massoftind.rnd.firechatonetoone.adapters;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.massoftind.rnd.firechatonetoone.R;
import com.massoftind.rnd.firechatonetoone.datamodal.LoginRegisterDatamodel;

import java.util.ArrayList;

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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_login_register, null);
        return new LoginRegisterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LoginRegisterViewHolder holder, int position) {
        holder.init(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class LoginRegisterViewHolder extends RecyclerView.ViewHolder{

        EditText editPassword;
        EditText editInput;
        TextView textView;
        TextInputLayout textInputLayoutPassword;
        TextInputLayout textInputLayoutInput;
        Button btn;
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
        }

        public void init(final int position){
            final LoginRegisterDatamodel rowItem = items.get(position);
            if(rowItem.isEditTextVisible()){
                if(rowItem.isPassWord()){
                    textInputLayoutPassword.setVisibility(View.VISIBLE);
                    textInputLayoutInput.setVisibility(View.GONE);
                    editPassword.setVisibility(View.VISIBLE);
                    editPassword.setVisibility(View.VISIBLE);
                    editPassword.setText(rowItem.getTextValue());
                    editPassword.setFocusable(true);
                    editPassword.setHint(rowItem.getHint());
                    if(rowItem.getError() != null && !rowItem.getError().equalsIgnoreCase("")){
                        editPassword.setError(rowItem.getError());
                    }
                    editPassword.setTextColor(rowItem.getEditTextColor());
                } else {
                    textInputLayoutPassword.setVisibility(View.GONE);
                    textInputLayoutInput.setVisibility(View.VISIBLE);
                    editInput.setText(rowItem.getTextValue());
                    editInput.setFocusable(true);
                    editInput.setHint(rowItem.getHint());
                    if(rowItem.getError() != null && !rowItem.getError().equalsIgnoreCase("")){
                        editInput.setError(rowItem.getError());
                    }
                    editInput.setTextColor(rowItem.getEditTextColor());
                    editInput.setInputType(rowItem.getInputType());
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
            } else {
                btn.setVisibility(View.GONE);
            }
        }

    }
}
