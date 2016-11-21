package com.example.mike.cst2335_group_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "Home Screen Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transition, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        Intent intent;
        switch (id){
            case R.id.actionHome:
                Toast.makeText(HomeScreen.this, "You are already here !!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionLivingRoom:
                intent = new Intent(HomeScreen.this, LivingRoomActivity.class);
                Log.i(ACTIVITY_NAME, "Going to Home Screen");
                startActivity(intent);
                break;
            case R.id.actionHouseSetting:
                intent = new Intent(HomeScreen.this, HouseSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content), R.string.about, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
        }
        return true;
    }
}
