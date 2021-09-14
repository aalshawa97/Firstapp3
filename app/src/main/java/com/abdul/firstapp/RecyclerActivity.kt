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
    /*
    public Holder(View itemView) {
        super(itemView);
        contacts_cardView = (CardView) itemView.findViewById(R.id.contacts_cardView);
        contacts_Name = (TextView)itemView.findViewById(R.id.contacts_Name);
        contacts_Tel = (TextView) itemView.findViewById(R.id.contacts_Tel);
        btnCancel = itemView.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {

        });

        itemView.setOnClickListener(new View.OnClickListener() {
            int pos=getAdapterPosition();
            Toast.makeText(context,"Pick contact :"+datas.get(pos).getName(), Toast.LENGTH_SHORT);
        });
    }
    */
}