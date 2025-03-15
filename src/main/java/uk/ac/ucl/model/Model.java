package uk.ac.ucl.model;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class Model
{
    private final LinkedHashMap <LocalDateTime, NoteRecord> noteData;
    
    public Model()
    {
        noteData = JSONHandler.readJSON();
    }
    
    public LinkedHashMap<LocalDateTime, NoteRecord> getNoteData() {return noteData;}
    
    public NoteRecord getNoteData(LocalDateTime key){return noteData.get(key);}
    
    private void sendUpdates()
    {
        NoteSorter.outOfDate();
        JSONHandler.writeJSON(noteData);
    }
    
    public void addNoteData(String name, String body)
    {
        NoteRecord newNote = new NoteRecord(name, body);
        noteData.put(LocalDateTime.now(), newNote);
        sendUpdates();
    }
    
    public void updateNoteData(LocalDateTime key, String name, String body)
    {
        NoteRecord newNote = new NoteRecord(name, body);
        noteData.put(key, newNote);
        sendUpdates();
    }
    
    public void deleteNoteData(LocalDateTime key)
    {
        noteData.remove(key);
        JSONHandler.writeJSON(noteData);
        sendUpdates();
    }

}
