package br.com.siomara.android.candidateportfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        // Configures toolbar.
        getSupportActionBar().setTitle(R.string.resume);

        TextView txtResume =  findViewById(R.id.textViewResume);
        EditText edtEmail = findViewById(R.id.editTextTextEmailAddress);
        Button btnDownload = findViewById(R.id.buttonDownload);

        // This configures text view resume to scroll vertically but it crashes something that
        // makes keyboard to cover the email field when it gets the focus. The solution for the
        // bug is documented on the layout file for this activity (activity_resume.xml).
        txtResume.setMovementMethod(ScrollingMovementMethod.getInstance());

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Click on download button!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
