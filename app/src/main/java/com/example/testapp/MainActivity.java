package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout greenLight;
    private LinearLayout yellowLight;
    private LinearLayout redLight;
    private Button startButton;
    private boolean isCounterOn = false;
    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        greenLight = findViewById(R.id.greenLight);
        yellowLight = findViewById(R.id.yellowLight);
        redLight = findViewById(R.id.redLight);
        startButton = findViewById(R.id.button);
    }

    @SuppressLint("SetTextI18n")
    public void onClickStart(View view) {
        if (!isCounterOn) {
            isCounterOn = true;
            startButton.setText("STOP");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isCounterOn) {
                        counter++;
                        switch (counter) {
                            case 1:
                                greenLight.setBackgroundColor(Color.GREEN);
                                yellowLight.setBackgroundColor(Color.GRAY);
                                redLight.setBackgroundColor(Color.GRAY);
                                break;
                            case 2:
                                greenLight.setBackgroundColor(Color.GRAY);
                                yellowLight.setBackgroundColor(Color.YELLOW);
                                redLight.setBackgroundColor(Color.GRAY);
                                break;
                            case 3:
                                greenLight.setBackgroundColor(Color.GRAY);
                                yellowLight.setBackgroundColor(Color.GRAY);
                                redLight.setBackgroundColor(Color.RED);
                                counter = 0;
                                break;
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } else {
            isCounterOn = false;
            startButton.setText("START");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.isCounterOn = false;
    }
}
