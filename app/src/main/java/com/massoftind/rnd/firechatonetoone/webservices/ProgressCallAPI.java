package com.massoftind.rnd.firechatonetoone.webservices;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressCallAPI {

	private ProgressDialog progressDialog;
	private Context currentContext;
	
	public ProgressCallAPI(Context ct)
	{
		currentContext=ct;
	}
	
	public void setProgress()
	{
		progressDialog=new ProgressDialog(currentContext);
	}
	
	public void showProgressDialog() {
		if (!progressDialog.isShowing())
			progressDialog.show();
	}

	public void hideProgressDialog() {
		if (progressDialog.isShowing())
			progressDialog.hide();
	}
}
