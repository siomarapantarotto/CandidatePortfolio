package br.com.siomara.android.candidateportfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LawProjectsActivity extends AppCompatActivity {

    private ListView lstLawProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_projects);

        // Configures toolbar.
        getSupportActionBar().setTitle(R.string.law_projects);

        lstLawProjects = findViewById(R.id.listViewLawProjects);

        // Fills the list with countries stored on string-array of strings.xml file.
        // To fill the list with countries stored on the array above, comment out the
        // line below and uncomment the lines that define the array countries above.
        String[] candidateLawProjects = getResources().getStringArray(R.array.lawProjects);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                candidateLawProjects
        );

        lstLawProjects.setAdapter(arrayAdapter);

        lstLawProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) lstLawProjects.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}