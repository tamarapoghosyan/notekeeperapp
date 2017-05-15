package com.example.lenovo.notekeeperapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.notekeeperapp.R;
import com.example.lenovo.notekeeperapp.pojos.Note;
import com.example.lenovo.notekeeperapp.utils.note.FileNoteStorage;
import com.example.lenovo.notekeeperapp.utils.note.NoteStorage;

import java.util.Date;

import petrov.kristiyan.colorpicker.ColorPicker;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MESSAGE_TO_CREATED = "com.example.lenovo.notekeeperapp.NOTECREATED";

    EditText editTextTitle;
    EditText editTextDescription;
    TextView picker;
    CheckBox checkBoxImportant;
    Button saveBtn;
    NoteStorage noteStorage;
    Note note;

//    ColorPicker colorPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        note=new Note();

        editTextTitle= (EditText) findViewById(R.id.act_create_note_edittext_title);
        editTextDescription= (EditText) findViewById(R.id.act_create_note_edittext_description);
        picker= (TextView) findViewById(R.id.act_create_note_textview_color_picker);

        saveBtn= (Button) findViewById(R.id.act_create_note_save_botton);
        saveBtn.setOnClickListener(this);

        noteStorage=new FileNoteStorage();

        checkBoxImportant= (CheckBox) findViewById(R.id.act_create_note_checkbox_important);

        picker.setOnClickListener(this);
//        Context context=picker.getContext();
//        colorPicker = new ColorPicker(context);
//        colorPicker.show();
//        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
//            @Override
//            public void onChooseColor(int position,int color) {
//                editTextDescription.setTextColor(color);
//                editTextTitle.setTextColor(color);
//                picker.setBackgroundColor(color);
//            }
//
//            @Override
//            public void onCancel(){
//                // put code
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_create_note_save_botton:{
                note.setTitle(editTextTitle.getText().toString());
                note.setDescription(editTextDescription.getText().toString());
                note.setImpotant(checkBoxImportant.isChecked());
                note.setId(System.currentTimeMillis());
                note.setCreateDate(new Date());

                noteStorage.createNote(note, new NoteStorage.NoteListener() {
                    @Override
                    public void onNote(Note note) {

                    }
                });

                Intent intent = new Intent();
                intent.putExtra(EXTRA_MESSAGE_TO_CREATED, note);
                setResult(Activity.RESULT_OK, intent);
                //  startActivityForResult(intent,1);
                finish();


            break;
            }
            case R.id.act_create_note_textview_color_picker:{
                ColorPicker colorPicker = new ColorPicker(this);
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position,int color) {
                        picker.setBackgroundColor(color);
                        note.setColor(color);
                    }

                    @Override
                    public void onCancel(){
                        // put code
                    }
                });
                break;
            }
        }
    }
}
