package com.thoughtworks.gsahu.gameofthrones.dao;

/**
 * Created by gasahu on 08-Jan-17.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.thoughtworks.gsahu.gameofthrones.beans.Battle;
import com.thoughtworks.gsahu.gameofthrones.utils.GoTConstants;
import com.thoughtworks.gsahu.gameofthrones.utils.GoTLibraryManager;
import com.thoughtworks.gsahu.gameofthrones.utils.SQLConstants;

import java.util.ArrayList;
import java.util.Iterator;

class GoTDBHelper extends SQLiteOpenHelper {
    GoTDBHelper(Context context) {
        super(context, GoTContract.DATABASE_NAME, null, GoTContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SQLiteStatement insertStmt = null;
        ArrayList<Battle> battleList;
        Iterator<Battle> battleIterator;
        Battle battle;
        int c, rowsInserted = 0;

        try {
            //Creating table 'Battles'
            Log.d(GoTConstants.LOG_TAG_SQL, SQLConstants.SQL_CREATE_BATTLES);
            db.execSQL(SQLConstants.SQL_CREATE_BATTLES);

            //Creating table 'Kings'
            Log.d(GoTConstants.LOG_TAG_SQL, SQLConstants.SQL_CREATE_KINGS);
            db.execSQL(SQLConstants.SQL_CREATE_KINGS);

            //Getting list of battles from JSON API
            battleList = GoTLibraryManager.getBattleList();

            //Inserting battles in table 'Battles'
            insertStmt = db.compileStatement(SQLConstants.SQL_INSERT_BATTLE);

            if(battleList != null && !battleList.isEmpty()) {
                battleIterator = battleList.iterator();

                while (battleIterator.hasNext()) {
                    battle = battleIterator.next();
                    c = GoTConstants.ONE;

                    insertStmt.bindString(c++, battle.getBattleName());
                    insertStmt.bindLong(c++, battle.getBattleYear());
                    insertStmt.bindString(c++, battle.getAttackerKing());
                    insertStmt.bindString(c++, battle.getDefenderKing());
                    insertStmt.bindString(c++, battle.getAttacker1());
                    insertStmt.bindString(c++, battle.getAttacker2());
                    insertStmt.bindString(c++, battle.getAttacker3());
                    insertStmt.bindString(c++, battle.getAttacker4());
                    insertStmt.bindString(c++, battle.getDefender1());
                    insertStmt.bindString(c++, battle.getDefender2());
                    insertStmt.bindString(c++, battle.getDefender3());
                    insertStmt.bindString(c++, battle.getDefender4());
                    insertStmt.bindString(c++, battle.getAttackerOutcome());
                    insertStmt.bindString(c++, battle.getBattleType());
                    insertStmt.bindLong(c++, battle.getMajorDeath());
                    insertStmt.bindLong(c++, battle.getMajorCapture());
                    insertStmt.bindLong(c++, battle.getAttackerSize());
                    insertStmt.bindLong(c++, battle.getDefenderSize());
                    insertStmt.bindString(c++, battle.getAttackerCommander());
                    insertStmt.bindString(c++, battle.getDefenderCommander());
                    insertStmt.bindLong(c++, battle.getSummer());
                    insertStmt.bindString(c++, battle.getLocation());
                    insertStmt.bindString(c++, battle.getRegion());
                    insertStmt.bindString(c, battle.getNote());

                    Log.d(GoTConstants.LOG_TAG_SQL, insertStmt.toString());

                    try {
                        insertStmt.executeInsert();
                        ++rowsInserted;
                        insertStmt.clearBindings();
                    } catch (SQLException sqle) {
                        Log.e(GoTConstants.LOG_TAG_EXCEPTION, sqle.getMessage());
                    }
                }
            }

            Log.d("Battles added to db", String.valueOf(rowsInserted));
        } catch(Exception e) {
            Log.e(GoTConstants.LOG_TAG_EXCEPTION, e.getMessage());
        } finally {
            if(insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
