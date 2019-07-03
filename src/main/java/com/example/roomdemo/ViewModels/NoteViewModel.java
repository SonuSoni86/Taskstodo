package com.example.roomdemo.ViewModels;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdemo.Model.Note;
import com.example.roomdemo.NotesDao.NoteDAO;
import com.example.roomdemo.NotesDao.NoteRoomDatabase;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    public String TAG = this.getClass().getSimpleName();
    private NoteDAO noteDAO;
    private NoteRoomDatabase noteRoomDb;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) { super(application);

    noteRoomDb = NoteRoomDatabase.getNoteRoomDatabaseInstance(application);
    noteDAO = noteRoomDb.noteDAO();
    mAllNotes = noteDAO.getAllNotes();
    }

    private class InsertAsyncTask extends AsyncTask<Note,Void,Void>{

        NoteDAO mNoteDao;
        public InsertAsyncTask(NoteDAO mNoteDao){
            this.mNoteDao = mNoteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<Note,Void,Void>{
        NoteDAO mNoteDao;
        public UpdateAsyncTask(NoteDAO noteDAO) { this.mNoteDao = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.update(notes[0]);
            return null;
        }
    }

    public void insert(Note note){
        new InsertAsyncTask(noteDAO).execute(note);
    }
    public LiveData<List<Note>> getmAllNotes(){
        return mAllNotes;
    }

    public void update(Note note){ new UpdateAsyncTask(noteDAO).execute(note);}


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG,"View model destroyed");
    }


}
