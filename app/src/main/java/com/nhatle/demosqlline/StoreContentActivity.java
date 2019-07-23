package com.nhatle.demosqlline;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StoreContentActivity extends AppCompatActivity {
    private ManagerSQL managerSQL;
    private TextView nameStore;
    private TextView contentStore;
    private Store store;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_content_store);
        nameStore = findViewById(R.id.name);
        contentStore = findViewById(R.id.content_store);
        managerSQL = new ManagerSQL(this);

        Intent intent = getIntent();
        int id = intent.getIntExtra("ID",0);
        store = managerSQL.getData(id);
        String name = intent.getStringExtra("Name");
        String content = intent.getStringExtra("Content");

        init(name,content);
    }

    private void init(String name, String content) {
        nameStore.setText(name);
        contentStore.setText(android.text.Html.fromHtml(content));
//        contentStore.setText(content);
    }

}
