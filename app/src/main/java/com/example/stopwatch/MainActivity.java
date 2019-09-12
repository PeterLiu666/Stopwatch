package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
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
    private boolean isStart = false;
    private boolean running = false;
    private Chronometer timer;
    private long base = 0;
    private long pauseTime = 0;
    private long newTime = 0;
    public static final String KEY_CHRONOMETER_BASE = "chronometer base";
    public static final String KEY_CHRONOMETER_RUNNING = "chronometer running";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.button_start_main);
        stop = findViewById(R.id.button_stop_main);
        reset = findViewById(R.id.button_reset_main);
        timer = findViewById(R.id.chronometer_timer_main);








        setListener();
        Log.d(TAG, "onCreate: ");


        if(savedInstanceState != null)
        {
            if(savedInstanceState.getBoolean(KEY_CHRONOMETER_RUNNING))
            {
                timer.setBase(savedInstanceState.getLong(KEY_CHRONOMETER_BASE));
                timer.start();
            }
            else
            {
                timer.setBase(savedInstanceState.getLong(KEY_CHRONOMETER_BASE));

            }

        }
        

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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("chronometer base", timer.getBase());
        if(running)
        {
            outState.putBoolean("chronometer running", true);
        }
        else
        {
            outState.putBoolean("chronometer running", false);
        }

    }

    private void setListener()
    {
        running = true;
        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!isStart)
                {
                    timer.setBase(SystemClock.elapsedRealtime());
                    isStart = true;
                }
                else
                {

                    newTime = SystemClock.elapsedRealtime();

                    timer.setBase(base + (newTime - pauseTime));
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
                running = false;

                pauseTime = SystemClock.elapsedRealtime();
                base = timer.getBase();




            }
        });
        reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                timer.setBase(SystemClock.elapsedRealtime());
                isStart = false;

                running = false;

                timer.stop();



            }
        });

    }


}