package com.example.android.habitracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import java.util.HashSet;

/**
 * Test DB
 */
public class ApplicationTestDb extends AndroidTestCase {

    public static final String LOG_TAG = ApplicationTestDb.class.getSimpleName();

    public void testCreateDb() throws Throwable {

        // build a HashSet of all of the table names we wish to look for
        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(HabitContract.HabitEntry.TABLE_NAME);

        mContext.deleteDatabase(HabitDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new HabitDbHelper(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());

        // To check if the table has been created or not
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error: This means that the database has not been created correctly",
                c.moveToFirst());

        // verify that the tables have been created
        do {
            tableNameHashSet.remove(c.getString(0));
        } while( c.moveToNext() );

        // Check Database contains HabitEntry Table
        assertTrue("Error: Your database was created without Habit Entry tables",
                tableNameHashSet.isEmpty());

        // Check if tables contain correct columns
        c = db.rawQuery("PRAGMA table_info(" + HabitContract.HabitEntry.TABLE_NAME + ")",
                null);

        assertTrue("Error: This means that we were unable to query the database for table information.",
                c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> habitColumnHashSet = new HashSet<String>();
        habitColumnHashSet.add(HabitContract.HabitEntry.ID);
        habitColumnHashSet.add(HabitContract.HabitEntry.COLUMN_NAME);
        habitColumnHashSet.add(HabitContract.HabitEntry.COLUMN_DATE);
        habitColumnHashSet.add(HabitContract.HabitEntry.COLUMN_HABIT);
        habitColumnHashSet.add(HabitContract.HabitEntry.COLUMN_LOCATION);

        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            habitColumnHashSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that database doesn't contain all of the required
        // entry columns
        assertTrue("Error: The database doesn't contain all of the required entry columns",
                habitColumnHashSet.isEmpty());
        db.close();
    }

    public void testHabitTable() {
        HabitDbHelper dbHelper = new HabitDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues testValues = ApplicationTestUtilities.createHabitValues();
        long habitRowID = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, testValues);

        // Verify we got a row back
        assertTrue(habitRowID != -1);

        Cursor cursor = db.query(
                HabitContract.HabitEntry.TABLE_NAME,  // Table to Query
                null, // all columns
                null, // Columns for the "where" clause
                null, // Values for the "where" clause
                null, // columns to group by
                null, // columns to filter by row groups
                null // sort order
        );

        assertTrue( "Error: No Records returned from query.", cursor.moveToFirst() );

        ApplicationTestUtilities.validateCurrentRecord("Error: Query Validation Failed",
                cursor, testValues);
        cursor.close();
        db.close();
    }
}