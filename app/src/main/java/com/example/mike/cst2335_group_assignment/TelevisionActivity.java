package com.example.mike.cst2335_group_assignment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mike.cst2335_group_assignment.Database.Helper;

import java.util.ArrayList;

public class TelevisionActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "Television activity ";
    ImageButton enterButton;
    ImageButton leftArrow;
    ImageButton rightArrow;
    ImageButton downArrow;
    ImageButton upArrow;
    Button buttonOnOff;
    Button favButton;
    EditText channel;
    TextView tvOnOff;
    ArrayList<Integer> favChannels = new ArrayList<>();
    private Helper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_television);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
     
        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TelevisionActivity.this, "You clicked the Down Arrow", Toast.LENGTH_SHORT).show();
            }
        });
        upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TelevisionActivity.this, "You clicked the Up Arrow", Toast.LENGTH_SHORT).show();
            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TelevisionActivity.this, "You clicked the Right Arrow", Toast.LENGTH_SHORT).show();
            }
        });
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TelevisionActivity.this, "You clicked the Left Arrow", Toast.LENGTH_SHORT).show();
            }
        });

        buttonOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clicked = 0;
                if (clicked != 1) {
                    Toast.makeText(TelevisionActivity.this, "You put TV to sleep", Toast.LENGTH_SHORT).show();
                    clicked +=1;
                }
                else {
                    Toast.makeText(TelevisionActivity.this, "You woke the TV up", Toast.LENGTH_SHORT).show();
                    clicked-=1;
                }
            }
        });
        buttonOnOff.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                 int longClicked =0;
                if (longClicked == 0) {
                    Toast.makeText(TelevisionActivity.this, "You shut tv off", Toast.LENGTH_SHORT).show();
                    tvOnOff.setText("Tv is OFF");
                    longClicked += 1;
                }else{
                    Toast.makeText(TelevisionActivity.this, "You turned the TV back on", Toast.LENGTH_SHORT).show();
                    tvOnOff.setText("TV is ON");
                    longClicked -= 1;
                }
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
                return false;
            }
        });
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        helper      = new Helper(this);
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
