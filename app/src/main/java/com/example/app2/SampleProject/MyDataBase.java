package com.example.app2.SampleProject;

import android.app.DownloadManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDataBase extends SQLiteOpenHelper {

    private Context context;

    private final static int VERSION = 1;
    private final static String LOG = "LOG";
    private final static String DB_NAME = "omidDataBase";
    private final static String TABLE_NAME = "tb_row";
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_TITLE = "title";
    private final static String COLUMN_DESCRIPTION = "description";
    private final static String COLUMN_REMEMBER = "remember";
    private static final String COLUMN_CURRENT_DATE = "cdate";
    private final static String COLUMN_CURRENT_TIME = "ctime";

    private final static String QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_DESCRIPTION + " TEXT, " +
            COLUMN_REMEMBER + " INTEGER, " +
            COLUMN_CURRENT_DATE + " TEXT, " +
            COLUMN_CURRENT_TIME + " TEXT " + ")";

    public MyDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            Log.i(LOG, "QUERY IS:" + QUERY);

            db.execSQL(QUERY);

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertRecord(String title, String description, int remember, String currentDate, String currentTime) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_REMEMBER, remember);
        contentValues.put(COLUMN_CURRENT_DATE, currentDate);
        contentValues.put(COLUMN_CURRENT_TIME, currentTime);

        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getInfos() {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public void deleteRow(int id) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void updateRow(int id, String title, String description) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_DESCRIPTION, description);

        sqLiteDatabase.update(TABLE_NAME, contentValues,
                COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor getSomeInfo(int remember) {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME +
                        " WHERE " + COLUMN_REMEMBER + " = ?",
                new String[]{String.valueOf(remember)});
    }
}
