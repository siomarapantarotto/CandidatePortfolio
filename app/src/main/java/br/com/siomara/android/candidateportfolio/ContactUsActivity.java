package br.com.siomara.android.candidateportfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        // Configures toolbar.
        getSupportActionBar().setTitle(R.string.contact_us);
    }
}