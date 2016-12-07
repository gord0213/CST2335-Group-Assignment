package com.example.mike.cst2335_group_assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mike.cst2335_group_assignment.Database.Helper;

import java.util.ArrayList;

public class FavoritesChannel extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "FavoritesChannel";
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
                SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("FavChannel", Integer.valueOf(channel));
                editor.commit();
                Intent intent = new Intent(FavoritesChannel.this, TelevisionActivity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton fabBack = (FloatingActionButton) findViewById(R.id.fabBack);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_house_settings, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        Intent intent;
        switch (id){
            case R.id.actionHome:
                intent = new Intent(FavoritesChannel.this, HomeScreen.class);
                Log.i(ACTIVITY_NAME, "Going to Home Screen");
                startActivity(intent);
                break;
            case R.id.actionLivingRoom:
                Toast.makeText(FavoritesChannel.this, "You are already here !!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionHouseSetting:
                intent = new Intent(FavoritesChannel.this, HouseSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content),R.string.about, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.Help:
                intent = new Intent(FavoritesChannel.this, LRHelpActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
