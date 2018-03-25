package com.example.android.hylda_1202154346_studycase5;

import android.provider.BaseColumns;

/**
 * Created by hp on 25/03/2018.
 */

public class dbContract {
    public static final class DatabaseScheme implements BaseColumns {
        public static final String TABLE_NAME = "table_name";
        public static final String ID_DATABASE = "ID";
        public static final String TODO_NAME ="todoname";
        public static final String DESCRIPTION = "description";
        public static final String PRIORITY = "priority";
    }

}
