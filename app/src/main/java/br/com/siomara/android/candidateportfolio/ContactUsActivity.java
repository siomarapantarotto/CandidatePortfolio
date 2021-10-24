package br.com.siomara.android.candidateportfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactUsActivity extends AppCompatActivity {

    private EditText edtCompleteName;
    private EditText edtContactEmail;
    private EditText edtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        // Configures toolbar.
        getSupportActionBar().setTitle(R.string.contact_us);

        // Initializes GUI components.
        edtCompleteName = findViewById(R.id.editTextCompleteName);
        edtContactEmail = findViewById(R.id.editTextContactEmail);
        edtMessage = findViewById(R.id.editTextMessage);
        Button btnSendMessage = findViewById(R.id.buttonSendMessage);

        // Handles button click to mail the message.
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String someoneName = edtCompleteName.getText().toString().trim();
                String someoneEmail = edtContactEmail.getText().toString().trim();
                String message = edtMessage.getText().toString().trim();
                sendPresetEmail(someoneName, someoneEmail, message);

                // If Log.d is not showing in your Logcat just replace Log.d with Log.wtf Like this:
                //Log.d("==> CANDIDATE_PORTFOLIO", "ENDING btnSendMessage.setOnClickListener - AFTER sendPresetEmail(someoneName, someoneEmail, message)");
                Log.wtf("==> CANDIDATE_PORTFOLIO","ENDING btnSendMessage.setOnClickListener - AFTER sendPresetEmail(someoneName, someoneEmail, message)");
            }
        });

    }

    /**
     * Sends email with preset values
     */
    public void sendPresetEmail(String someoneName, String someoneEmail, String message) {
        Log.d("==> CANDIDATE_PORTFOLIO", "BEGINNING sendPresetEmail(String someoneName, String someoneEmail, String message)");

        Intent email = new Intent(Intent.ACTION_SEND);

        // Sets all the emails the candidate wants the message to be sent to.
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{
                someoneEmail, // sends also to the sender
                "siomarapantarotto@gmail.com",  // 1st email
                "siomarapantarotto@hotmail.com" // 2nd email
        });

        // Sets the email subject.
        email.putExtra(Intent.EXTRA_SUBJECT, "Copy of the message mailed to the Candidate.");

        // Sets the email message.
        email.putExtra(Intent.EXTRA_TEXT, message);

        // Configures to open only email applications installed on de mobile device.
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose your favorite email application!"));

        Log.d("==> CANDIDATE_PORTFOLIO", "ENDING sendPresetEmail(String someoneName, String someoneEmail, String message)");

    }
}