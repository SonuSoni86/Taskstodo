package com.example.tasktodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tasktodo.Model.Task;
import com.example.tasktodo.TaskDAO.AppDatabase;
import com.example.tasktodo.View.TaskAdapter;
import com.example.tasktodo.ViewModel.NewTaskActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList = getTasks();
    TextView noTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        noTask = findViewById(R.id.enpty_task);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(taskAdapter);
    }

    private ArrayList<Task> getTasks() {
        AppDatabase database = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"TODO_DATABASE")
                .allowMainThreadQueries().build();
        Cursor cursor = database.taskDao().getAllTasks();
        ArrayList<Task>taskList1 = new ArrayList<>();


        if(cursor.getCount()==0){
            noTask.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            Task task;
            while(cursor.moveToNext()){
                task = new Task(cursor.getString(0),cursor.getString(1));
                taskList1.add(task);
            }
        }
        //taskAdapter.notifyDataSetChanged();
        return taskList1;
    }

    public void addNewTask(View view) {
        startActivity(new Intent(MainActivity.this, NewTaskActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
