package com.abdul.firstapp.roomdb

import android.os.AsyncTask

class InsertNoteAsyncTask(val word: Word, val wordDao: WordDao): AsyncTask<Void,Void,Void>() {


    override fun doInBackground(vararg p0: Void?): Void? {
        wordDao.insert(word)
        return null
    }


}