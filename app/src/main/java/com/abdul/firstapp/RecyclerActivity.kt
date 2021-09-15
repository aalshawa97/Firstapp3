package com.abdul.firstapp

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerActivity : AppCompatActivity() {

    /*
    inner class ItemClickListener {
        fun onItemClick(view: View, position: Int) {
            startActivityFromFragmentForResult<SelectExerciseActivity>(SELECT_EXERCISES)
        }
    }
    */
    lateinit var myAdapter : ViewHolder
    lateinit var wordsRecyclerView : RecyclerView
    var words = arrayOf("first", "second", "third")
    val s1 = arrayOfNulls<String>(10)
    val s2 = arrayOfNulls<String>(10)
    //val images[] = (R.id.drawable.c_plusplus, R.drawable)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        wordsRecyclerView = findViewById(R.id.wordsRecyclerview)
        wordsRecyclerView.layoutManager = LinearLayoutManager(this)
        //myAdapter.setClickListener(itemClickListener());
        wordsRecyclerView.setAdapter(myAdapter);
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

private fun RecyclerView.setAdapter(myAdapter: ViewHolder) {

}
