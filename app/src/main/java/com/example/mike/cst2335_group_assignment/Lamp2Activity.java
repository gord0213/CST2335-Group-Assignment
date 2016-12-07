package com.example.mike.cst2335_group_assignment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;

public class Lamp2Activity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "Lamp2Activity ";
    private SeekBar lightDimm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lightDimm = (SeekBar) findViewById(R.id.lightDimmer);

        final SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
        int progress = prefs.getInt("Progress%", 0);
        float backLightValue = prefs.getFloat("LightValue", 1);

        lightDimm.setProgress(progress);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.screenBrightness = backLightValue;
        getWindow().setAttributes(layoutParams);
        lightDimm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            float backLightValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ContentResolver cr = getContentResolver();
//                try{
                    Log.i(ACTIVITY_NAME, "Light is at " + progress );
//                    int brightness = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS);
//                    Settings.System.putInt(cr, Settings.System.SCREEN_BRIGHTNESS, brightness);
//                    WindowManager.LayoutParams lp = getWindow().getAttributes();
//                    lp.screenBrightness = brightness / 255.0f;
//                    getWindow().setAttributes(lp);
                    backLightValue = (float) progress/100;
                    WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                    layoutParams.screenBrightness = backLightValue;
                    getWindow().setAttributes(layoutParams);
//                }catch(Settings.SettingNotFoundException ex){
//
//                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("Progress%", seekBar.getProgress());
                editor.putFloat("LightValue",  backLightValue);
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
                intent = new Intent(Lamp2Activity.this, HomeScreen.class);
                Log.i(ACTIVITY_NAME, "Going to Home Screen");
                startActivity(intent);
                break;
            case R.id.actionLivingRoom:
                Toast.makeText(Lamp2Activity.this, "You are already here !!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionHouseSetting:
                intent = new Intent(Lamp2Activity.this, HouseSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content),R.string.about, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.Help:
                intent = new Intent(Lamp2Activity.this, LRHelpActivity.class);
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
