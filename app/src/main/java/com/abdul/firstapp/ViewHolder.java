package com.abdul.firstapp;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

// stores and recycles views as they are scrolled off screen
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView myTextView;
    private ItemClickListener mClickListener;

    ViewHolder(View itemView) {
        super(itemView);
        myTextView = itemView.findViewById(R.id.tvWord);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ItemClickListener mClickListener = null;
        if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
    }


    // convenience method for getting data at click position
    String getItem(int id) {
        //return mData.get(id);
        return "";
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

// parent activity will implement this method to respond to click events
public interface ItemClickListener {
    void onItemClick(View view, int position);
}
}