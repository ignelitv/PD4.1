package com.example.pd41;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Map;

public class DeleteNoteActivity extends AppCompatActivity {

    private Spinner notesSpinner;
    private Button deleteButton;
    private Button backButton;
    private SharedPreferences sharedPreferences;
    private ArrayList<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        notesSpinner = findViewById(R.id.notesSpinner);
        deleteButton = findViewById(R.id.deleteButton);
        backButton = findViewById(R.id.backButton);
        sharedPreferences = getSharedPreferences(getString(R.string.notes_preferences), MODE_PRIVATE);
        notesList = new ArrayList<>();

        loadNotes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, notesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notesSpinner.setAdapter(adapter);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedNote = (String) notesSpinner.getSelectedItem();
                if (selectedNote != null) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(selectedNote);
                    editor.apply();

                    Toast.makeText(DeleteNoteActivity.this, getString(R.string.note_deleted), Toast.LENGTH_SHORT).show();
                    loadNotes();
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(DeleteNoteActivity.this, getString(R.string.no_note_selected), Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Grąžina į MainActivity
            }
        });
    }

    private void loadNotes() {
        notesList.clear();
        Map<String, ?> allNotes = sharedPreferences.getAll();
        notesList.addAll(allNotes.keySet());
    }
}
