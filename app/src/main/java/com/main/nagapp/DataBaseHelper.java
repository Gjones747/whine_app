package com.main.nagapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TODOTABLE = "TODOTABLE";
    public static final String COLUMN_QUESTION = "QUESTION";
    public static final String COLUMN_TIME = "TIME";
    public static final String COLUMN_YES_MESSAGE = "YES_MESSAGE";
    public static final String COLUMN_NO_MESSAGE = "NO_MESSAGE";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "todoDB", null, 1);


    }

    // generates table or if one exists does nothing
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TODOTABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_QUESTION + " TEXT, " + COLUMN_TIME + " TEXT, " + COLUMN_YES_MESSAGE + " TEXT, " + COLUMN_NO_MESSAGE + " TEXT)";
        db.execSQL(createTableStatement);
    }

    // automatically modifies database (not really important for this)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean addOne(ToDoModel toDoModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_QUESTION, toDoModel.getQuestion());
        cv.put(COLUMN_NO_MESSAGE, toDoModel.getNoMessage());
        cv.put(COLUMN_YES_MESSAGE, toDoModel.getYesMessage());
        cv.put(COLUMN_TIME, toDoModel.getTime());

        long insert = db.insert(TODOTABLE, null,cv);

        if (insert == -1) {
            return false;
        }

        return true;
    }

    public HashMap<Integer, ToDoModel> showAll() {
        HashMap<Integer, ToDoModel> showAll = new HashMap<>();
        String queryString = "SELECT * FROM " + TODOTABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String question = cursor.getString(1);
                String time = cursor.getString(2);
                String yesMessage = cursor.getString(3);
                String noMessage = cursor.getString(4);

                ToDoModel todo = new ToDoModel(question, yesMessage, noMessage, time);
                showAll.put(id, todo);

            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return showAll;
    }
}
