package br.com.siomara.android.candidateportfolio;
/*
        Setting a vertical scrollbar to the textViewResume crashed something that made keyboard to
        cover the editTextTextEmailAddress when it gets the focus. The bug was solved by adding two
        lines, one in this file, another on the manifest, both related to the editTextTextEmailAddress
        On this file:
        android:windowSoftInputMode="adjustPan"
        On the manifest file:
        android:windowSoftInputMode="adjustPan"
        https://stackoverflow.com/questions/3295672/android-soft-keyboard-covers-edittext-field
*/

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ResumeActivity extends AppCompatActivity {

    private static final int PERMISSION_STORAGE_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        // Configures toolbar.
        getSupportActionBar().setTitle(R.string.resume);

        // Initializes components with XML.
        TextView txtResume = findViewById(R.id.textViewResume);
        Button btnDownload = findViewById(R.id.buttonDownload);

        // This configures text view resume to scroll vertically but it crashes something that
        // makes keyboard to cover the email field when it gets the focus. The solution for the
        // bug is documented on the layout file for this activity (activity_resume.xml).
        txtResume.setMovementMethod(ScrollingMovementMethod.getInstance());

        // Handles click to download a PDF file from a server.
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If OS is Marshmallow or above, handle runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        // Permission denied, request it.
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        // Shows popup for runtime permission
                        requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                    } else {
                        // Permission already granted, perform download.
                        startDownload();
                    }
                } else {
                    // System OS is less than marshmallow, perform download.
                    startDownload();
                }
            }
        });
    }

    private void startDownload() {
        // get url/text from edit text
        String url = "https://siomara.com.br/ResumePANTAROTTO.pdf";

        // Create download request.
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        // Allow types of network to download files
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download"); // Set title in download notification
        request.setDescription("Downloading file..."); // Set description in download notification

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis()); // get current timestamp as file name

        // Get download service and enqueue file.
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    // Handles permission result.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_STORAGE_CODE: {
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted from popup, perform download.
                    startDownload();
                } else {
                    // Permission granted from popup, show error message.
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
