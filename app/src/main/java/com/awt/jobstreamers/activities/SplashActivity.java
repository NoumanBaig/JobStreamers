package com.awt.jobstreamers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.awt.jobstreamers.R;
import com.awt.jobstreamers.utils.AlertDialogBox;
import com.awt.jobstreamers.utils.NetworkInformation;
import com.awt.jobstreamers.utils.SharedPrefsUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        boolean loginStatus = SharedPrefsUtils.getBoolean(SplashActivity.this, "login_status",false);

        if (NetworkInformation.isConnected(SplashActivity.this)) {
            Thread timer = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (loginStatus) {
                            startActivity(new Intent(SplashActivity.this, ProfileActivity.class));
                            finish();
                        } else {
                            startActivity(new Intent(SplashActivity.this, LoginOtpActivity.class));
                            finish();
                        }
                    }
                }
            };
            timer.start();
        } else {
            AlertDialogBox.showAlert(SplashActivity.this, "No Internet Connection!");
        }

    }
}
