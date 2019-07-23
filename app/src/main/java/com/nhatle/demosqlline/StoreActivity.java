package com.nhatle.demosqlline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class StoreActivity extends AppCompatActivity implements IStore {
    private ManagerSQL managerSQL;
    private RecyclerView rc_store;
    private  List<Store>stores;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        managerSQL = new ManagerSQL(this);
        int id = getIntent().getIntExtra("ID",0);
        stores = managerSQL.getStore(id);
        rc_store = findViewById(R.id.rc_store);
        rc_store.setLayoutManager(new LinearLayoutManager(this));
        rc_store.setAdapter(new StoreAdapter(this));
    }

    @Override
    public Topic getTopic(int position) {
        return null;
    }

    @Override
    public Store getStore(int position) {
        return stores.get(position);
    }

    @Override
    public int getCount() {
        return stores.size();
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent();
        intent.setClass(this,StoreContentActivity.class);
        intent.putExtra("ID",stores.get(position).getId());
        intent.putExtra("Name",stores.get(position).getName());
        intent.putExtra("Content",stores.get(position).getContent());
        startActivity(intent);
    }
}
