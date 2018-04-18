package group6.fga.fsoft.com.mockproject_group6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import group6.fga.fsoft.com.mockproject_group6.model.Contract;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    String CREATE_TIMETABLE;
    String CREATE_LESSONTABLE;
    String DROP_LESSONTABLE;
    String DROP_TIMETABLE;
    Context context;


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DBManager", "DBManager: ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        CREATE_LESSONTABLE = "CREATE TABLE "
                + Contract.TABLE_LESSON + "("
                + Contract.LESSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.LESSON_NAME + "TEXT NOT NULL" + ")";
        sqLiteDatabase.execSQL(CREATE_LESSONTABLE);
        CREATE_TIMETABLE = "CREATE TABLE "
                + Contract.TABLE_TIME + "("
                + Contract.TIME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.TIME_ROW + "INTEGER NOT NULL, "
                + Contract.TIME_COLUMN + "INTEGER NOT NULL, "
                + Contract.TIME_LESSONID + "INTEGER NOT NULL" + ")";
        sqLiteDatabase.execSQL(CREATE_TIMETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        DROP_LESSONTABLE = "DROP TABLE IF EXISTS " + Contract.TABLE_LESSON;
        sqLiteDatabase.execSQL(DROP_LESSONTABLE);
        onCreate(sqLiteDatabase);
        DROP_TIMETABLE = "DROP TABLE IF EXISTS " + Contract.TABLE_TIME;
        sqLiteDatabase.execSQL(DROP_TIMETABLE);
        onCreate(sqLiteDatabase);
    }
}
