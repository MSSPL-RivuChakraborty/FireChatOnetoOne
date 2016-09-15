package com.massoftind.rnd.firechatonetoone.datamodal;

import java.io.Serializable;

/**
 * Created by developer on 7/4/16.
 */
public class ListRowDataModel implements Serializable {

    String hint="";
    String firstname="";
    String surname="";
    String email="";
    String dataText = "";
    int rowType = -1;
    String hintText = "";
    String errMsg = "";
    int leftDrawableId = -1;
    private String title;


    private int icon;



    int leftDrawableVisibility = -1;
    int errorVisibility = -1;

    String subText="";
    int inputType = -1;

    int rowBgImageId = -1;
    String bgColor="";
    String bgColor2="";

    int subTextVisibility=-1;
    int buttonOpacity = -1;

    String textColor="";
    String extraPassText="";

    int subEdittextVisibility=-1;

    String edittext="";


    public int getSubEdittextVisibility() {
        return subEdittextVisibility;
    }

    public void setSubEdittextVisibility(int subEdittextVisibility) {
        this.subEdittextVisibility = subEdittextVisibility;
    }


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdittext() {
        return edittext;
    }

    public void setEdittext(String edittext) {
        this.edittext = edittext;
    }

    public int getButtonOpacity() {
        return buttonOpacity;
    }

    public void setButtonOpacity(int buttonOpacity) {
        this.buttonOpacity = buttonOpacity;
    }
    public int getLeftDrawableVisibility() {
        return leftDrawableVisibility;
    }

    public void setLeftDrawableVisibility(int leftDrawableVisibility) {
        this.leftDrawableVisibility = leftDrawableVisibility;
    }


    public String getSubText() {
        return subText;
    }
    public int getLeftDrawableId() {
        return leftDrawableId;
    }

    public void setLeftDrawableId(int leftDrawableId) {
        this.leftDrawableId = leftDrawableId;
    }
    public void setSubText(String subText) {
        this.subText = subText;
    }


    public int getSubTextVisibility() {
        return subTextVisibility;
    }

    public void setSubTextVisibility(int subTextVisibility) {
        this.subTextVisibility = subTextVisibility;
    }



    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getBgColor2() {
        return bgColor2;
    }

    public void setBgColor2(String bgColor2) {
        this.bgColor2 = bgColor2;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getExtraPassText() {
        return extraPassText;
    }

    public void setExtraPassText(String extraPassText) {
        this.extraPassText = extraPassText;
    }


    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }



    public int getRowBgImageId() {
        return rowBgImageId;
    }

    public void setRowBgImageId(int rowBgImageId) {
        this.rowBgImageId = rowBgImageId;
    }


    public int getRowType() {
        return rowType;
    }

    public void setRowType(int rowType) {
        this.rowType = rowType;
    }



    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public String getDataText() {
        return dataText;
    }

    public void setDataText(String dataText) {
        this.dataText = dataText;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getErrorVisibility() {
        return errorVisibility;
    }

    public void setErrorVisibility(int errorVisibility) {
        this.errorVisibility = errorVisibility;
    }


    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
