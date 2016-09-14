package com.massoftind.rnd.firechatonetoone.datamodal;

import android.graphics.Color;
import android.text.InputType;
import android.view.View;

import com.massoftind.rnd.firechatonetoone.interfaces.OnRecyclerViewCellClick;

/**
 * Created by developer on 14/9/16.
 */
public class LoginRegisterDatamodel {
    String text;
    String hint;
    String textValue;
    String error;
    boolean editTextVisible = true;
    boolean textViewVisible = false;
    int inputType = InputType.TYPE_CLASS_TEXT;
    boolean isPassWord = false;
    int editTextColor = Color.BLACK;
    int textViewColor = Color.BLACK;
    OnRecyclerViewCellClick onClick = null;
    boolean buttonVisible;
    String buttonText;
    View.OnClickListener buttonOnClickListener;

    public LoginRegisterDatamodel() {
    }

    public LoginRegisterDatamodel(String text, String hint, String textValue, String error,
                                  boolean editTextVisible, boolean textViewVisible, int inputType, boolean isPassWord,
                                  int editTextColor, int textViewColor, OnRecyclerViewCellClick onClick, boolean buttonVisible,
                                  String buttonText, View.OnClickListener buttonOnClickListener) {
        this.text = text;
        this.hint = hint;
        this.textValue = textValue;
        this.error = error;
        this.editTextVisible = editTextVisible;
        this.textViewVisible = textViewVisible;
        this.inputType = inputType;
        this.isPassWord = isPassWord;
        this.editTextColor = editTextColor;
        this.textViewColor = textViewColor;
        this.onClick = onClick;
        this.buttonVisible = buttonVisible;
        this.buttonText = buttonText;
        this.buttonOnClickListener = buttonOnClickListener;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isEditTextVisible() {
        return editTextVisible;
    }

    public void setEditTextVisible(boolean editTextVisible) {
        this.editTextVisible = editTextVisible;
    }

    public boolean isTextViewVisible() {
        return textViewVisible;
    }

    public void setTextViewVisible(boolean textViewVisible) {
        this.textViewVisible = textViewVisible;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public boolean isPassWord() {
        return isPassWord;
    }

    public void setPassWord(boolean passWord) {
        isPassWord = passWord;
    }

    public int getEditTextColor() {
        return editTextColor;
    }

    public void setEditTextColor(int editTextColor) {
        this.editTextColor = editTextColor;
    }

    public int getTextViewColor() {
        return textViewColor;
    }

    public void setTextViewColor(int textViewColor) {
        this.textViewColor = textViewColor;
    }

    public OnRecyclerViewCellClick getOnClick() {
        return onClick;
    }

    public void setOnClick(OnRecyclerViewCellClick onClick) {
        this.onClick = onClick;
    }

    public boolean isButtonVisible() {
        return buttonVisible;
    }

    public void setButtonVisible(boolean buttonVisible) {
        this.buttonVisible = buttonVisible;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public View.OnClickListener getButtonOnClickListener() {
        return buttonOnClickListener;
    }

    public void setButtonOnClickListener(View.OnClickListener buttonOnClickListener) {
        this.buttonOnClickListener = buttonOnClickListener;
    }
}
