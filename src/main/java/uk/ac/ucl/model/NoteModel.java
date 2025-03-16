package uk.ac.ucl.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;

public class NoteModel
{
    private final LinkedHashMap<LocalDateTime, NoteRecord> noteData;
    
    public NoteModel()
    {
        noteData = JSONHandler.readJSON();
    }
    
    
    public LinkedHashMap<LocalDateTime, NoteRecord> getNoteData() { return noteData; }
    
    public NoteRecord getNoteData(LocalDateTime key){ return noteData.get(key); }
    
    
    public void addNote(String name, String body)
    {
        NoteRecord newNote = new NoteRecord(name, body);
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        noteData.put(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), newNote);
        System.out.println(DateFormatter.dateToString(now));
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
