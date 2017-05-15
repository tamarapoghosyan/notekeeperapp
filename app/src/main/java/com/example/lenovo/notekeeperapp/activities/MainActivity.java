package com.example.lenovo.notekeeperapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.lenovo.notekeeperapp.R;
import com.example.lenovo.notekeeperapp.pojos.Note;
import com.example.lenovo.notekeeperapp.utils.note.FileNoteStorage;
import com.example.lenovo.notekeeperapp.utils.note.NoteStorage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_TEST = 1;
    public static final String EXTRA_MESSAGE_TO_CREATED = "com.example.lenovo.notekeeperapp.NOTECREATED";

    private NoteStorage noteStorage;
    private RecyclerView recyclerView;

    private NoteAdapter noteAdapter;
    private FloatingActionButton floatingActionButton;

    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton= (FloatingActionButton) findViewById(R.id.act_main_fl_act_btn);

        noteStorage=new FileNoteStorage();

        recyclerView= (RecyclerView) findViewById(R.id.act_main_rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        notes=new ArrayList<>();
        noteAdapter = new NoteAdapter();

        noteStorage.findAllNotes(new NoteStorage.NotesFoundListener() {
            @Override
            public void onNotesFound(List<Note> notes) {
                noteAdapter.setNotes(notes);
            }
        });

        recyclerView.setAdapter(noteAdapter);


        floatingActionButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_main_fl_act_btn:{
                Intent intent=new Intent(MainActivity.this,CreateNoteActivity.class);
                startActivityForResult(intent,REQUEST_CODE_TEST);
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_TEST){
            Log.d("testt", "requestCode = " + requestCode);
            Log.d("testt", "resultCode = " + resultCode);

            if (resultCode == Activity.RESULT_OK){
                Note nn= (Note) data.getSerializableExtra(EXTRA_MESSAGE_TO_CREATED);
                noteAdapter.addNote(nn);


//                String dataStr = data.getStringExtra("aaaaa");
//                textView.setText(dataStr);
            }
        }

    }

    //    public void testCreateNote(){
//        Note note=new Note("title","desc", Color.BLUE,true);
//        noteStorage.createNote(note, new NoteStorage.NoteListener() {
//            @Override
//            public void onNote(Note note) {
//
//            }
//        });
//    }
}
