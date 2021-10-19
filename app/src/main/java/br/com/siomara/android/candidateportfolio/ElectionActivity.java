package br.com.siomara.android.candidateportfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ElectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election);

        // Configures toolbar.
        getSupportActionBar().setTitle(R.string.election);
    }
}