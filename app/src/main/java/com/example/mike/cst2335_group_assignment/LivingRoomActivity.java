package com.example.mike.cst2335_group_assignment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LivingRoomActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "Living Room Activity";
    ListView mainMenuView;
    String[] menuList;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Resources resources = getResources();
        menuList = resources.getStringArray(R.array.living_room_menu);
        mainMenuView = (ListView) findViewById(R.id.livingRoomMenuView);
        mainMenuView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuList));
        mainMenuView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(ACTIVITY_NAME, "You Clicked a menu item");
                if(menuList[position].equals("Lamp 1")){
                    intent = new Intent(LivingRoomActivity.this, Lamp1Activity.class);
                    Log.i(ACTIVITY_NAME, "Going to lamp 1 activity");
                    startActivity(intent);
                }else if(menuList[position].equals("Lamp 2")){
                    intent = new Intent(LivingRoomActivity.this, Lamp2Activity.class);
                    Log.i(ACTIVITY_NAME, "Going to lamp 2 activity");
                    startActivity(intent);
                }else if(menuList[position].equals("Lamp 3")){
                    intent = new Intent(LivingRoomActivity.this, Lamp3Activity.class);
                    Log.i(ACTIVITY_NAME, "Going to lamp 3 activity");
                    startActivity(intent);
                }else if(menuList[position].equals("Television")){
                    intent = new Intent(LivingRoomActivity.this, TelevisionActivity.class);
                    Log.i(ACTIVITY_NAME, "Going to lamp 1 activity");
                    startActivity(intent);
                }else if(menuList[position].equals("Blinds")){
                    intent = new Intent(LivingRoomActivity.this, BlindsActivity.class);
                    Log.i(ACTIVITY_NAME, "Going to lamp 1 activity");
                    startActivity(intent);
                }
            }

        });
    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_transition, m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        Intent intent;
        switch (id){
            case R.id.actionHome:
                intent = new Intent(LivingRoomActivity.this, HomeScreen.class);
                Log.i(ACTIVITY_NAME, "Going to Home Screen");
                startActivity(intent);
                break;
            case R.id.actionLivingRoom:
                Toast.makeText(LivingRoomActivity.this, "You are already here !!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionHouseSetting:
                intent = new Intent(LivingRoomActivity.this, HouseSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content), R.string.about, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
        }
        return true;
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");

    }

}
