package com.example.lenovo.notekeeperapp.utils.note;

import com.example.lenovo.notekeeperapp.App;
import com.example.lenovo.notekeeperapp.pojos.Note;
import com.example.lenovo.notekeeperapp.utils.PreferencesHelper;
import com.example.lenovo.notekeeperapp.utils.StorageHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LENOVO on 06.05.2017.
 */
public class FileNoteStorage extends NoteStorage {
    private static final String NOTES_FILE_NAME = "FileNoteStorage.Notes";


    private NotesWrapper notesWrapper;

    public FileNoteStorage() {
        notesWrapper = (NotesWrapper) StorageHelper.deserialize(getFileName());

        if(notesWrapper == null){
            notesWrapper = new NotesWrapper();
        }
    }

    private String getFileName(){
        return String.format("%s_%s",
                NOTES_FILE_NAME,
                PreferencesHelper.getInstance(App.getInstance()).getUserId());
    }



    @Override
    public void createNote(Note note,NoteListener noteListener) {
       note.setId(System.currentTimeMillis());
        note.setCreateDate(new Date());

        notesWrapper.getNotes().add(note);
        noteListener.onNote(note);
        StorageHelper.serialize(getFileName(),notesWrapper);
        notifyNoteFound(note,noteListener);

//        if(!checkTitleAvailable(note.getTitle())){
//            notifyNoteFound(null, actionListener);
//        }
//
//        getNotes().add(note);
//        StorageHelper.serialize(NOTES_LIST_KEY, notesList);
//        notifyNoteFound(note, actionListener);
    }

    @Override
    public void findNote(long id, NoteListener noteListener) {
        for (Note note:notesWrapper.getNotes()){
            if (note.getId()==id){
                notifyNoteFound(note,noteListener);
            }


        }
        notifyNoteFound(null,noteListener);
    }

    @Override
    public void findAllNotes(NotesFoundListener notesFoundListener) {
        notifyNotesFound(notesWrapper.getNotes(),notesFoundListener);
    }

    @Override
    public void updateNote(Note note, NoteListener noteListener) {

    }

    @Override
    public void deleteNote(Note note, NoteDeleteListener noteDeleteListener) {

    }

    //ui-ner@
    //saveic heto

//    private boolean checkTitleAvailable(String title){
//        for (Note n : notesList.getNotes()){
//            if(n.getTitle().equalsIgnoreCase(title)){
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    @Override
//    protected List<Note> getNotes() {
//        return notesList.getNotes();
//    }
//
//    @Override
//    public void findNoteById(long id, NoteFoundListener actionListener) {
//        for (Note n : notesList.getNotes()){
//            if(n.getId() == id){
//                notifyNoteFound(n, actionListener);
//                return;
//            }
//        }
//
//        notifyNoteFound(null, actionListener);
//    }
//
//    @Override
//    public void checkAndGetNote(String title, Date date, NoteFoundListener actionListener) {
//        for (Note n : notesList.getNotes()){
//            if(n.getTitle().equalsIgnoreCase(title) && n.getCreateDate().equals(date)){
//                notifyNoteFound(n, actionListener);
//                return;
//            }
//        }
//
//        notifyNoteFound(null, actionListener);
//    }
//
//    @Override
//    public void findNoteByTitle(String title, NoteFoundListener actionListener) {
//        for (Note n : notesList.getNotes()){
//            if(n.getTitle().equalsIgnoreCase(title)){
//                notifyNoteFound(n, actionListener);
//                return;
//            }
//        }
//
//        notifyNoteFound(null, actionListener);
//    }

    //wrapper
    private static class NotesWrapper implements Serializable {
        static final long serialVersionUID = -1;

        private List<Note> notes;

        public NotesWrapper() {
            notes = new ArrayList<>();
        }

        public List<Note> getNotes() {
            return notes;
        }
    }
}
