package com.example.mike.cst2335_group_assignment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;

public class Lamp2Activity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "Lamp 2 activity ";
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