package com.example.mike.cst2335_group_assignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class BlindsActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "Blind Activity";
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blinds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        seekBar = (SeekBar) findViewById(R.id.blindSlider);
        final SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);


        seekBar.setProgress(prefs.getInt("BlindProgress", 0));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {         }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (progress == 0) {
                    Toast.makeText(BlindsActivity.this, "Blind is fully down", Toast.LENGTH_SHORT).show();
                }else if (progress < 40 && progress > 0){
                    Toast.makeText(BlindsActivity.this, "Blind is almost down", Toast.LENGTH_SHORT).show();
                }else if (progress > 40 && progress < 60) {
                    Toast.makeText(BlindsActivity.this, "Blind is about halfway", Toast.LENGTH_SHORT).show();
                }else if (progress > 60 && progress < 100){
                    Toast.makeText(BlindsActivity.this, "Blind is almost up", Toast.LENGTH_SHORT).show();
                }else if(progress == 100){
                    Toast.makeText(BlindsActivity.this, "Blind is fully up", Toast.LENGTH_SHORT).show();
                }
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("BlindProgress", progress);
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
