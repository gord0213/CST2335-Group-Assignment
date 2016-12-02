package com.example.mike.cst2335_group_assignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class HSGarageActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "HSGarageActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsgarage);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        Button btnOpenClose = (Button) findViewById(R.id.btnOpenClose);
        final Switch swtGarageLight = (Switch) findViewById(R.id.swtGarageLight);
        final TextView txtGarageStatus = (TextView) findViewById(R.id.txtGarageStatus);

        //button for opening and closing the garage
        btnOpenClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Context context = HSGarageActivity.this;
                if (txtGarageStatus.getText() == getString(R.string.gs_closed)){
                    txtGarageStatus.setText(getString(R.string.gs_open));
                   //if door is being opened, turn on the light
                    swtGarageLight.setChecked(true);
                } else {
                    txtGarageStatus.setText(getString(R.string.gs_closed));
                }
               //save current state to shared prefs.
                SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("GarageDoor", txtGarageStatus.getText().toString());
                editor.commit();
            }
        });
        //turn on or off the garage light with switch. save state in shared prefs.
        swtGarageLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                if (isChecked) {

                    Toast toast = Toast.makeText(getApplicationContext(), "Garage Light is On", Toast.LENGTH_SHORT);
                    toast.show();
                    editor.putBoolean("GarageLight", true);
                    editor.commit();

                } else {

                    Toast toast = Toast.makeText(getApplicationContext(), "Garage Light is Off", Toast.LENGTH_SHORT);
                    toast.show();
                    editor.putBoolean("GarageLight", false);
                    editor.commit();
                }
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
        Switch swtGarageLight = (Switch) findViewById(R.id.swtGarageLight);
        TextView txtGarageStatus = (TextView) findViewById(R.id.txtGarageStatus);

//        TextView txtGarageStatus = (TextView) findViewById(R.id.txtGarageStatus);
        SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
        txtGarageStatus.setText( prefs.getString("GarageDoor", getString(R.string.gs_closed)));

//        Switch swtGarageLight = (Switch) findViewById(R.id.swtGarageLight);
//        SharedPreferences prefsGarageLight = getSharedPreferences("GarageLight", Context.MODE_PRIVATE);
        swtGarageLight.setChecked( prefs.getBoolean("GarageLight", false));

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
