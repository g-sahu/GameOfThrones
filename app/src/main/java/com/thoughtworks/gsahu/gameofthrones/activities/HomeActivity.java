package com.thoughtworks.gsahu.gameofthrones.activities;

/**
 * Created by gasahu on 08-Jan-17.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.thoughtworks.gsahu.gameofthrones.R;
import com.thoughtworks.gsahu.gameofthrones.adapters.KingsListAdapter;
import com.thoughtworks.gsahu.gameofthrones.beans.King;
import com.thoughtworks.gsahu.gameofthrones.dao.GoTDAO;

import java.util.ArrayList;

import static com.thoughtworks.gsahu.gameofthrones.utils.GoTConstants.LOG_TAG_EXCEPTION;

public class HomeActivity extends AppCompatActivity {
    private final static String LOG_TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(LOG_TAG, "HomeActivity created");

        Toast toast = Toast.makeText(this, "LIBRARY UPDATED", Toast.LENGTH_LONG);
        toast.show();

        GoTDAO dao = new GoTDAO(this);

        //Fetching all kings from database
        ArrayList<King> kingsList = dao.getKings();

        ListView listView;listView = (ListView) findViewById(R.id.listView);
        ListAdapter playlistAdapter = new KingsListAdapter(this, kingsList);
        listView.setAdapter(playlistAdapter);
    }
}