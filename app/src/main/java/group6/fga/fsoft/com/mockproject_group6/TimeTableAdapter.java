package group6.fga.fsoft.com.mockproject_group6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import group6.fga.fsoft.com.mockproject_group6.model.Contract;
import group6.fga.fsoft.com.mockproject_group6.model.entity.TimetableCell;


public class TimeTableAdapter {
    SQLiteDatabase mSqLiteDatabase;
    private DBManager dbManager;

    public TimeTableAdapter(Context c) {
        dbManager = new DBManager(c);
        mSqLiteDatabase = dbManager.getWritableDatabase();
    }

    // Add
    public void addTimeTable(int row, int column, int lessonID) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.TIME_ROW, row);
        contentValues.put(Contract.TIME_COLUMN, column);
        contentValues.put(Contract.TIME_LESSONID, lessonID);
        mSqLiteDatabase.insert(Contract.TABLE_TIME, null, contentValues);
    }

    // Update
    public boolean updateTimeTable(ArrayList<TimetableCell> timeTableArrayList) {
        mSqLiteDatabase.delete(Contract.TABLE_TIME, null, null);
        for (TimetableCell timeTable : timeTableArrayList) {
            int row = timeTable.getRow();
            int column = timeTable.getCol();
            int lesson_id = timeTable.getLessonID();
            addTimeTable(row, column, lesson_id);
        }
        return true;
    }

    // Delete
    public int delTimeTable(int id) {
        return mSqLiteDatabase.delete(Contract.TABLE_TIME, Contract.TIME_ID + "=?", new String[]{id + ""});
    }

    //Get TimeTable
    public Cursor getTimeTable(int i) {
        String query = "SELECT * FROM " + Contract.TABLE_TIME + " WHERE " + Contract.TIME_ID + "= ?";
        Cursor cursor = mSqLiteDatabase.rawQuery(query, new String[]{i + ""});
        return cursor;
    }

}
