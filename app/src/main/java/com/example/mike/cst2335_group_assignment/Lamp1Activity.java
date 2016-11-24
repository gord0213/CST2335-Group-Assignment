package com.example.mike.cst2335_group_assignment;

import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Lamp1Activity extends AppCompatActivity {
    ToggleButton toggleButton;
    Boolean ButtonState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggleButton = (ToggleButton)findViewById(R.id.lightSwitch);
        toggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (toggleButton.isChecked()){
                    Toast.makeText(Lamp1Activity.this, "Lamp is turned on", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Lamp1Activity.this, "Lamp is turned off", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
