package br.com.siomara.android.candidateportfolio;

/**
 * Initially, this resume activity was going to have a field for the user to enter his or her email
 * and by pressing the download button the app would send the resume displayed on the screen to the
 * informed email and also download a PDF file from a URL. This approach presented a bug that was
 * fixed. This approach was changed but the solution was documented as a reminder for future apps.
 *
 * Solution: Setting a vertical scrollbar to the textViewResume component crashed something that
 * made the keyboard to cover the editTextTextEmailAddress when it gets the focus. The bug was
 * solved by adding two lines, one in the xml file, another on the manifest, both related to the
 * editTextTextEmailAddress.
 *
 * On the xml file: android:windowSoftInputMode="adjustPan"
 * On the manifest file: android:windowSoftInputMode="adjustPan"
 *
 * @see https://stackoverflow.com/questions/3295672/android-soft-keyboard-covers-edittext-field
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

    // Permission for file download manager stores file.
    private static final int PERMISSION_STORAGE_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        // Configures toolbar title.
        getSupportActionBar().setTitle(R.string.resume);

        // Initializes components with XML.
        Button btnDownload = findViewById(R.id.buttonDownload);

        // Scrollbar to work properly is set programmatically.
        TextView txtResume = findViewById(R.id.textViewResume);
        txtResume.setMovementMethod(ScrollingMovementMethod.getInstance());

        // Handles click to download a PDF file from a server.
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If OS is Marshmallow or above, handles runtime permission.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        // Permission denied, request it.
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        // Shows popup for runtime permission.
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
        // Sets the url for the file to be downloaded.
        String url = "https://siomara.com.br/ResumePANTAROTTO.pdf";

        // Creates download request.
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        // Allows types of network to download files.
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Candidate Resume"); // Sets title in download notification.
        request.setDescription("Downloading candidate resume..."); // Sets description in download notification.

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis()); // get current timestamp as file name

        // Gets download service and enqueue file.
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
