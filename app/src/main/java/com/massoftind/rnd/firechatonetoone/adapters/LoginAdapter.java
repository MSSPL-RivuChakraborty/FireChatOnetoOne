package com.massoftind.rnd.firechatonetoone.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.massoftind.rnd.firechatonetoone.R;
import com.massoftind.rnd.firechatonetoone.datamodal.ListRowDataModel;
import com.massoftind.rnd.firechatonetoone.ui.SetTypeFace;

import java.util.ArrayList;


/**
 * Created by developer on 11/4/16.
 */
public class LoginAdapter extends BaseAdapter {


    private Activity activity;
    ArrayList<ListRowDataModel> rowDataModelList;
    SetTypeFace typeFace;

    public LoginAdapter(Activity activity, ArrayList<ListRowDataModel> rowDataModelList) {
        this.activity = activity;
        typeFace = new SetTypeFace(activity);
        this.rowDataModelList = rowDataModelList;

    }

    @Override
    public int getCount() {
        return rowDataModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return rowDataModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        EditText edtRow;
//        LinearLayout linError;
//        ImageView ivErrorIcon;
//        TextView err_msg;
        TextView tvSubText, tvforgotpass;
        RelativeLayout rlBg, rlEdt, rlButton;
        TextView tvButtonText, headTxt;
    }

    ViewHolder viewHolder = null;
    LinearLayout linVisibleErrorLayout = null;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        final Integer pos = position;
        LayoutInflater inflater = null;
        //  final int type = getItemViewType(position);
        if (vi == null) {
            viewHolder = new ViewHolder();
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.register_list_row_cell, parent, false);
            viewHolder.edtRow = (EditText) vi.findViewById(R.id.edtRow);
            viewHolder.edtRow.setFocusable(true);
            viewHolder.rlEdt = (RelativeLayout) vi.findViewById(R.id.rlEdt);
            viewHolder.tvforgotpass = (TextView) vi.findViewById(R.id.tvforgotpass);
            vi.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) vi.getTag();
        }
//        final EditText edtRow = viewHolder.edtRow;
//        edtRow.setTag(pos);
//        ListRowDataModel listRowDataModel = rowDataModelList.get(position);
//        typeFace.setTypeFace(viewHolder.edtRow, "r");
//
//        viewHolder.rlEdt.setVisibility(View.VISIBLE);
//        edtRow.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                //Log.d"aaa", "BEFORE" + s);
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //Log.d"aaa", "OnText" + s);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//               /* //Log.d"aaa", "After"+pos + s);
//               rowDataModelList.get(pos).setDataText(s.toString());*/
//                //Log.d"aaa", "After" + s);
//                int taggedPos = (int) edtRow.getTag();
//                //Log.d"aaa", " taggedPos : " + taggedPos);
//                rowDataModelList.get(taggedPos).setDataText(s.toString());
//            }
//        });
//        // viewHolder.edtRow.setTag(vi);
//        viewHolder.edtRow.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//
//            }
//        });
//        edtRow.setText(listRowDataModel.getDataText());
//        edtRow.setHint(listRowDataModel.getHintText());
//        viewHolder.edtRow.setInputType(listRowDataModel.getInputType());
//        viewHolder.tvforgotpass.setText(listRowDataModel.getSubText());
//        viewHolder.tvforgotpass.setVisibility(listRowDataModel.getSubTextVisibility());
//        typeFace.setTypeFace(viewHolder.tvforgotpass, "r");
//        viewHolder.tvforgotpass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity, ForgotPasswordActivity.class);
//                activity.startActivity(intent);
//                activity.finish();
//            }
//        });

        return vi;
    }

}
