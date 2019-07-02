package com.example.roomdemo.NotesDao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdemo.Model.Note;

@Database(entities = Note.class,version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDAO noteDAO();

    private static volatile NoteRoomDatabase noteRoomDatabaseInstance;

    public static NoteRoomDatabase getNoteRoomDatabaseInstance(final Context context){
        if(noteRoomDatabaseInstance==null){
            synchronized (NoteRoomDatabase.class){
                if(noteRoomDatabaseInstance==null){
                    noteRoomDatabaseInstance = Room.databaseBuilder
                            (context.getApplicationContext(),NoteRoomDatabase.class,"note_database").build();
                }
            }
        }
        return noteRoomDatabaseInstance;
    }


}
