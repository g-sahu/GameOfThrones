package com.thoughtworks.gsahu.gameofthrones.services;

/**
 * Created by gasahu on 08-Jan-17.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.thoughtworks.gsahu.gameofthrones.activities.HomeActivity;

public class GoTReceiver extends BroadcastReceiver {
    public GoTReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MediaPlayerReceiver", "Broadcast received");

        intent.setClass(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
