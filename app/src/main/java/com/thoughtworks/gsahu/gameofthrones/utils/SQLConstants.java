package com.thoughtworks.gsahu.gameofthrones.utils;

import com.thoughtworks.gsahu.gameofthrones.dao.GoTContract;

public final class SQLConstants {
    private static final String PRIMARY_KEY = " PRIMARY KEY ";
    private static final String FOREIGN_KEY = " FOREIGN KEY ";
    private static final String AUTOINCREMENT = " AUTOINCREMENT ";
    private static final String REFERENCES = " REFERENCES ";
    private static final String NOT_NULL = " NOT NULL ";
    private static final String UNIQUE = " UNIQUE ";
    private static final String TEXT = " TEXT ";
    private static final String INTEGER = " INTEGER ";
    private static final String BLOB = " BLOB ";
    private static final String COMMA_SEP = ", ";
    static final String DD_MM_YYYY = "dd-MM-yyyy";

    // Create tables
    public static final String SQL_CREATE_BATTLES =
            "CREATE TABLE " + GoTContract.Battles.TABLE_NAME + " (" +
                    GoTContract.Battles.BATTLE_NUM + INTEGER + NOT_NULL + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
                    GoTContract.Battles.BATTLE_NAME + TEXT + COMMA_SEP +
                    GoTContract.Battles.BATTLE_YEAR + INTEGER + NOT_NULL + COMMA_SEP +
                    GoTContract.Battles.ATTACKER_KING + TEXT + COMMA_SEP +
                    GoTContract.Battles.DEFENDER_KING + TEXT + COMMA_SEP +
                    GoTContract.Battles.ATTACKER1 + TEXT + COMMA_SEP +
                    GoTContract.Battles.ATTACKER2 +	TEXT + COMMA_SEP +
                    GoTContract.Battles.ATTACKER3 + TEXT + COMMA_SEP +
                    GoTContract.Battles.ATTACKER4 + TEXT + COMMA_SEP +
                    GoTContract.Battles.DEFENDER1 + TEXT + COMMA_SEP +
                    GoTContract.Battles.DEFENDER2 + TEXT + COMMA_SEP +
                    GoTContract.Battles.DEFENDER3 + TEXT + COMMA_SEP +
                    GoTContract.Battles.DEFENDER4 + TEXT + COMMA_SEP +
                    GoTContract.Battles.ATTACKER_OUTCOME + TEXT + COMMA_SEP +
                    GoTContract.Battles.BATTLE_TYPE + TEXT + COMMA_SEP +
                    GoTContract.Battles.MAJOR_DEATH + INTEGER + COMMA_SEP +
                    GoTContract.Battles.MAJOR_CAPTURE + INTEGER + COMMA_SEP +
                    GoTContract.Battles.ATTACKER_SIZE + INTEGER + COMMA_SEP +
                    GoTContract.Battles.DEFENDER_SIZE + INTEGER + COMMA_SEP +
                    GoTContract.Battles.ATTACKER_COMMANDER + TEXT + COMMA_SEP +
                    GoTContract.Battles.DEFENDER_COMMANDER + TEXT + COMMA_SEP +
                    GoTContract.Battles.SUMMER + INTEGER + COMMA_SEP +
                    GoTContract.Battles.LOCATION + TEXT + COMMA_SEP +
                    GoTContract.Battles.REGION + TEXT + COMMA_SEP +
                    GoTContract.Battles.NOTE + TEXT +
                    " )";

    public static final String SQL_CREATE_KINGS =
            "CREATE TABLE " + GoTContract.Kings.TABLE_NAME + " (" +
                    GoTContract.Kings.KING_ID + INTEGER + NOT_NULL + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
                    GoTContract.Kings.KING_NAME + TEXT + COMMA_SEP +
                    GoTContract.Kings.RATING + INTEGER +
                    " )";

    //Insert queries
    public static final String SQL_INSERT_BATTLE =
            "INSERT INTO " + GoTContract.Battles.TABLE_NAME + "(" +
                    GoTContract.Battles.BATTLE_NAME + COMMA_SEP +
                    GoTContract.Battles.BATTLE_YEAR + COMMA_SEP +
                    GoTContract.Battles.ATTACKER_KING + COMMA_SEP +
                    GoTContract.Battles.DEFENDER_KING + COMMA_SEP +
                    GoTContract.Battles.ATTACKER1 + COMMA_SEP +
                    GoTContract.Battles.ATTACKER2 + COMMA_SEP +
                    GoTContract.Battles.ATTACKER3 + COMMA_SEP +
                    GoTContract.Battles.ATTACKER4 + COMMA_SEP +
                    GoTContract.Battles.DEFENDER1 + COMMA_SEP +
                    GoTContract.Battles.DEFENDER2 + COMMA_SEP +
                    GoTContract.Battles.DEFENDER3 + COMMA_SEP +
                    GoTContract.Battles.DEFENDER4 + COMMA_SEP +
                    GoTContract.Battles.ATTACKER_OUTCOME + COMMA_SEP +
                    GoTContract.Battles.BATTLE_TYPE + COMMA_SEP +
                    GoTContract.Battles.MAJOR_DEATH + COMMA_SEP +
                    GoTContract.Battles.MAJOR_CAPTURE + COMMA_SEP +
                    GoTContract.Battles.ATTACKER_SIZE + COMMA_SEP +
                    GoTContract.Battles.DEFENDER_SIZE + COMMA_SEP +
                    GoTContract.Battles.ATTACKER_COMMANDER + COMMA_SEP +
                    GoTContract.Battles.DEFENDER_COMMANDER + COMMA_SEP +
                    GoTContract.Battles.SUMMER + COMMA_SEP +
                    GoTContract.Battles.LOCATION + COMMA_SEP +
                    GoTContract.Battles.REGION + COMMA_SEP +
                    GoTContract.Battles.NOTE +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_INSERT_KING =
            "INSERT INTO " + GoTContract.Kings.TABLE_NAME + "(" +
                    GoTContract.Kings.KING_NAME + COMMA_SEP +
                    GoTContract.Kings.RATING +
                    ") VALUES (?, ?)";

    //Select queries
    public static final String SQL_SELECT_BATTLES =
            "SELECT * FROM " + GoTContract.Battles.TABLE_NAME;

    public static final String SQL_SELECT_KINGS =
            "SELECT * FROM " + GoTContract.Kings.TABLE_NAME;

    public static final String SQL_SELECT_KING =
            "SELECT * FROM " + GoTContract.Kings.TABLE_NAME +
                    " WHERE " + GoTContract.Kings.KING_NAME + " = ?";

    //Update queries
    public static final String SQL_UPDATE_KINGS =
            "UPDATE " + GoTContract.Kings.TABLE_NAME + " SET " +
                    GoTContract.Kings.RATING + " = ?" + COMMA_SEP +
                    " WHERE " + GoTContract.Kings.KING_ID + " = ?";
}
