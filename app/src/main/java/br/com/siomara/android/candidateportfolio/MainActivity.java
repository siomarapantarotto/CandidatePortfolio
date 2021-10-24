package br.com.siomara.android.candidateportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hides the bar in the main activity.
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initializes GUI components.
        ImageView imgResume = findViewById(R.id.imageViewResume);
        ImageView imgElection = findViewById(R.id.imageViewElection);
        ImageView imgLawProjects = findViewById(R.id.imageViewLawProjects);
        ImageView imgContactUs = findViewById(R.id.imageViewContactUs);

        // Handles click and starts RESUME activity.
        imgResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ResumeActivity.class));
            }
        });

        // Handles click and starts ELECTION activity.
        imgElection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ElectionActivity.class));
            }
        });

        // Handles click and starts LAW PROJECTS activity.
        imgLawProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LawProjectsActivity.class));
            }
        });

        // Handles click and starts CONTACT US activity.
        imgContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
            }
        });

    }
}