package com.awt.jobstreamers.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.awt.jobstreamers.R;
import org.json.JSONObject;
import java.util.Arrays;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    @BindView(R.id.edt_userName)
    EditText editText_userName;
    @BindView(R.id.edt_password)
    EditText editText_password;
    @BindView(R.id.view_userName)
    View view_userName;
    @BindView(R.id.view_password)
    View view_password;
    @BindView(R.id.checkbox_password)
    CheckBox checkBox_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        editText_userName.setOnFocusChangeListener(this);
        editText_password.setOnFocusChangeListener(this);
        checkBox_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showHidePassword(isChecked);
            }
        });

    }

    private void showHidePassword(boolean isChecked) {
        if (isChecked) {
            editText_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            editText_password.setSelection(editText_password.length());
        } else {
            editText_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editText_password.setSelection(editText_password.length());
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edt_userName:
                changeViewColor(view_userName, hasFocus);
                break;
            case R.id.edt_password:
                changeViewColor(view_password, hasFocus);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    private void changeViewColor(View view, boolean yes) {
        if (yes) {
            view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        } else {
            view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    @OnClick({R.id.txt_register, R.id.btn_signin, R.id.txt_forgotPassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.txt_forgotPassword:
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                break;
            case R.id.btn_signin:
                checkSignInValidation();
                break;
            case R.id.txt_signin_fb:
                //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
                break;


        }
    }

    private void checkSignInValidation() {
        if (editText_userName.getText().toString().equalsIgnoreCase("")) {
            editText_userName.setError("Enter User Name");
        } else if (editText_password.getText().toString().equalsIgnoreCase("")) {
            editText_password.setError("Enter Password");
        } else {
            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            finish();
        }
    }

}
