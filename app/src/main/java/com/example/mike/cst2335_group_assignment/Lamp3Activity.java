package com.example.mike.cst2335_group_assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class Lamp3Activity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "Lamp3Activity ";
    private SeekBar colorBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        colorBar = (SeekBar) findViewById(R.id.colorSlider);

        final SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
        float[] hsvColor = {prefs.getFloat("LightColor0", 0), 1, 1};
        if (hsvColor[0]!= 0) {
            getWindow().getDecorView().setBackgroundColor(Color.HSVToColor(hsvColor));
        }else{
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }
         colorBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public float[] hsvColor = {0, 1, 1};
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }else{
                    hsvColor[0] = 360f * progress / seekBar.getMax();
                    //     view.setBackgroundColor(Color.HSVToColor(hsvColor));
                    getWindow().getDecorView().setBackgroundColor(Color.HSVToColor(hsvColor));

                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat("LightColor0", hsvColor[0]);
                editor.putFloat("LightColor1", hsvColor[1]);
                editor.putFloat("LightColor2", hsvColor[2]);
                editor.commit();
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
                intent = new Intent(Lamp3Activity.this, HomeScreen.class);
                Log.i(ACTIVITY_NAME, "Going to Home Screen");
                startActivity(intent);
                break;
            case R.id.actionLivingRoom:
                Toast.makeText(Lamp3Activity.this, "You are already here !!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionHouseSetting:
                intent = new Intent(Lamp3Activity.this, HouseSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content),R.string.about, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.Help:
                intent = new Intent(Lamp3Activity.this, LRHelpActivity.class);
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
