package br.com.siomara.android.candidateportfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ElectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election);

        // Configures toolbar.
        getSupportActionBar().setTitle(R.string.election);

        // Initializes GUI component.
        TextView textViewInstructions = findViewById(R.id.textViewInstructions);

        // This configures text view to scroll vertically.
        textViewInstructions.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}