package com.example.javame6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class TwoA extends AppCompatActivity {

    ProgressBar pbCount;
    TextView tvInfo;
    CheckBox chbInfo;
    int cnt;
    final String LOG_TAG = "myLogs";
    final int max = 100;
    Handler h;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        h = new Handler();
        pbCount = findViewById(R.id.pbCount);
        pbCount.setMax(max);
        pbCount.setProgress(0);
        tvInfo = findViewById(R.id.tvInfo);
        chbInfo = findViewById(R.id.chbInfo);
        chbInfo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tvInfo.setVisibility(View.VISIBLE);

                h.post(showInfo);
            } else {
                tvInfo.setVisibility(View.GONE);

                h.removeCallbacks(showInfo);
            }
        });
        Thread t = new Thread(() -> {
            try {
                for (cnt = 1; cnt < max; cnt++) {
                    TimeUnit.MILLISECONDS.sleep(100);

                    h.post(updateProgress);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

    Runnable updateProgress = new Runnable() {
        public void run() {
            pbCount.setProgress(cnt);
        }
    };

    Runnable showInfo = new Runnable() {
        public void run() {
            Log.d(LOG_TAG, "showInfo");
            tvInfo.setText("Count = " + cnt);

            h.postDelayed(showInfo, 1000);
        }
    };
}