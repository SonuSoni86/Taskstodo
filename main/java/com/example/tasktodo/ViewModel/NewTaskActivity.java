package com.example.tasktodo.ViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tasktodo.MainActivity;
import com.example.tasktodo.Model.Task;
import com.example.tasktodo.R;
import com.example.tasktodo.TaskDAO.AppDatabase;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class NewTaskActivity extends AppCompatActivity {

    TextInputEditText title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        title = findViewById(R.id.task_title);
        description = findViewById(R.id.task_description);
    }

    public void discardButtonClicked(View view) {
        startActivity(new Intent(NewTaskActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }

    public void saveButtonClicked(View view) {
        AppDatabase database = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"TODO_DATABASE")
                .allowMainThreadQueries().build();
        database.taskDao().insertAll(new Task(title.getText().toString(),description.getText().toString()));
        startActivity(new Intent(NewTaskActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }
}
