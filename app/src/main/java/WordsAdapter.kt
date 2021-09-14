package com.abdul.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordsAdapter(val words: Array<String>) : RecyclerView.Adapter<WordsAdapter.WordsViewHolder>() {


    class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRowPlank : TextView = itemView.findViewById(R.id.tvRow)
    }

    //david -- to buy new planks as and when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        var plankRow = LayoutInflater.from(parent.context).inflate(R.layout.row_recycler_view,parent,false);
        return WordsViewHolder(plankRow);
    }

    //abdullah handwriting-- write the content on the plank
    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.tvRowPlank.setText(words[position])
    }

    override fun getItemCount(): Int {
        return words.size
    }
}