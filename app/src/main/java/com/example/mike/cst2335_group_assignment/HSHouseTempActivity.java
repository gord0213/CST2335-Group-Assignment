package com.example.mike.cst2335_group_assignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.valueOf;

public class HSHouseTempActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "HSHouseTempActivity";
    public int houseTemp;
    ArrayList<String> tempAdapterList;
    TempAdapter tempAdapter;
//    TempDatabaseHelper tempHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hshouse_temp);
        SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
        ListView schedTempView = (ListView) findViewById(R.id.schedTempView);
        houseTemp = prefs.getInt("HouseTemp", 22);
        TextView txtHouseTemp = (TextView) findViewById(R.id.txtHouseTemp);
        SeekBar sbSetTemp = (SeekBar) findViewById(R.id.sbSetTemp);
        final EditText setTemp = (EditText) findViewById(R.id.setTemp);
        final EditText setTime = (EditText) findViewById(R.id.setTime);
        Button btnSetTemp = (Button) findViewById(R.id.btnSetTemp);
        tempAdapterList = new ArrayList<String>();

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
//                tempHelper.insertData(tempText.getText().toString());
                tempAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
//                tempText.setText("");
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
//            if(position%2 == 0) {
                result = inflater.inflate(R.layout.scheduled_temp_list, null);
//            } else {
//                result = inflater.inflate(R.layout.chat_row_outgoing, null);
//            }
            TextView temp = (TextView)result.findViewById(R.id.temp_text);
            temp.setText(   getItem(position)  ); // get the string at position
            return result;

        }

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
 //       SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);




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
