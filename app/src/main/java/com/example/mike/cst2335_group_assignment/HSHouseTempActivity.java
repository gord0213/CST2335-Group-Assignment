package com.example.mike.cst2335_group_assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Integer.valueOf;

//Activity for controlling and scheduling the temperature in the house.
public class HSHouseTempActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "HSHouseTempActivity";
    public int houseTemp;
    ArrayList<String> tempAdapterList;
    TempAdapter tempAdapter;
    HomeTempDatabaseHelper tempHelper;
    SQLiteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        setContentView(R.layout.activity_hshouse_temp);
        SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
        FloatingActionButton fabBack = (FloatingActionButton) findViewById(R.id.fabBack);
        ListView schedTempView = (ListView) findViewById(R.id.schedTempView);
        houseTemp = prefs.getInt("HouseTemp", 22);
        TextView txtHouseTemp = (TextView) findViewById(R.id.txtHouseTemp);
        SeekBar sbSetTemp = (SeekBar) findViewById(R.id.sbSetTemp);
        final EditText setTemp = (EditText) findViewById(R.id.setTemp);
        final EditText setTime = (EditText) findViewById(R.id.setTime);
        Button btnSetTemp = (Button) findViewById(R.id.btnSetTemp);
        tempHelper = new HomeTempDatabaseHelper(this);
        Cursor cursor = tempHelper.getData();
        tempAdapterList = new ArrayList<String>();

        while (cursor.moveToNext()){
            tempAdapterList.add(cursor.getString(cursor.getColumnIndex(tempHelper.KEY_DATA)));
        }

        tempAdapter = new TempAdapter(this);
        schedTempView.setAdapter(tempAdapter);

        txtHouseTemp.setText(String.valueOf(houseTemp) + "ºC");
        sbSetTemp.setProgress(valueOf(houseTemp));



        //seek bar to adjust the house temperature
        sbSetTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView txtHouseTemp = (TextView) findViewById(R.id.txtHouseTemp);
                houseTemp = progress;
                txtHouseTemp.setText(String.valueOf(houseTemp) + "ºC");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Once touch has been released, save temp into shared prefs.
                SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("HouseTemp", valueOf(houseTemp));
                editor.commit();
            }
        });

        btnSetTemp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                tempAdapterList.add("Time: " + setTime.getText().toString() + ", Temp: " + setTemp.getText().toString());
                tempHelper.insertData("Time: " + setTime.getText().toString() + ", Temp: " + setTemp.getText().toString());
                tempAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
            }
        });

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class TempAdapter extends ArrayAdapter<String> {
        public TempAdapter(Context ctx){
            super(ctx, 0);
        }

        public int getCount(){
            return tempAdapterList.size();
        }

        public String getItem(int position){
            return tempAdapterList.get(position);
        }

       public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = HSHouseTempActivity.this.getLayoutInflater();

            View result = null ;
                result = inflater.inflate(R.layout.scheduled_temp_list, null);
            TextView temp = (TextView)result.findViewById(R.id.temp_text);
            temp.setText(   getItem(position)  ); // get the string at position
            return result;

        }

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
                intent = new Intent(HSHouseTempActivity.this, HomeScreen.class);
                Log.i(ACTIVITY_NAME, "Going to Home Screen");
                startActivity(intent);
                break;
            case R.id.actionLivingRoom:
                intent = new Intent(HSHouseTempActivity.this, LivingRoomActivity.class);
                startActivity(intent);
                break;
            case R.id.actionHouseSetting:
                Toast.makeText(HSHouseTempActivity.this, "You are already here !!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content),R.string.about, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.Help:
                intent = new Intent(HSHouseTempActivity.this, HSHelpActivity.class);
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
        tempHelper.close();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        tempHelper.close();
        Log.i(ACTIVITY_NAME, "In onStop()");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        tempHelper.close();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

}
