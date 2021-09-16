package com.abdul.firstapp;

import android.os.AsyncTask;
import android.widget.TextView;
import org.w3c.dom.Text;

//ip type is bookname-- string, op type is a jsonString

public class FetchBookTask extends AsyncTask<String,Void,String> {
    TextView titleTv, authorTv;

    public FetchBookTask(TextView mTitleText, TextView mAuthorText) {
        titleTv = mTitleText;
        authorTv = mAuthorText;
    }

    @Override
    protected String doInBackground(String... bookName) {
        return NetworkUtils.getBookInfo(bookName[0]);
    }

    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);
    }
}