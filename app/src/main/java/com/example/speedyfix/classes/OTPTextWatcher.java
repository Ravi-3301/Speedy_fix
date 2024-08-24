package com.example.speedyfix.classes;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class OTPTextWatcher implements TextWatcher {

    private EditText currentEditText;
    private EditText nextEditText;

    public OTPTextWatcher(EditText currentEditText, EditText nextEditText) {
        this.currentEditText = currentEditText;
        this.nextEditText = nextEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 1 && nextEditText != null) {
            nextEditText.requestFocus();
        }
    }
}

