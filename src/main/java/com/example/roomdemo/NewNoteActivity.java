package com.example.roomdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NOTE_ADDED = "new_note";

    private TextInputEditText eNewNote;
    private Button addNoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        eNewNote = findViewById(R.id.note_content);
        addNoteContent = findViewById(R.id.add_new_note);
        addNoteContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                if(TextUtils.isEmpty(eNewNote.getText())){
                    setResult(RESULT_CANCELED,resultIntent);
                }else{
                    String note = eNewNote.getText().toString().trim();
                    resultIntent.putExtra(NOTE_ADDED,note);
                    setResult(RESULT_OK,resultIntent);
                }
                finish();
            }
        });

    }
}
