package com.abdul.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BooksActivity extends AppCompatActivity {
    EditText mBookInput;
    TextView mTitleText,mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        mBookInput = findViewById(R.id.bookInput);
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);
    }


    public void searchBooksApi(View view) {
        String bookName = mBookInput.getText().toString();
        FetchBookTask fetchBookTask = new FetchBookTask(mTitleText,mAuthorText);
        fetchBookTask.execute(bookName);
    }
}