package com.example.lenovo.notekeeperapp.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.notekeeperapp.R;
import com.example.lenovo.notekeeperapp.pojos.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 10.05.2017.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private List<Note> notes;

    public NoteAdapter() {
        notes = new ArrayList<>();
    }

    public void setNotes(List<Note> notes) {
        this.notes.addAll(notes);
        notifyDataSetChanged();
    }

    public void addNote(Note note){
        notes.add(note);
        notifyDataSetChanged();

    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);

    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
