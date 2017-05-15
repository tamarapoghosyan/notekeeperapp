package com.example.lenovo.notekeeperapp.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.notekeeperapp.R;
import com.example.lenovo.notekeeperapp.pojos.Note;

import java.text.SimpleDateFormat;

/**
 * Created by LENOVO on 10.05.2017.
 */
public class NoteViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewTitle;
   private TextView textViewDescription;
    private TextView textViewDate;
private View colorView;

    private Note note;


    public NoteViewHolder(View itemView) {
        super(itemView);
        textViewTitle= (TextView) itemView.findViewById(R.id.item_note_textview_title);
        textViewDescription= (TextView) itemView.findViewById(R.id.item_note_textview_desc);
        textViewDate= (TextView) itemView.findViewById(R.id.item_note_textview_date);
colorView=itemView.findViewById(R.id.item_note_color_view);

    }

    public void bind(final Note note){
        this.note=note;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd");
        textViewTitle.setText(textViewTitle != null ? note.getTitle() : "");
        textViewDescription.setText(textViewDescription != null ? note.getDescription() : "");
        textViewDate.setText(textViewDate != null ? (simpleDateFormat.format(note.getCreateDate())).toString() : "");

colorView.setBackgroundColor(note.getColor());
    }
}
