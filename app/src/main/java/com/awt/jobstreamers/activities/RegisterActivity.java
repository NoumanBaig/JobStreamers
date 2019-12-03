package com.awt.jobstreamers.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.awt.jobstreamers.R;
import com.awt.jobstreamers.utils.AlertDialogBox;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements View.OnFocusChangeListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.edt_userName)
    EditText editText_username;
    @BindView(R.id.edt_email)
    EditText editText_email;
    @BindView(R.id.edt_mobile)
    EditText editText_mobile;
    @BindView(R.id.edt_password)
    EditText editText_password;
    @BindView(R.id.edt_con_password)
    EditText editText_con_password;
    @BindView(R.id.view_userName)
    View view_userName;
    @BindView(R.id.view_email)
    View view_email;
    @BindView(R.id.view_mobile)
    View view_mobile;
    @BindView(R.id.view_password)
    View view_password;
    @BindView(R.id.view_con_password)
    View view_con_password;
    @BindView(R.id.checkbox_password)
    CheckBox checkBox_password;
    @BindView(R.id.checkbox_con_password)
    CheckBox checkbox_con_password;
    @BindView(R.id.checkbox_agree)
    CheckBox checkbox_agree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        editText_username.setOnFocusChangeListener(this);
        editText_email.setOnFocusChangeListener(this);
        editText_mobile.setOnFocusChangeListener(this);
        editText_password.setOnFocusChangeListener(this);
        editText_con_password.setOnFocusChangeListener(this);
        checkBox_password.setOnCheckedChangeListener(this);
        checkbox_con_password.setOnCheckedChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edt_userName:
                changeViewColor(view_userName, hasFocus);
                break;
            case R.id.edt_email:
                changeViewColor(view_email, hasFocus);
                break;
            case R.id.edt_mobile:
                changeViewColor(view_mobile, hasFocus);
                break;
            case R.id.edt_password:
                changeViewColor(view_password, hasFocus);
                break;
            case R.id.edt_con_password:
                changeViewColor(view_con_password, hasFocus);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkbox_password:
                showHidePassword(editText_password, isChecked);
                break;
            case R.id.checkbox_con_password:
                showHidePassword(editText_con_password, isChecked);
                break;
        }
    }

    private void changeViewColor(View view, boolean yes) {
        if (yes) {
            view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        } else {
            view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void showHidePassword(EditText editText, boolean isChecked) {
        if (isChecked) {
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            editText.setSelection(editText.length());
        } else {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editText.setSelection(editText.length());
        }
    }

    @OnClick({R.id.txt_signin, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_signin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.btn_register:
                checkRegisterValidation();
                break;
        }
    }

    private void checkRegisterValidation() {
        if (editText_username.getText().toString().equalsIgnoreCase("")) {
            editText_username.setError("Enter User Name");
        } else if (editText_email.getText().toString().equalsIgnoreCase("")) {
            editText_email.setError("Enter Email");
        } else if (editText_mobile.getText().toString().equalsIgnoreCase("")) {
            editText_mobile.setError("Enter Mobile Number");
        } else if (editText_password.getText().toString().equalsIgnoreCase("")) {
            editText_password.setError("Enter Password");
        } else if (editText_con_password.getText().toString().equalsIgnoreCase("")) {
            editText_con_password.setError("Re Enter Password");
        } else if (!checkbox_agree.isChecked()) {
            AlertDialogBox.showAlert(RegisterActivity.this, "Please Agree terms and conditions");
        } else {
            startActivity(new Intent(RegisterActivity.this, ProfileActivity.class));
           // finish();
        }
    }
}
