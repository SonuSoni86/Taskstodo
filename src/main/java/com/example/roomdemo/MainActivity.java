package com.example.roomdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.roomdemo.Model.Note;
import com.example.roomdemo.View.NoteListAdapter;
import com.example.roomdemo.ViewModels.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_NOTE_ACTIVITY_REQUEST_CODE =1 ;
    public  static final int UPDATE_NOTE_ACTIVITY_REQUEST_CODE =1 ;
    private String TAG = this.getClass().getSimpleName();
    private NoteViewModel noteViewModel;
    FloatingActionButton addButton;
    RecyclerView listOfNotes;
    NoteListAdapter noteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.add_button);
        listOfNotes = findViewById(R.id.note_list);
        noteListAdapter = new NoteListAdapter(this);

        listOfNotes.setAdapter(noteListAdapter);
        listOfNotes.setLayoutManager(new LinearLayoutManager(this));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NewNoteActivity.class);
                startActivityForResult(intent,NEW_NOTE_ACTIVITY_REQUEST_CODE);
            }
        });

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        noteViewModel.getmAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteListAdapter.setNotes(notes);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK){

            final String note_id = UUID.randomUUID().toString();
            Note note = new Note(note_id, data.getStringExtra(NewNoteActivity.NOTE_ADDED));
            Toast.makeText(getApplicationContext(),R.string.saved,Toast.LENGTH_SHORT).show();

            noteViewModel.insert(note);

        }else if(requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            //code to update the note
            Note note = new Note(data.getStringExtra(EditNoteActivity.NOTE_ID),data.getStringExtra(EditNoteActivity.UPDATED_NOTE));
            noteViewModel.update(note);
            Toast.makeText(getApplicationContext(),"Note Updated",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),R.string.not_saved,Toast.LENGTH_SHORT).show();
        }
    }
}
