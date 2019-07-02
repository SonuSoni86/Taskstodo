package com.example.roomdemo.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdemo.Model.Note;
import com.example.roomdemo.NotesDao.NoteDAO;
import com.example.roomdemo.NotesDao.NoteRoomDatabase;

public class EditNoteViewModel extends AndroidViewModel {
    private String TAG = this.getClass().getSimpleName();
    private NoteDAO noteDAO;
    private NoteRoomDatabase noteRoomDb;

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        Log.i(TAG,"in Edit ViewModel");
        noteRoomDb = NoteRoomDatabase.getNoteRoomDatabaseInstance(application);
        noteDAO = noteRoomDb.noteDAO();
    }

    public LiveData<Note> getNote(String noteId){
        return  noteDAO.getNote(noteId);
    }
}
