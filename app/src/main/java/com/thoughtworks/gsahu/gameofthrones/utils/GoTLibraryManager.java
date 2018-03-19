package com.thoughtworks.gsahu.gameofthrones.utils;

/**
 * Created by gasahu on 08-Jan-17.
 */

import android.content.Context;
import android.util.Log;

import com.thoughtworks.gsahu.gameofthrones.beans.Battle;
import com.thoughtworks.gsahu.gameofthrones.beans.King;
import com.thoughtworks.gsahu.gameofthrones.dao.GoTContract;
import com.thoughtworks.gsahu.gameofthrones.dao.GoTDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static com.thoughtworks.gsahu.gameofthrones.utils.GoTConstants.LOG_TAG_EXCEPTION;

public class GoTLibraryManager {
    private static String TAG = GoTLibraryManager.class.getSimpleName();

    public static void init(Context context) {
        GoTDAO dao;
        ArrayList<Battle> battleList;
        Battle battle;
        String name1, name2;
        King king1, king2;
        int rating1, rating2, newRating1, newRating2;
        double score1, score2;
        int kingID1, kingID2;
        int ratings[];

        try {
            dao = new GoTDAO(context);
            battleList = dao.getBattles();

            //Iterate thu battle list
            Iterator<Battle> itr = battleList.iterator();

            while(itr.hasNext()) {
                battle = itr.next();

                //Get Kings names
                name1 = battle.getAttackerKing();
                name2 = battle.getDefenderKing();

                //Get score
                switch(battle.getAttackerOutcome()) {
                    case GoTConstants.WIN:
                        score1 = GoTConstants.ONE;
                        score2 = GoTConstants.ZERO;
                        break;

                    case GoTConstants.LOSS:
                        score1 = GoTConstants.ZERO;
                        score2 = GoTConstants.ONE;
                        break;

                    default:
                        score1 = GoTConstants.HALF;
                        score2 = GoTConstants.HALF;
                }

                //Check if a row exists in table Kings for the current Kings
                king1 = dao.getKing(name1);
                king2 = dao.getKing(name2);

                //If row does not exist, take current rating = 400 and calculate new rating
                //Else if row(s) exists for current King(s), fetch current rating and use it to calculate new rating
                rating1 = (king1 != null) ? king1.getRating() : GoTConstants.N;
                rating2 = (king2 != null) ? king2.getRating() : GoTConstants.N;

                ratings = EloRating.calculateRating(rating1, rating2, score1, score2);
                newRating1 = ratings[GoTConstants.ZERO];
                newRating2 = ratings[GoTConstants.ONE];

                //Insert/Update row(s) in table Kings for current Kings with calculated rating
                if(name1 != null && !name1.isEmpty()) {
                    if (king1 != null) {
                        kingID1 = king1.getKingID();
                        dao.updateKing(kingID1, newRating1);
                    } else {
                        king1 = new King();
                        king1.setKingName(name1);
                        king1.setRating(newRating1);
                        dao.insertKing(king1);
                    }
                }

                if(name2 != null && !name2.isEmpty()) {
                    if (king2 != null) {
                        kingID2 = king2.getKingID();
                        dao.updateKing(kingID2, newRating2);
                    } else {
                        king2 = new King();
                        king2.setKingName(name2);
                        king2.setRating(newRating2);
                        dao.insertKing(king2);
                    }
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG_EXCEPTION, e.getMessage());
        }
    }

    public static ArrayList<Battle> getBattleList() {
        HttpConnector sh = new HttpConnector();
        ArrayList<Battle> battleList = null;
        JSONObject c;
        Battle battle;
        int l;

        // Making a request to url and getting response
        String url = "http://starlord.hackerearth.com/gotjson";
        String jsonStr = sh.makeServiceCall(url);
        Log.e(TAG, "Response from url: " + jsonStr);

        if(jsonStr != null) {
            battleList = new ArrayList<Battle>();

            try {
                //JSONObject jsonObj = new JSONObject(jsonStr);
                JSONArray battles = new JSONArray(jsonStr);

                // Getting JSON Array node
                //JSONArray battles = jsonObj.getJSONArray();
                l = battles.length();

                // looping through All Contacts
                for(int i=0; i<l; i++) {
                    c = battles.getJSONObject(i);
                    battle = new Battle();

                    battle.setBattleName(c.getString(GoTContract.Battles.BATTLE_NAME));
                    battle.setBattleYear(c.getInt(GoTContract.Battles.BATTLE_YEAR));
                    battle.setBattleNum(c.getInt(GoTContract.Battles.BATTLE_NUM));
                    battle.setAttackerKing(c.getString(GoTContract.Battles.ATTACKER_KING));
                    battle.setDefenderKing(c.getString(GoTContract.Battles.DEFENDER_KING));
                    battle.setAttacker1(c.getString(GoTContract.Battles.ATTACKER1));
                    battle.setAttacker2(c.getString(GoTContract.Battles.ATTACKER2));
                    battle.setAttacker3(c.getString(GoTContract.Battles.ATTACKER3));
                    battle.setAttacker4(c.getString(GoTContract.Battles.ATTACKER4));
                    battle.setDefender1(c.getString(GoTContract.Battles.DEFENDER1));
                    battle.setDefender2(c.getString(GoTContract.Battles.DEFENDER2));
                    battle.setDefender3(c.getString(GoTContract.Battles.DEFENDER3));
                    battle.setDefender4(c.getString(GoTContract.Battles.DEFENDER4));
                    battle.setAttackerOutcome(c.getString(GoTContract.Battles.ATTACKER_OUTCOME));
                    battle.setBattleType(c.getString(GoTContract.Battles.BATTLE_TYPE));
                    battle.setMajorDeath(c.getInt(GoTContract.Battles.MAJOR_DEATH));
                    battle.setMajorCapture(c.getInt(GoTContract.Battles.MAJOR_CAPTURE));
                    battle.setAttackerSize(getIntegerValue(c.getString(GoTContract.Battles.ATTACKER_SIZE)));
                    battle.setDefenderSize(getIntegerValue(c.getString(GoTContract.Battles.DEFENDER_SIZE)));
                    battle.setAttackerCommander(c.getString(GoTContract.Battles.ATTACKER_COMMANDER));
                    battle.setDefenderCommander(c.getString(GoTContract.Battles.DEFENDER_COMMANDER));
                    battle.setSummer(getIntegerValue(c.getString(GoTContract.Battles.SUMMER)));
                    battle.setLocation(c.getString(GoTContract.Battles.LOCATION));
                    battle.setRegion(c.getString(GoTContract.Battles.REGION));
                    battle.setNote(c.getString(GoTContract.Battles.NOTE));

                    battleList.add(battle);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }

        return battleList;
    }

    static int getIntegerValue(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(s);
        }
    }
}
