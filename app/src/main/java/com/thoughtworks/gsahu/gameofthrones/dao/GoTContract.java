package com.thoughtworks.gsahu.gameofthrones.dao;

import android.provider.BaseColumns;

public final class GoTContract {
    static final String DATABASE_NAME = "GAME_OF_THRONES";
    static final int DATABASE_VERSION = 1;

    public static abstract class Battles implements BaseColumns {
        public static final String TABLE_NAME = "battles";
        public static final String BATTLE_NAME = "name";
        public static final String BATTLE_YEAR = "year";
        public static final String BATTLE_NUM = "battle_number";
        public static final String ATTACKER_KING = "attacker_king";
        public static final String DEFENDER_KING = "defender_king";
        public static final String ATTACKER1 = "attacker_1";
        public static final String ATTACKER2 = "attacker_2";
        public static final String ATTACKER3 = "attacker_3";
        public static final String ATTACKER4 = "attacker_4";
        public static final String DEFENDER1 = "defender_1";
        public static final String DEFENDER2 = "defender_2";
        public static final String DEFENDER3 = "defender_3";
        public static final String DEFENDER4 = "defender_4";
        public static final String ATTACKER_OUTCOME = "attacker_outcome";
        public static final String BATTLE_TYPE = "battle_type";
        public static final String MAJOR_DEATH = "major_death";
        public static final String MAJOR_CAPTURE = "major_capture";
        public static final String ATTACKER_SIZE = "attacker_size";
        public static final String DEFENDER_SIZE = "defender_size";
        public static final String ATTACKER_COMMANDER = "attacker_commander";
        public static final String DEFENDER_COMMANDER = "defender_commander";
        public static final String SUMMER = "summer";
        public static final String LOCATION = "location";
        public static final String REGION = "region";
        public static final String NOTE = "note";
    }

    public static abstract class Kings implements BaseColumns {
        public static final String TABLE_NAME = "kings";
        public static final String KING_ID = "king_id";
        public static final String KING_NAME = "king_name";
        public static final String RATING = "rating";
    }
}
