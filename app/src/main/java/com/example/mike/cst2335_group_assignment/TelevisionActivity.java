package com.example.mike.cst2335_group_assignment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mike.cst2335_group_assignment.Database.Helper;

import java.util.ArrayList;

public class TelevisionActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "TelevisionActivity ";
    private ImageButton enterButton;
    private ImageButton leftArrow;
    private ImageButton rightArrow;
    private ImageButton downArrow;
    private ImageButton upArrow;
    private Button buttonOnOff;
    private Button favButton;
    private EditText channel;
    private TextView tvOnOff;
    private ArrayList<Integer> favChannels = new ArrayList<>();
    private Helper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_television);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        final SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        final int favChannelText = prefs.getInt("FavChannel", 000);
        channel.setText(String.valueOf(favChannelText));
        tvOnOff.setText(prefs.getString("tvOnoff", "Tv is off"));
        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int favchannel = prefs.getInt("FavChannel", 20);
                favchannel--;
                channel.setText(String.valueOf(favchannel));
                editor.putInt("FavChannel", favchannel);
                editor.commit();
                Toast.makeText(TelevisionActivity.this, "channel is now " + favchannel, Toast.LENGTH_SHORT).show();
            }
        });
        upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int favchannel = prefs.getInt("FavChannel", 20);
                favchannel++;
                channel.setText(String.valueOf(favchannel));
                editor.putInt("FavChannel", favchannel);
                editor.commit();
                Toast.makeText(TelevisionActivity.this, "channel is now " + favchannel, Toast.LENGTH_SHORT).show();
            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int TVVolumn = prefs.getInt("Volumn", 20);
                TVVolumn++;
                editor.putInt("Volumn", TVVolumn);
                editor.commit();
                Toast.makeText(TelevisionActivity.this, "Your volumn is now set to " + TVVolumn, Toast.LENGTH_SHORT).show();
            }
        });
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int TVVolumn = prefs.getInt("Volumn", 20);
                TVVolumn--;
                editor.putInt("Volumn", TVVolumn);
                editor.commit();
                Toast.makeText(TelevisionActivity.this, "Your volumn is now set to " + TVVolumn, Toast.LENGTH_SHORT).show();
            }
        });
        buttonOnOff.setOnLongClickListener(new View.OnLongClickListener() {
            int longClicked =0;
            @Override
            public boolean onLongClick(View v) {

                if (longClicked++ % 2 == 0) {
                    tvOnOff.setText(R.string.tvon);
                }else{
                    tvOnOff.setText(R.string.tvoff);
                }
                editor.putString("tvOnoff", tvOnOff.getText().toString());
                editor.commit();
                return false;
            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (channel.getText().toString() == null) {
                    Toast.makeText(TelevisionActivity.this, "There is no channel to select", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TelevisionActivity.this, "Chanel " + channel.getText().toString() + " is selected", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("FavChannel", Integer.valueOf(channel.getText().toString()) );
                    editor.commit();
                }
            }
        });
        enterButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(ACTIVITY_NAME, "Enter button is clicked and held");
                AlertDialog.Builder builder = new AlertDialog.Builder(TelevisionActivity.this);
                final String channelToString = channel.getText().toString();
                builder.setTitle("Would you like to save " + channelToString + " to the favorites?" );
                builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            int chanel = Integer.valueOf(channelToString);
                            favChannels.add(chanel);
                            Log.i(ACTIVITY_NAME, "Array list has " + favChannels);
                            helper.inserData(chanel);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putInt("FavChannel", Integer.valueOf(channel.getText().toString()) );
                            editor.commit();
                        }catch(NumberFormatException numEx){
                            Log.e(ACTIVITY_NAME, "That was not a integer...");
                        }
                    }
                });
                builder.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(ACTIVITY_NAME, "You clicked no, Chanel not saved");
                        Snackbar.make(findViewById(android.R.id.content), "Channel " + channelToString + " is not saved to favorites", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    }
                });
                builder.show();
                return false;
            }
        });
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelevisionActivity.this, FavoritesChannel.class);
                startActivity(intent);
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
    
    
    
    public void init(){
        enterButton = (ImageButton) findViewById(R.id.enterButton);
        leftArrow   = (ImageButton) findViewById(R.id.leftArrow);
        rightArrow  = (ImageButton) findViewById(R.id.rightArrow);
        downArrow   = (ImageButton) findViewById(R.id.downArrow);
        upArrow     = (ImageButton) findViewById(R.id.upArrow);
        buttonOnOff = (Button)      findViewById(R.id.onOffButton);
        favButton   = (Button)      findViewById(R.id.favButton);
        channel     = (EditText)    findViewById(R.id.channelNumber);
        tvOnOff     = (TextView)    findViewById(R.id.TvOnOFf);
        helper      = new Helper(this);
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
                intent = new Intent(TelevisionActivity.this, HomeScreen.class);
                Log.i(ACTIVITY_NAME, "Going to Home Screen");
                startActivity(intent);
                break;
            case R.id.actionLivingRoom:
                Toast.makeText(TelevisionActivity.this, "You are already here !!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionHouseSetting:
                intent = new Intent(TelevisionActivity.this, HouseSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content),R.string.about, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.Help:
                intent = new Intent(TelevisionActivity.this, LRHelpActivity.class);
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
