package com.example.roomdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.roomdemo.Model.Note;
import com.example.roomdemo.ViewModels.EditNoteViewModel;

public class EditNoteActivity extends AppCompatActivity {

    public static final String NOTE_ID ="note_id" ;
    public static final String UPDATED_NOTE ="note_text" ;
    private EditText updatedNote;
    EditNoteViewModel editNoteViewModel;
    private Bundle bundle;
    private  String noteId;
    private LiveData<Note> note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        updatedNote = findViewById(R.id.updated_data);
        editNoteViewModel = ViewModelProviders.of(this).get(EditNoteViewModel.class);
        bundle = getIntent().getExtras();
        if(bundle!=null){
            noteId = bundle.getString("note_id");
        }

        note = editNoteViewModel.getNote(noteId);
        note.observe(this, new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                updatedNote.setText(note.getNote());
            }
        });
    }

    public void updateNote(View view) {
        String updateData = updatedNote.getText().toString().trim();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(NOTE_ID,noteId);
        resultIntent.putExtra(UPDATED_NOTE,updateData);
        setResult(RESULT_OK,resultIntent);
        finish();
    }

    public void cancelUpdate(View view) {
        finish();
    }

}
