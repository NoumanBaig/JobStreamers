package com.awt.jobstreamers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.awt.jobstreamers.R;
import com.awt.jobstreamers.adapters.RecentSearchesAdapter;
import com.awt.jobstreamers.adapters.SearchAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.recycler_recent_search)
    RecyclerView recycler_recent_search;
    @BindView(R.id.recycler_search)
    RecyclerView recycler_search;
    @BindView(R.id.layout_recent_search)
    LinearLayout layout_recent_search;
    @BindView(R.id.layout_search)
    LinearLayout layout_search;
    @BindView(R.id.edt_search)
    EditText editText_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search); ButterKnife.bind(this);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.search));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recycler_recent_search.setLayoutManager(new LinearLayoutManager(this));
        recycler_search.setLayoutManager(new LinearLayoutManager(this));
        recycler_recent_search.setAdapter(new RecentSearchesAdapter(SearchActivity.this));
        recycler_search.setAdapter(new SearchAdapter(SearchActivity.this));

        editText_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >=1 ){
                    layout_recent_search.setVisibility(View.GONE);
                    layout_search.setVisibility(View.VISIBLE);
                }else {
                    layout_recent_search.setVisibility(View.VISIBLE);
                    layout_search.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
