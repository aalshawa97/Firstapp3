package com.abdul.firstapp.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.abdul.firstapp.database.FeedReaderContract.FeedEntry;

import com.abdul.firstapp.database.model.TodoNote;

public class NotesDao {
    SQLiteDatabase database;

    public NotesDao(Context applicationContext) {
        DbHelper dbHelper = new DbHelper(applicationContext);
        database = dbHelper.getWritableDatabase();
    }

    public void createRow(TodoNote note){
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE,note.getTitle());
        values.put(FeedEntry.COLUMN_NAME_SUBTITLE,note.getSubTitle());
        database.insert(FeedEntry.TABLE_NAME,null,values);
    }
    public String readRow() {
        Cursor cursor = database.query(FeedEntry.TABLE_NAME, null, null, null, null, null, null);
        int titleColumnIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE);
        int subTitleColIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_SUBTITLE);
        cursor.moveToLast();
        String title = cursor.getString(titleColumnIndex);
        String subtitle = cursor.getString(subTitleColIndex);
        return title + "\n" + subtitle;
    }

    public Cursor getAllRows(){
        return database.query(FeedEntry.TABLE_NAME, null, null, null, null, null, null);

    }

    public void updateRow(){}
    public void deleteRow(){}

}