package com.lottery.app.helperPackages.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lottery.app.appUseCases.pick.activities.ActivityPick;

public class RoutingUtils {

    public static void openWindoq1(Context context, Bundle options) {
        Intent intent = new Intent(context, ActivityPick.class);
        if (options != null) intent.putExtras(options);
        context.startActivity(intent);
    }
}
