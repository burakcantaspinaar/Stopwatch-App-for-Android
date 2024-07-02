package com.example.kronometre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    Chronometer kronometreTV;
    Button startButton, resetButton;
    int number = 0;
    long data;
    int resetNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kronometreTV = findViewById(R.id.kronometreTv);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number==0)
                {
                    kronometreTV.setBase(SystemClock.elapsedRealtime());
                    kronometreTV.start();
                    startButton.setText("PAUSE");
                    number = 1;

                } else if (number==1)
                {
                    data = SystemClock.elapsedRealtime();
                    kronometreTV.stop();
                    startButton.setText("START");
                    number = 2;
                }
                else if (number==2)
                {
                    kronometreTV.setBase(kronometreTV.getBase() + SystemClock.elapsedRealtime() - data);
                    startButton.setText("PAUSE");
                    kronometreTV.start();
                    number = 1;
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetNumber == 0)
                {
                    kronometreTV.stop();
                    kronometreTV.setBase(SystemClock.elapsedRealtime());
                    startButton.setText("START");
                    number = 0;
                }
            }
        });
    }
}