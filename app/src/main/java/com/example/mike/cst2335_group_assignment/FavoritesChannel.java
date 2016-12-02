package com.example.mike.cst2335_group_assignment;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mike.cst2335_group_assignment.Database.Helper;

import java.util.ArrayList;

public class FavoritesChannel extends AppCompatActivity {
    private ListView listView;
    private Helper helper;
   //String[] channelList;
    private ArrayList<String> channelList = new ArrayList<>();
    TelevisionActivity televisionActivity = new TelevisionActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_channel);

        listView = (ListView) findViewById(R.id.favChannel);
        helper = new Helper(this);
        Cursor cursor = helper.getData();
        while(cursor.moveToNext()){
            channelList.add((cursor.getString( cursor.getColumnIndex(helper.KEY_CHANNEL))));
        }

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, channelList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String channel = channelList.get(position);
                televisionActivity.changeChannel(Integer.valueOf(channel));
                finish();
            }
        });
    }
}
