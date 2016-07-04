package com.example.android.habitracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * HabitDbHelper.java
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "habitTracker.db";

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " (" +
                HabitContract.HabitEntry.ID + " INTEGER PRIMARY KEY," +
                HabitContract.HabitEntry.COLUMN_NAME+ " TEXT UNIQUE NOT NULL, " +
                HabitContract.HabitEntry.COLUMN_LOCATION + " TEXT NOT NULL, " +
                HabitContract.HabitEntry.COLUMN_HABIT + " TEXT NOT NULL, " +
                HabitContract.HabitEntry.COLUMN_DATE + " INTEGER NOT NULL " +
                " );";
        sqLiteDatabase.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HabitContract.HabitEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    // Insert data in the table
    public boolean insertData(int id, String name, String location, String habit, int date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitContract.HabitEntry.ID, id);
        contentValues.put(HabitContract.HabitEntry.COLUMN_NAME, name);
        contentValues.put(HabitContract.HabitEntry.COLUMN_LOCATION, location);
        contentValues.put(HabitContract.HabitEntry.COLUMN_HABIT, habit);
        contentValues.put(HabitContract.HabitEntry.COLUMN_DATE, date);
        db.insert(HabitContract.HabitEntry.TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // Get data from the table
    public Cursor getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * from " + HabitContract.HabitEntry.TABLE_NAME +
                " WHERE name=" + name, null);
        db.close();
        return result;
    }

    // Delete all table entries
    public int deleteAllEntries() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(HabitContract.HabitEntry.TABLE_NAME, null, null);
    }

    // Delete the database
    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(HabitContract.HabitEntry.TABLE_NAME);
    }

    // Update data in the table
    public boolean updateData(int id, String name, String location, String habit, int date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitContract.HabitEntry.ID, id);
        contentValues.put(HabitContract.HabitEntry.COLUMN_NAME, name);
        contentValues.put(HabitContract.HabitEntry.COLUMN_LOCATION, location);
        contentValues.put(HabitContract.HabitEntry.COLUMN_HABIT, habit);
        contentValues.put(HabitContract.HabitEntry.COLUMN_DATE, date);
        db.update(HabitContract.HabitEntry.TABLE_NAME, contentValues, " id = ? ",
                new String[]{Integer.toString(id)});
        db.close();
        return true;
    }

}
