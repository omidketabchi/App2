package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app2.R;

public class DownloaderActivity extends AppCompatActivity {

    Button btnDownload;
    EditText edtUrl;
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloader);

        setupViews();

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile();
            }
        });
    }

    private void setupViews() {

        btnDownload = (Button) findViewById(R.id.btn_downloader_download);
        edtUrl = (EditText) findViewById(R.id.edt_downloader_url);
    }

    private void downloadFile() {

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        if (edtUrl.getText().toString().equals("")) {

            Toast.makeText(this, "url not valid", Toast.LENGTH_SHORT).show();

        } else {

            String filename = edtUrl.getText().toString()
                    .substring(edtUrl.getText().toString()
                            .lastIndexOf("/"));

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(edtUrl.getText().toString()));

            request.setTitle("download file");
            request.setDescription("downloading...");
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);

            downloadManager.enqueue(request);
        }
    }
}

