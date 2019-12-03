package com.awt.jobstreamers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.awt.jobstreamers.R;
import com.awt.jobstreamers.models.Logout;
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

public class HomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private Animation fabOpenAnimation;
    private Animation fabCloseAnimation;
    private boolean isFabMenuOpen = false;
//    @BindView(R.id.base_fab)
//    FloatingActionButton base_fab;
    @BindView(R.id.flip_fab)
    FloatingActionButton flip_fab;
    @BindView(R.id.mic_fab)
    FloatingActionButton mic_fab;
    @BindView(R.id.activity_main)
    ConstraintLayout layout_normal;
//    @BindView(R.id.end_fab)
//    FloatingActionButton end_fab;
//    @BindView(R.id.createLayout)
//    LinearLayout createLayout;
//    @BindView(R.id.shareLayout)
//    LinearLayout shareLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getAnimations();

    }

    private void getAnimations() {
        fabOpenAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabCloseAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_close);
    }

    @OnClick({R.id.flip_fab, R.id.mic_fab})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.base_fab:
//                if (isFabMenuOpen)
//                    collapseFabMenu();
//                else
//                    expandFabMenu();
//                break;
            case R.id.flip_fab:
                Toast.makeText(this, "fab clicked", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.end_fab:
//                Toast.makeText(this, "fab clicked", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.mic_fab:
                Toast.makeText(this, "fab clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        if (isFabMenuOpen)
//            collapseFabMenu();
//        else
            super.onBackPressed();
    }

//    private void expandFabMenu() {
//        ViewCompat.animate(base_fab).rotation(45.0F).withLayer().setDuration(300).setInterpolator(new OvershootInterpolator(10.0F)).start();
//        flip_fab.startAnimation(fabOpenAnimation);
//        end_fab.startAnimation(fabOpenAnimation);
//        mic_fab.startAnimation(fabOpenAnimation);
//        flip_fab.setClickable(true);
//        end_fab.setClickable(true);
//        mic_fab.setClickable(true);
//        isFabMenuOpen = true;
//    }

//    private void collapseFabMenu() {
//        ViewCompat.animate(base_fab).rotation(0.0F).withLayer().setDuration(300).setInterpolator(new OvershootInterpolator(10.0F)).start();
//        flip_fab.startAnimation(fabCloseAnimation);
//        end_fab.startAnimation(fabCloseAnimation);
//        mic_fab.startAnimation(fabCloseAnimation);
//        flip_fab.setClickable(false);
//        end_fab.setClickable(false);
//        mic_fab.setClickable(false);
//        isFabMenuOpen = false;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
//        MenuItem item = (MenuItem) menu.findItem(R.id.toggle);
//        item.setActionView(R.layout.toggle_layout);
//        Switch switchAB = item
//                .getActionView().findViewById(R.id.svitch);
//        switchAB.setChecked(true);
//        switchAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,
//                                         boolean isChecked) {
//                if (!isChecked) {
//                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
//                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
            PopupMenu popup = new PopupMenu(HomeActivity.this, vItem);
            popup.setOnMenuItemClickListener(HomeActivity.this);
            popup.inflate(R.menu.menu_popup);
            popup.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                return true;
            case R.id.search:
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                return true;
            case R.id.keywords:
                startActivity(new Intent(HomeActivity.this, KeywordsActivity.class));
                return true;
            case R.id.resume:
                startActivity(new Intent(HomeActivity.this, ResumeUploadActivity.class));
                return true;
            case R.id.shortlisted:
                startActivity(new Intent(HomeActivity.this, ShortlistedActivity.class));
                return true;
            case R.id.recents:
                startActivity(new Intent(HomeActivity.this, RecentsActivity.class));
                return true;
            case R.id.tests:
                startActivity(new Intent(HomeActivity.this, TestsActivity.class));
                return true;
            case R.id.block:
                startActivity(new Intent(HomeActivity.this, BlockActivity.class));
                return true;
            case R.id.notifications:
                startActivity(new Intent(HomeActivity.this, NotificationsActivity.class));
                return true;
            case R.id.logout:
                logout(SharedPrefsUtils.getString(HomeActivity.this, "token"));
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
                        SharedPrefsUtils.clear(HomeActivity.this);
                        ShowSnackBar.show(HomeActivity.this, response.body().getMessage(), layout_normal);
                        Intent intent = new Intent(HomeActivity.this, LoginOtpActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        ShowSnackBar.show(HomeActivity.this, response.body().getMessage(), layout_normal);
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
