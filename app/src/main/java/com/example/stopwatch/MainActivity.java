package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    // Look up the Log class for android
    // List all levels of logging and when they're used
    
    /*________________________________________________________
     * V: Verbose(lowest priority)
     * D: Debug
     * I: Info
     * W: Warning
     * E: Error
     * 
     *_______________________________________________________*/
    
    private Button start;
    private Button stop;
    private Button reset;
    private boolean pause;
    private Chronometer timer;
    private long base = 0;
    private long newBase = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.button_start_main);
        stop = findViewById(R.id.button_stop_main);
        reset = findViewById(R.id.button_reset_main);
        timer = findViewById(R.id.chronometer_timer_main);
        pause = true;





        setListener();
        Log.d(TAG, "onCreate: ");
        

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    // Activity is running
    // Another activity is covering part of this one
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }
    // This activity is completely hidden
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }
    // when activity is finished
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    private void setListener()
    {
        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(base == 0)
                {
                    timer.setBase(SystemClock.elapsedRealtime());
                }
                else
                {
                    newBase = SystemClock.elapsedRealtime() - timer.getBase();

                    timer.setBase(base - newBase);
                }


                timer.start();


            }
        });
        stop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                timer.stop();
                base = SystemClock.elapsedRealtime() - timer.getBase();




            }
        });
        reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                timer.setBase(SystemClock.elapsedRealtime());
                base = timer.getBase();

                timer.stop();



            }
        });

    }


}