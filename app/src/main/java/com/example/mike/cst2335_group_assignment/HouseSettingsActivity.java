package com.example.mike.cst2335_group_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HouseSettingsActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "House Settings Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        }
        return true;
    }
}
