package com.example.pd41;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView notesListView;
    private ArrayList<String> notesList;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;

    // Pridedame mygtukų kintamuosius
    private Button addNoteButton;
    private Button deleteNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializuojame ListView ir SharedPreferences
        notesListView = findViewById(R.id.notesListView);
        sharedPreferences = getSharedPreferences(getString(R.string.notes_preferences), MODE_PRIVATE);
        notesList = new ArrayList<>();

        // Inicializuojame mygtukus
        addNoteButton = findViewById(R.id.addNoteButton);
        deleteNoteButton = findViewById(R.id.deleteNoteButton);

        // Užkrauname užrašus ir nustatome adapterį
        loadNotes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        notesListView.setAdapter(adapter);

        // Priskiriame mygtukams veiksmus
        addNoteButton.setOnClickListener(v -> {
            // Pereiname į AddNoteActivity
            startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
        });

        deleteNoteButton.setOnClickListener(v -> {
            // Pereiname į DeleteNoteActivity
            startActivity(new Intent(MainActivity.this, DeleteNoteActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Atnaujiname užrašų sąrašą, kai grįžtame į šią veiklą
        loadNotes();
        adapter.notifyDataSetChanged();
    }

    private void loadNotes() {
        notesList.clear();
        // Užkrauname užrašus iš SharedPreferences
        Map<String, ?> allNotes = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allNotes.entrySet()) {
            notesList.add(entry.getKey()); // Pridedame tik užrašų pavadinimus
        }
    }
}
