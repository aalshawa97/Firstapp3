package com.abdul.firstapp.roomdb;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {

    public Word(String title) {
        this.title = title;
    }

    @PrimaryKey(autoGenerate=true)
    int wid;

    @ColumnInfo
    String title;
}