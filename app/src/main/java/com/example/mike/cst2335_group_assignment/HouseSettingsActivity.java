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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
//Main screen for Home Settings. Allows access to Garage, Home Temp, and Outside Weather

public class HouseSettingsActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "HouseSettingsActivity";
    ListView mainMenuView;
    String[] menuList;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        Resources resources = getResources();
        FloatingActionButton fabBack = (FloatingActionButton) findViewById(R.id.fabBack);
        imageView = (ImageView) findViewById(R.id.HouseImage);
        imageView.setImageResource(R.mipmap.ic_house2);
        imageView.getLayoutParams().height = 800;
        imageView.getLayoutParams().width = 800;
        menuList = resources.getStringArray(R.array.home_setting_menu_array);
        mainMenuView = (ListView) findViewById(R.id.mainMenuView);
        mainMenuView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuList));
        mainMenuView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(ACTIVITY_NAME, "User clicked a menu item");
                Toast.makeText(getApplicationContext(), menuList[position], Toast.LENGTH_SHORT).show();
                if (menuList[position].equals("Garage")){
                    Intent intent = new Intent(HouseSettingsActivity.this, HSGarageActivity.class);
                    Log.i(ACTIVITY_NAME, "Going to Garage Settings");
                    startActivityForResult(intent, 5);
                } else if (menuList[position].equals("House Temperature")){
                    Intent intent = new Intent(HouseSettingsActivity.this, HSHouseTempActivity.class);
                    Log.i(ACTIVITY_NAME, "Going to Home Temperature Settings");
                    startActivityForResult(intent, 5);
                } else if (menuList[position].equals("Outside Weather")){
                    Intent intent = new Intent(HouseSettingsActivity.this, HSOutsideTempActivity.class);
                    Log.i(ACTIVITY_NAME, "Going to Outside Weather Info");
                    startActivityForResult(intent, 5);
                }else if(menuList[position].equals("Help")) {
                    Intent intent = new Intent(HouseSettingsActivity.this, HSHelpActivity.class);
                    Log.i(ACTIVITY_NAME, "Going to Help Activity");
                    startActivity(intent);
                }

            }
        });

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
                intent = new Intent(HouseSettingsActivity.this, HomeScreen.class);
                Log.i(ACTIVITY_NAME, "Going to Home Screen");
                startActivity(intent);
                break;
            case R.id.actionLivingRoom:
                intent = new Intent(HouseSettingsActivity.this, LivingRoomActivity.class);
                startActivity(intent);
                break;
            case R.id.actionHouseSetting:
                Toast.makeText(HouseSettingsActivity.this, "You are already here !!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content),R.string.about, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.Help:
                intent = new Intent(HouseSettingsActivity.this, HSHelpActivity.class);
                startActivity(intent);
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
