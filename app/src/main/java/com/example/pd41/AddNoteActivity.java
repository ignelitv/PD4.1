package com.example.pd41;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText noteNameEditText, noteContentEditText;
    private Button saveButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteNameEditText = findViewById(R.id.noteNameEditText);
        noteContentEditText = findViewById(R.id.noteContentEditText);
        saveButton = findViewById(R.id.saveButton);
        sharedPreferences = getSharedPreferences(getString(R.string.notes_preferences), MODE_PRIVATE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteName = noteNameEditText.getText().toString().trim();
                String noteContent = noteContentEditText.getText().toString().trim();

                if (noteName.isEmpty() || noteContent.isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, getString(R.string.both_fields_required), Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(noteName, noteContent);
                editor.apply();

                Toast.makeText(AddNoteActivity.this, getString(R.string.note_added), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
