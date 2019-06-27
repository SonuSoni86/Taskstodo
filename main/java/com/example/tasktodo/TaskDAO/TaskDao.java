package com.example.tasktodo.TaskDAO;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.tasktodo.Model.Task;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    Cursor getAllTasks();

    @Insert
    void insertAll(Task... tasks);
}
