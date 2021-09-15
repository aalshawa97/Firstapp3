package com.abdul.firstapp;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class DownloadTask extends AsyncTask<String, Integer, Void>{

    public static String tag = DownloadTask.class.getSimpleName();
    ProgressBar progressBar;

    public DownloadTask(ProgressBar mProgressBar){
        progressBar = mProgressBar;
    }
    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This will normally run on a background thread. But to better
     * support testing frameworks, it is recommended that this also tolerates
     * direct execution on the foreground thread, as part of the {@link #execute} call.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param strings The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Void doInBackground(String... strings) {
        Log.i(tag, "Downloading-- " + strings[0]);
        for(int i = 1; i<=20; i++){
            //Sending a message
            publishProgress(i*5);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPreExecute() { //runs on the ui thread
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }
}
