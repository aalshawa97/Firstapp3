package com.abdul.firstapp.com.abdul.firstapp.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.abdul.firstapp.R

class DataStorageActivity : AppCompatActivity() {

    lateinit var titleEditText: EditText
    lateinit var  notesEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_storage)

        titleEditText = findViewById(R.id.etTitle);
        notesEditText = findViewById(R.id.etNotes)
    }

    override fun onPause() {
        super.onPause()
        storeData()
    }

    private fun storeData() {
        //get the text/data from those 2 edittexts
        var title = titleEditText.text.toString()
        var notes = notesEditText.text.toString()
        //create a file
        var sharedPreferences = getSharedPreferences("preferences.xml", MODE_PRIVATE)
        //open the file in edit mode
        var editor = sharedPreferences.edit()
        //write to the file
        editor.putString("mtitle",title)
        editor.putString("mnotes",notes)
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