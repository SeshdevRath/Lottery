package com.lottery.app.appUseCases.home.activities;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lottery.app.R;
import com.lottery.app.helperPackages.baseClasses.LotteryBaseActivity;
import com.lottery.app.helperPackages.utils.RoutingUtils;

public class ActivityHome extends LotteryBaseActivity {
    private Button buttonWindow1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        buttonWindow1= findViewById(R.id.button_window_1);

        buttonWindow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoutingUtils.openWindoq1(ActivityHome.this,  null);
            }
        });
    }

    @Override
    public void onBackPressed() {
        
    }
}
