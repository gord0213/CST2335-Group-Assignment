package com.example.mike.cst2335_group_assignment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class Lamp3Activity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "Lamp 3 activity ";
    private SeekBar colorBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        colorBar = (SeekBar) findViewById(R.id.colorSlider);

        colorBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float[] hsvColor = {0, 1, 1};
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
