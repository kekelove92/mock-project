package group6.fga.fsoft.com.mockproject_group6.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import group6.fga.fsoft.com.mockproject_group6.model.Contract;
import group6.fga.fsoft.com.mockproject_group6.model.entity.Lesson;


public class LessonDatabase {
    SQLiteDatabase mSqLiteDatabase;

    public LessonDatabase(Context c, DBManager dbManager) {
        mSqLiteDatabase = dbManager.getWritableDatabase();
    }

    // Add
    public void addLesson(Lesson lesson) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.LESSON_NAME, lesson.getmName());
        mSqLiteDatabase.insert(Contract.TABLE_LESSON, null, contentValues);
    }

    // Update
    public void updateLesson(int id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.LESSON_NAME, name);
        mSqLiteDatabase.update(Contract.TABLE_LESSON, contentValues, Contract.LESSON_ID + " =? ",
                new String[]{id + ""});
    }

    // Delete
    public int deleteLesson(int id) {
        return mSqLiteDatabase.delete(Contract.TABLE_LESSON, Contract.LESSON_ID + "=?", new String[]{id + ""});
    }

    // Get all data from table
    public Cursor getAllLesson() {
        return mSqLiteDatabase.rawQuery("SELECT * FROM " + Contract.TABLE_LESSON, null);
    }

    // Check name of lesson already exist
    public boolean checkName(String name) {
        Boolean check = true;
        Cursor cursor = mSqLiteDatabase.rawQuery("SELECT * FROM " + Contract.TABLE_LESSON, null);
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndexOrThrow(Contract.LESSON_NAME)).equals(name)) {
                check = false;
                break;
            }

        }
        return check;
    }

    public int getLessonsCount() {
        String countQuery = "SELECT  * FROM " + Contract.TABLE_LESSON;

        Cursor cursor = mSqLiteDatabase.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }
}
