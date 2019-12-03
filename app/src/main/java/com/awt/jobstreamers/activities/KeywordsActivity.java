package com.awt.jobstreamers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.awt.jobstreamers.R;
import com.awt.jobstreamers.adapters.KeywordsAdapter;
import com.awt.jobstreamers.utils.ItemClickSupport;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KeywordsActivity extends AppCompatActivity {

    @BindView(R.id.recycler_keywords)
    RecyclerView recycler_keywords;
    @BindView(R.id.edt_search)
    EditText editText_search;
    ArrayList<String> arrayList;
    KeywordsAdapter keywordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keywords);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.keywords));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        arrayList = new ArrayList<>();
//        recycler_keywords.setLayoutManager(new GridLayoutManager(this,GridLayoutManager.DEFAULT_SPAN_COUNT));
        recycler_keywords.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick({R.id.txt_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_add:
                if (arrayList.size()<5){
                    arrayList.add(editText_search.getText().toString());
                    editText_search.setText("");
                    keywordsAdapter = new KeywordsAdapter(KeywordsActivity.this,arrayList);
                    recycler_keywords.setAdapter(keywordsAdapter);
                    keywordsAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(this, "Keywords limit exceeds", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}