package com.example.android.habitracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.Map;
import java.util.Set;

/**
 * Created by me on 2/7/16.
 */
public class ApplicationTestUtilities extends AndroidTestCase {

    static ContentValues createHabitValues() {
        // Create a new map of values, where column names are the keys
        ContentValues testValues = new ContentValues();
        testValues.put(HabitContract.HabitEntry.COLUMN_LOCATION, "San Francisco");
        testValues.put(HabitContract.HabitEntry.COLUMN_NAME, "Justin");
        testValues.put(HabitContract.HabitEntry.COLUMN_DATE, 1345619256);
        testValues.put(HabitContract.HabitEntry.COLUMN_HABIT, "Eating Pizza");
        testValues.put(HabitContract.HabitEntry.ID, 1);

        return testValues;
    }

    static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for (Map.Entry<String, Object> entry : valueSet) {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse("Column '" + columnName + "' not found. " + error, idx == -1);
            String expectedValue = entry.getValue().toString();
            assertEquals("Value '" + entry.getValue().toString() +
                    "' did not match the expected value '" +
                    expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
        }
    }
}
