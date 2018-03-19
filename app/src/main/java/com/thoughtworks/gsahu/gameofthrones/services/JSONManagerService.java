package com.thoughtworks.gsahu.gameofthrones.services;

/**
 * Created by gasahu on 08-Jan-17.
 */

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.thoughtworks.gsahu.gameofthrones.utils.GoTConstants;
import com.thoughtworks.gsahu.gameofthrones.utils.GoTLibraryManager;

public class JSONManagerService extends IntentService {

    public JSONManagerService() {
        super("JSONManagerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("JSONManagerService", "Inside JSONManagerService");

        //Initialising/Updating Mediaplayer library
        GoTLibraryManager.init(this);

        //Sending broadcast indicating that JSONManagerService has finished initiliasing/updating the library
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(GoTConstants.ACTION_SERVICE_BROADCAST);
        sendBroadcast(broadcastIntent);

    }
}