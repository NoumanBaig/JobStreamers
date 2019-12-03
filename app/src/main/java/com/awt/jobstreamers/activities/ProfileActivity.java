package com.awt.jobstreamers.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.awt.jobstreamers.models.Logout;
import com.awt.jobstreamers.rest.ApiClient;
import com.awt.jobstreamers.rest.ApiInterface;
import com.awt.jobstreamers.utils.SharedPrefsUtils;
import com.awt.jobstreamers.utils.ShowSnackBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.awt.jobstreamers.R;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @BindView(R.id.layout_normal)
    LinearLayout layout_normal;
    @BindView(R.id.layout_edit)
    LinearLayout layout_edit;
    @BindView(R.id.layout_normal_img)
    LinearLayout layout_normal_img;
    @BindView(R.id.layout_edit_img)
    LinearLayout layout_edit_img;
    @BindView(R.id.fab_edit)
    FloatingActionButton fab_edit;
    @BindView(R.id.fab_save)
    FloatingActionButton fab_save;
    @BindView(R.id.spinner_exp)
    Spinner spinner_exp;
    @BindView(R.id.spinner_gender)
    Spinner spinner_gender;
    @BindView(R.id.spinner_status)
    Spinner spinner_status;
    String[] experience_arr = {"Work Experience (Fresher / Experienced)", "Fresher", "Experienced"};
    String[] gender_arr = {"Select Gender (Male / Female / Other)", "Male", "Female", "Other"};
    String[] marital_arr = {"Marital Status (Single / Married)", "Single", "Married"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setTitle("Profile");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Make toolbar show navigation button (i.e back button with arrow icon)
//        toolbar.setNavigationIcon(R.mipmap.icon_app);

        fab_edit.setRippleColor(getResources().getColor(R.color.dark_green));
        fab_save.setRippleColor(getResources().getColor(R.color.colorAccent));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, experience_arr);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, gender_arr);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, marital_arr);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_exp.setAdapter(arrayAdapter);
        spinner_gender.setAdapter(arrayAdapter2);
        spinner_status.setAdapter(arrayAdapter3);

    }

    @SuppressLint("RestrictedApi")
    @OnClick({R.id.fab_save, R.id.fab_edit})
    public void onFabClick(View view) {
        switch (view.getId()) {
            case R.id.fab_edit:
                fab_edit.setVisibility(View.GONE);
                fab_save.setVisibility(View.VISIBLE);
                layout_normal.setVisibility(View.GONE);
                layout_normal_img.setVisibility(View.GONE);
                layout_edit.setVisibility(View.VISIBLE);
                layout_edit_img.setVisibility(View.VISIBLE);
                break;
            case R.id.fab_save:
//                fab_edit.setVisibility(View.VISIBLE);
//                fab_save.setVisibility(View.GONE);
//                layout_normal.setVisibility(View.VISIBLE);
//                layout_normal_img.setVisibility(View.VISIBLE);
//                layout_edit.setVisibility(View.GONE);
//                layout_edit_img.setVisibility(View.GONE);
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
//        MenuItem item = (MenuItem) menu.findItem(R.id.toggle);
//        item.setActionView(R.layout.toggle_layout);
//        Switch switchAB = item
//                .getActionView().findViewById(R.id.svitch);
//        switchAB.setChecked(false);
//        switchAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,
//                                         boolean isChecked) {
//                if (isChecked) {
//                    startActivity(new Intent(ProfileActivity.this,HomeActivity.class));
//                    finish();
//                }
//            }
//        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            View vItem = findViewById(R.id.action_settings);
            PopupMenu popup = new PopupMenu(ProfileActivity.this, vItem);
            popup.setOnMenuItemClickListener(ProfileActivity.this);
            popup.inflate(R.menu.menu_popup);
            popup.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
//                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                return true;
            case R.id.search:
                startActivity(new Intent(ProfileActivity.this, SearchActivity.class));
                return true;
            case R.id.keywords:
                startActivity(new Intent(ProfileActivity.this, KeywordsActivity.class));
                return true;
            case R.id.resume:
                startActivity(new Intent(ProfileActivity.this, ResumeUploadActivity.class));
                return true;
            case R.id.shortlisted:
                startActivity(new Intent(ProfileActivity.this, ShortlistedActivity.class));
                return true;
            case R.id.recents:
                startActivity(new Intent(ProfileActivity.this, RecentsActivity.class));
                return true;
            case R.id.tests:
                startActivity(new Intent(ProfileActivity.this, TestsActivity.class));
                return true;
            case R.id.block:
                startActivity(new Intent(ProfileActivity.this, BlockActivity.class));
                return true;
            case R.id.notifications:
                startActivity(new Intent(ProfileActivity.this, NotificationsActivity.class));
                return true;
            case R.id.logout:
                logout(SharedPrefsUtils.getString(ProfileActivity.this, "token"));
                return true;
            default:
                return false;
        }
    }

    private void logout(String token) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Logout> logoutCall = apiInterface.logout(token);
        logoutCall.enqueue(new Callback<Logout>() {
            @Override
            public void onResponse(Call<Logout> call, Response<Logout> response) {
                Log.e("onResponse", new Gson().toJson(response));
                dialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        SharedPrefsUtils.clear(ProfileActivity.this);
                        ShowSnackBar.show(ProfileActivity.this, response.body().getMessage(), layout_normal);
                        Intent intent = new Intent(ProfileActivity.this, LoginOtpActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        ShowSnackBar.show(ProfileActivity.this, response.body().getMessage(), layout_normal);
                    }
                }
            }

            @Override
            public void onFailure(Call<Logout> call, Throwable t) {
                Log.e("onFailure", "" + t);
                dialog.dismiss();
            }
        });
    }

}
