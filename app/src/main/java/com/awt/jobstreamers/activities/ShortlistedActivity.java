package com.awt.jobstreamers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.awt.jobstreamers.R;
import com.awt.jobstreamers.adapters.ShortlistedAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShortlistedActivity extends AppCompatActivity {

    @BindView(R.id.recycler_shortlisted)
    RecyclerView recycler_shortlisted;
    ShortlistedAdapter shortlistedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortlisted); ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.shortlisted));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recycler_shortlisted.setLayoutManager(new LinearLayoutManager(this));
        shortlistedAdapter=new ShortlistedAdapter(ShortlistedActivity.this);
        recycler_shortlisted.setAdapter(shortlistedAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
