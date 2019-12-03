package com.awt.jobstreamers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.transition.TransitionManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.awt.jobstreamers.R;
import com.awt.jobstreamers.models.LoginResponse;
import com.awt.jobstreamers.rest.ApiClient;
import com.awt.jobstreamers.rest.ApiInterface;
import com.awt.jobstreamers.utils.ShowDialog;
import com.awt.jobstreamers.utils.ShowSnackBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.transitionseverywhere.extra.Scale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginOtpActivity extends AppCompatActivity {

    @BindView(R.id.edt_mobile)
    EditText editText_mobile;
    @BindView(R.id.img_logo)
    ImageView image_logo;
    @BindView(R.id.btn_login)
    Button button_login;
    @BindView(R.id.linear_container)
    LinearLayout linear_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_otp);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_login)
    public void onClick(View view) {
        if (editText_mobile.getText().toString().equalsIgnoreCase("")) {
            ShowSnackBar.show(LoginOtpActivity.this,"Please enter Email ID / Mobile",view);
        } else {
            callLogin(editText_mobile.getText().toString());
        }

    }

    private void callLogin(String user_id) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> loginResponseCall = apiInterface.login(user_id);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e("onResponse", new Gson().toJson(response));
                dialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        if (response.body().getStatusLogin().equalsIgnoreCase("creating_new")) {
                            Intent intent = new Intent(LoginOtpActivity.this, EnterUserDetailsActivity.class);
                            intent.putExtra("mobile_email", editText_mobile.getText().toString());
                            Pair<View, String> p1 = Pair.create((View) image_logo, "logo");
                            ActivityOptionsCompat options = ActivityOptionsCompat.
                                    makeSceneTransitionAnimation(LoginOtpActivity.this, p1);
                            startActivity(intent,options.toBundle());
                        }else {
                            Intent intent = new Intent(LoginOtpActivity.this, VerifyOtpActivity.class);
                            intent.putExtra("mobile_email", editText_mobile.getText().toString());
                            Pair<View, String> p1 = Pair.create((View) image_logo, "logo");
                            ActivityOptionsCompat options = ActivityOptionsCompat.
                                    makeSceneTransitionAnimation(LoginOtpActivity.this,p1);
                            startActivity(intent, options.toBundle());
                        }

                    } else {
                        ShowSnackBar.show(LoginOtpActivity.this,"Please enter Email ID / Mobile",linear_container);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("onFailure", "" + t);
                dialog.dismiss();
            }
        });
    }
}
