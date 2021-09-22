package com.abdul.firstapp.roomdb

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface WordDao {
    @Insert
    fun insert(word: Word?)

    @Query("SELECT * FROM Word WHERE title LIKE :word ")
    fun findNote(word: Word?): List<Word?>?

    @get:Query("SELECT * from Word ORDER BY title ASC")
    val allNotes: List<Word?>?

    @Update
    fun update(word: Word?)

    @Query("DELETE FROM Word")
    fun deleteAllNotes()
}