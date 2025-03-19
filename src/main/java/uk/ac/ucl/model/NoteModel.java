package uk.ac.ucl.model;

import uk.ac.ucl.util.JSONHandler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;

// This class is responsible for creating, retrieving, updating, and deleting notes

public class NoteModel
{
    private final LinkedHashMap<LocalDateTime, NoteRecord> noteData;
    
    public NoteModel()
    {
        noteData = JSONHandler.readJSON();
    }
    
    public LinkedHashMap<LocalDateTime, NoteRecord> getNoteMap() { return noteData; }
    public NoteRecord getNoteRecord(LocalDateTime key){ return noteData.get(key); }
    
    public void addNote(String name, String body)
    {
        NoteRecord newNote = new NoteRecord(name, body);
        noteData.put(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), newNote);
    }
    
    public void updateNote(LocalDateTime key, String name, String body)
    {
        NoteRecord newNote = new NoteRecord(name, body);
        noteData.put(key, newNote);
    }
    
    public void deleteNote(LocalDateTime key)
    {
        noteData.remove(key);
    }
    
}
