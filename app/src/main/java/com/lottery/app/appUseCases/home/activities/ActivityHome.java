package com.lottery.app.appUseCases.home.activities;


import android.os.Bundle;

import com.lottery.app.R;
import com.lottery.app.helperPackages.baseClasses.LotteryBaseActivity;

public class ActivityHome extends LotteryBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
