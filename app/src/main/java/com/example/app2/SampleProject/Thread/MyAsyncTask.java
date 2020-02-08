package com.example.app2.SampleProject.Thread;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

// first Void, is input for doInBackground.
// second Void, is input for onProgressUpdate.
// third Void, is input for onPostExecute and output for doInBackground.
public class MyAsyncTask extends android.os.AsyncTask<Void, Void, String> {

    TextView txtDone;
    FrameLayout frameLayout;

    public MyAsyncTask(FrameLayout frameLayout, TextView txtDone) {
        this.txtDone = txtDone;
        this.frameLayout = frameLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        frameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... voids) {

        for (int i = 0; i < 200000; i++) {
            String str = new String();
            Log.i("LOG", "onCreate: " + i);

            if (i == 150000) {
                return "done";
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);

        frameLayout.setVisibility(View.GONE);
        txtDone.setText(string);
    }
}
