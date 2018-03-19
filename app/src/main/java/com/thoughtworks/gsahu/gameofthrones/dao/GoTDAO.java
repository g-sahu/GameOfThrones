package com.thoughtworks.gsahu.gameofthrones.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.thoughtworks.gsahu.gameofthrones.beans.Battle;
import com.thoughtworks.gsahu.gameofthrones.beans.King;
import com.thoughtworks.gsahu.gameofthrones.utils.GoTConstants;
import com.thoughtworks.gsahu.gameofthrones.utils.SQLConstants;

import java.util.ArrayList;

public class GoTDAO {
    private SQLiteDatabase db;
    private GoTDBHelper mDbHelper;
    private Context context;

    public GoTDAO(Context context) {
        this.context = context;
        mDbHelper = new GoTDBHelper(context);
        db = mDbHelper.getWritableDatabase();
    }

    //Closes SQLite database connection
    public void closeConnection() {
        if(mDbHelper != null) {
            mDbHelper.close();
        }

        if(db != null) {
            db.close();
        }
    }

    public ArrayList<Battle> getBattles() {
        ArrayList<Battle> battlesList = null;
        Battle battle;
        int c;

        Log.d(GoTConstants.LOG_TAG_SQL, SQLConstants.SQL_SELECT_BATTLES);
        Cursor cursor = db.rawQuery(SQLConstants.SQL_SELECT_BATTLES, null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            battlesList = new ArrayList<Battle>();

            while(!cursor.isAfterLast()) {
                battle = new Battle();
                c = GoTConstants.ZERO;

                battle.setBattleNum(cursor.getInt(c++));
                battle.setBattleName(cursor.getString(c++));
                battle.setBattleYear(cursor.getInt(c++));
                battle.setAttackerKing(cursor.getString(c++));
                battle.setDefenderKing(cursor.getString(c++));
                battle.setAttacker1(cursor.getString(c++));
                battle.setAttacker2(cursor.getString(c++));
                battle.setAttacker3(cursor.getString(c++));
                battle.setAttacker4(cursor.getString(c++));
                battle.setDefender1(cursor.getString(c++));
                battle.setDefender2(cursor.getString(c++));
                battle.setDefender3(cursor.getString(c++));
                battle.setDefender4(cursor.getString(c++));
                battle.setAttackerOutcome(cursor.getString(c++));
                battle.setBattleType(cursor.getString(c++));
                battle.setMajorDeath(cursor.getInt(c++));
                battle.setMajorCapture(cursor.getInt(c++));
                battle.setAttackerSize(cursor.getInt(c++));
                battle.setDefenderSize(cursor.getInt(c++));
                battle.setAttackerCommander(cursor.getString(c++));
                battle.setDefenderCommander(cursor.getString(c++));
                battle.setSummer(cursor.getInt(c++));
                battle.setLocation(cursor.getString(c++));
                battle.setRegion(cursor.getString(c++));
                battle.setNote(cursor.getString(c));

                battlesList.add(battle);
                cursor.moveToNext();
            }
        }

        cursor.close();
        return battlesList;
    }

    public King getKing(String name) {
        King king = null;
        Cursor cursor =  null;
        int c;
        String args[] = {name};

        try {
            Log.d(GoTConstants.LOG_TAG_SQL, SQLConstants.SQL_SELECT_KING);
            cursor = db.rawQuery(SQLConstants.SQL_SELECT_KING, args);

            if(cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                c = GoTConstants.ZERO;

                king = new King();
                king.setKingID(cursor.getInt(c++));
                king.setKingName(cursor.getString(c++));
                king.setRating(cursor.getInt(c));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(cursor != null) {
                cursor.close();
            }
        }

        return king;
    }

    public void insertKing(King king) {
        SQLiteStatement insertStmt = null;
        int c = GoTConstants.ONE;

        try {
            //Inserting King in table 'Kings'
            insertStmt = db.compileStatement(SQLConstants.SQL_INSERT_KING);
            insertStmt.bindString(c++, king.getKingName());
            insertStmt.bindLong(c, king.getRating());
            insertStmt.executeInsert();
        } catch(Exception e) {
            Log.e(GoTConstants.LOG_TAG_EXCEPTION, e.getMessage());
        } finally {
            if(insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public void updateKing(int kingID, int rating) {
        SQLiteStatement updateStmt = null;

        try {
            updateStmt = db.compileStatement(SQLConstants.SQL_UPDATE_KINGS);
            updateStmt.bindLong(1, rating);
            updateStmt.bindLong(2, kingID);
            Log.d(GoTConstants.LOG_TAG_SQL, updateStmt.toString());
            updateStmt.execute();
        } catch(Exception e) {
            Log.e(GoTConstants.LOG_TAG_EXCEPTION, e.getMessage());
        } finally {
            if(updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public ArrayList<King> getKings() {
        ArrayList<King> kingsList = null;
        King king;
        int c;

        Log.d(GoTConstants.LOG_TAG_SQL, SQLConstants.SQL_SELECT_KINGS);
        Cursor cursor = db.rawQuery(SQLConstants.SQL_SELECT_KINGS, null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            kingsList = new ArrayList<King>();

            while(!cursor.isAfterLast()) {
                king = new King();
                c = GoTConstants.ZERO;

                king.setKingID(cursor.getInt(c++));
                king.setKingName(cursor.getString(c++));
                king.setRating(cursor.getInt(c));

                kingsList.add(king);
                cursor.moveToNext();
            }
        }

        cursor.close();
        return kingsList;
    }
}