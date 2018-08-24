package com.karacken.datetabindicator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private DateTabIndicator dateTabIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateTabIndicator = findViewById(R.id.date_tab_indicator);
        dateTabIndicator.setDays(Arrays.asList(new Integer[]{0,0,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31}));

    }
}
