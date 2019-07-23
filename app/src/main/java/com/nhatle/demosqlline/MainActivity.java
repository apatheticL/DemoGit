package com.nhatle.demosqlline;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements IStore{
    private ManagerSQL managerSQL;
    private RecyclerView rc;
    private List<Topic>topics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        managerSQL = new ManagerSQL(this);
        topics = managerSQL.getAllTopic();
        rc = findViewById(R.id.rc);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(new TopicAdapter(this));
    }

    @Override
    public Topic getTopic(int position) {
        return topics.get(position);
    }

    @Override
    public Store getStore(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return topics.size();
    }

    @Override
    public void onClick(int position) {

        Intent intent = new Intent();
        intent.setClass(this,StoreActivity.class);
        intent.putExtra("ID",topics.get(position).getId());
        startActivity(intent);
    }


}
