package com.abdul.firstapp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.widget.TextView;

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
        if (parseBookData(jsonString)) return;

        //parseWeatherData(jsonString);

    }

    private boolean parseBookData(String jsonString) {
        try {
            JSONObject rootJsonObject = new JSONObject(jsonString);
            JSONArray itemsArray = rootJsonObject.getJSONArray("items");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject book = itemsArray.getJSONObject(i);
                String title = null;
                String authors = null;
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (title != null && authors != null) {
                    titleTv.setText(title);
                    authorTv.setText(authors);
                    return true;
                }

                titleTv.setText("No Results Found");
                authorTv.setText("");

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void parseWeatherData(String jsonString) {
        try {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonString);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            JSONArray weatherJsonArray = jsonObject.getJSONArray("weather");
          for(int i=0; i< weatherJsonArray.length();i++){
              JSONObject mJsonObject = weatherJsonArray.getJSONObject(i);
              String description = mJsonObject.getString("description");
              authorTv.setText(description);
          }

            } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }
}