package com.example.roomdemo.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdemo.EditNoteActivity;
import com.example.roomdemo.MainActivity;
import com.example.roomdemo.Model.Note;
import com.example.roomdemo.R;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<Note> mNotes;


    public NoteListAdapter(Context context) {
        layoutInflater  = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(mNotes!=null){
            Note note = mNotes.get(position);
            holder.setData(note.getNote(),position);
            holder.setListeners();
        }else{
            holder.noteData.setText(R.string.no_note);
        }

    }

    @Override
    public int getItemCount() {
        if(mNotes!=null){
            return mNotes.size();
        }else return 0;
    }

    public void setNotes(List<Note> notes) {
        mNotes= notes;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView noteData;
        private int position;
        private ImageView imgDelete, imgEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            noteData = (TextView)itemView.findViewById(R.id.note_data);
            imgDelete = (ImageView)itemView.findViewById(R.id.img_delete);
            imgEdit = (ImageView)itemView.findViewById(R.id.img_edit);
        }

        public void setData(String note, int position) {

            noteData.setText(note);
            this.position=position;
        }

        public void setListeners(){
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, EditNoteActivity.class);
                    intent.putExtra("note_id",mNotes.get(position).getId());
                    ((Activity)mContext).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
