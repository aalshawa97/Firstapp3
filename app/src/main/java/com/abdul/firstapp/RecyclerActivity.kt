package com.abdul.firstapp

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerActivity : AppCompatActivity() {
    lateinit var wordsRecyclerView : RecyclerView
    var words = arrayOf("first", "second", "third")

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        wordsRecyclerView = findViewById(R.id.wordsRecyclerview)
        wordsRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}