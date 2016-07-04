package com.example.android.habitracker;

import android.provider.BaseColumns;

/**
 * Habit Contract Class
 */
public class HabitContract {

    /*
        Inner class that defines the contents of the Habit Tracking App
     */
    public static final class HabitEntry implements BaseColumns {

        public static final String ID = "id";
        public static final String TABLE_NAME = "habit";

        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_HABIT = "habit";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_DATE = "date";
    }
}
