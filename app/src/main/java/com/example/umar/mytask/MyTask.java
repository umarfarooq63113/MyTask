package com.example.umar.mytask;

import android.app.ProgressDialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by umar on 7/1/2018.
 */

public class MyTask extends AsyncTask<Void, Integer, String> {
    Context context;
    Button button;
    TextView textView;
    ProgressDialog progressDialog;

    public MyTask(Context context, Button button, TextView textView) {
        this.context = context;
        this.button = button;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Void... voids) {

        int a = 0;
        synchronized (this) {
            while (a < 15) {
                try {
                    wait(1500);
                    a++;
                    publishProgress(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Download completed";
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Downloading in progress....");
        progressDialog.setMax(15);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        textView.setText(s);
        button.setEnabled(true);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText("Downlloading in progress...");
    }
}
