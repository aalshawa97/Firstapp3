package com.abdul.firstapp.com.abdul.firstapp.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.abdul.firstapp.R
import com.abdul.firstapp.database.FeedReaderContract
import com.abdul.firstapp.database.NotesDao
import com.abdul.firstapp.database.model.TodoNote

class DataStorageActivity : AppCompatActivity() {

    lateinit var titleEditText: EditText
    lateinit var  notesEditText: EditText
    lateinit var rpCheckBox: CheckBox
    lateinit var notesDao: NotesDao
    lateinit var tvResult: TextView
    lateinit var notesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_storage)

        titleEditText = findViewById(R.id.etTitle);
        notesEditText = findViewById(R.id.etNotes)
        rpCheckBox = findViewById(R.id.checkBoxRP)
        notesDao = NotesDao(applicationContext)
        tvResult = findViewById(R.id.tvRetreived)
        notesListView = findViewById(R.id.notesListview)

        var adapter: CursorAdapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_1, //row layout
            notesDao.allRows,    //data cursor
            arrayOf(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE), //column names
            intArrayOf(android.R.id.text1)) //textview id
        notesListView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        storeData()
    }

    fun dbHandler(view: View) {
        when(view.id){
            R.id.btnCommit -> {
                commitRow()
            }
            R.id.btnRetreive -> {
                var result =  notesDao.readRow()
                tvResult.setText(result)
            }

        }

    }

    private fun commitRow() {
        var title = titleEditText.text.toString()
        var notes = notesEditText.text.toString()
        var note: TodoNote = TodoNote(title, notes);

        notesDao.createRow(note)


    }

    private fun storeData() {
        //get the text/data from those 2 edittexts
        var title = titleEditText.text.toString()
        var notes = notesEditText.text.toString()
        var isChecked: Boolean = rpCheckBox.isChecked
        //create a file
        var sharedPreferences = getSharedPreferences("preferences.xml", MODE_PRIVATE)
        //open the file in edit mode
        var editor = sharedPreferences.edit()
        //write to the file
        editor.putString("mtitle",title)
        editor.putString("mnotes",notes)
        editor.putBoolean("rpCb",isChecked)
        //save the file
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        restoreData()
    }

    private fun restoreData() {
        //open the file
        var sharedPreferences = getSharedPreferences("preferences.xml", MODE_PRIVATE)
        //read data from file
        var title = sharedPreferences.getString("mtitle","")
        var notes = sharedPreferences.getString("mnotes","")
        //write to the edittexts
        titleEditText.setText(title)
        notesEditText.setText(notes)
    }
}