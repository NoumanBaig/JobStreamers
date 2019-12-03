package com.awt.jobstreamers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awt.jobstreamers.R;
import com.awt.jobstreamers.models.OtpResponse;
import com.awt.jobstreamers.models.RegisterResponse;
import com.awt.jobstreamers.rest.ApiClient;
import com.awt.jobstreamers.rest.ApiInterface;
import com.awt.jobstreamers.utils.SharedPrefsUtils;
import com.awt.jobstreamers.utils.ShowSnackBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpActivity extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.txt_code)
    TextView txt_code;
    @BindView(R.id.edt_otp1)
    EditText edt_otp1;
    @BindView(R.id.edt_otp2)
    EditText edt_otp2;
    @BindView(R.id.edt_otp3)
    EditText edt_otp3;
    @BindView(R.id.edt_otp4)
    EditText edt_otp4;
    @BindView(R.id.edt_otp5)
    EditText edt_otp5;
    @BindView(R.id.edt_otp6)
    EditText edt_otp6;
    String str_mobile;
    @BindView(R.id.layout_otp)
    LinearLayout layout_otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verify_otp);
        ButterKnife.bind(this);

        str_mobile = getIntent().getStringExtra("mobile_email");
        txt_code.setText(getResources().getString(R.string.sent_code) + " " + str_mobile + " " + getResources().getString(R.string.for_veri));

        edt_otp1.setEnabled(true);
        edt_otp1.requestFocus();

        edt_otp1.addTextChangedListener(this);
        edt_otp2.addTextChangedListener(this);
        edt_otp3.addTextChangedListener(this);
        edt_otp4.addTextChangedListener(this);
        edt_otp5.addTextChangedListener(this);
        edt_otp6.addTextChangedListener(this);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    @OnClick(R.id.img_back)
    public void back(View view) {
        supportFinishAfterTransition();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.e("hascode", "" + s.hashCode());
        Log.e("hascode2", "" + edt_otp1.getText().hashCode());
        if (s.hashCode() == edt_otp1.getText().hashCode()) {
            setEditTextFocus(edt_otp2);
        } else if (s.hashCode() == edt_otp2.getText().hashCode()) {
            setEditTextFocus(edt_otp3);
        } else if (s.hashCode() == edt_otp3.getText().hashCode()) {
            setEditTextFocus(edt_otp4);
        } else if (s.hashCode() == edt_otp4.getText().hashCode()) {
            setEditTextFocus(edt_otp5);
        } else {
            setEditTextFocus(edt_otp6);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    private void setEditTextFocus(EditText editText) {
        editText.setEnabled(true);
        editText.requestFocus();
    }

    @OnClick(R.id.fab_verify)
    public void onFabClick(View view) {
        validateEditFields();
    }

    private void validateEditFields() {
        String str_otp1 = edt_otp1.getText().toString();
        String str_otp2 = edt_otp2.getText().toString();
        String str_otp3 = edt_otp3.getText().toString();
        String str_otp4 = edt_otp4.getText().toString();
        String str_otp5 = edt_otp5.getText().toString();
        String str_otp6 = edt_otp6.getText().toString();
        if (str_otp1.equalsIgnoreCase("") || str_otp2.equalsIgnoreCase("")
                || str_otp3.equalsIgnoreCase("") || str_otp4.equalsIgnoreCase("")
                || str_otp5.equalsIgnoreCase("") || str_otp6.equalsIgnoreCase("")) {
            ShowSnackBar.show(VerifyOtpActivity.this, "Invalid OTP", layout_otp);
        }
        else {
            callRegister(str_mobile,str_otp1+str_otp2+str_otp3+str_otp4+str_otp5+str_otp6);
        }
    }

    private void callRegister(String userName, String otp) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<OtpResponse> otpResponseCall = apiInterface.otp(userName,otp);
        otpResponseCall.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                Log.e("onResponse", new Gson().toJson(response));
                dialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        String token = response.body().getToken();
                        SharedPrefsUtils.saveString(VerifyOtpActivity.this,"token",token);
                        SharedPrefsUtils.saveBoolean(VerifyOtpActivity.this,"login_status",true);
                        Intent intent = new Intent(VerifyOtpActivity.this, ProfileActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        ShowSnackBar.show(VerifyOtpActivity.this, response.body().getMessage(), layout_otp);
                    }
                }
            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                Log.e("onFailure", "" + t);
                dialog.dismiss();
            }
        });


    }

}
