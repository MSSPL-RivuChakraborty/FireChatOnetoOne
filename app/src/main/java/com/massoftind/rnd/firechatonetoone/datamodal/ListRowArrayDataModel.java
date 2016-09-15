package com.massoftind.rnd.firechatonetoone.datamodal;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ListRowArrayDataModel implements Serializable
{
	ArrayList<ListRowDataModel> dataModelList;

	public ArrayList<ListRowDataModel> getDataModelList() {
		return dataModelList;
	}

	public void setDataModelList(ArrayList<ListRowDataModel> dataModelList) {
		this.dataModelList = dataModelList;
	}
	
}
