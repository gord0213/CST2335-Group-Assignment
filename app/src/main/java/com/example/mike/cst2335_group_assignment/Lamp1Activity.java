package com.example.mike.cst2335_group_assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
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
import android.widget.ToggleButton;

public class Lamp1Activity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "Lamp1Activity";
    ToggleButton toggleButton;
    Boolean ButtonState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
        toggleButton = (ToggleButton)findViewById(R.id.lightSwitch);
        toggleButton.setChecked(prefs.getBoolean("IsChecked", false));
        toggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences.Editor editor = prefs.edit();
                if (toggleButton.isChecked()){
                    Toast.makeText(Lamp1Activity.this, "Lamp is turned on", Toast.LENGTH_SHORT).show();
                    editor.putBoolean("IsChecked", true);
                    editor.commit();
                }else{
                    Toast.makeText(Lamp1Activity.this, "Lamp is turned off", Toast.LENGTH_SHORT).show();
                    editor.putBoolean("IsChecked", false);
                    editor.commit();
                }
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
                intent = new Intent(Lamp1Activity.this, HomeScreen.class);
                Log.i(ACTIVITY_NAME, "Going to Home Screen");
                startActivity(intent);
                break;
            case R.id.actionLivingRoom:
                Toast.makeText(Lamp1Activity.this, "You are already here !!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionHouseSetting:
                intent = new Intent(Lamp1Activity.this, HouseSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content),R.string.about, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.Help:
                intent = new Intent(Lamp1Activity.this, LRHelpActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}
