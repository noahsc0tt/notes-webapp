package uk.ac.ucl.model;

import uk.ac.ucl.util.JSONHandler;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// The Model class is the main interface for the controller to interact with the model

public class Model
{
    private static final NoteModel noteModel;
    
    // Static block to initialise the noteModel through the factory
    static { noteModel = NoteModelFactory.getNoteModel(); }
    
    public static LinkedHashMap<LocalDateTime, NoteRecord> getNoteMap() { return noteModel.getNoteMap(); }
    public static NoteRecord getNoteRecord(LocalDateTime key){ return noteModel.getNoteRecord(key); }
    
    
    public static void addNote(String name, String body)
    {
        noteModel.addNote(name, body);
        sendUpdates();
    }
    public static void updateNote(LocalDateTime key, String name, String body)
    {
        noteModel.updateNote(key, name, body);
        sendUpdates();
    }
    public static void deleteNote(LocalDateTime key)
    {
        noteModel.deleteNote(key);
        sendUpdates();
    }
    
    private static void sendUpdates()
    {
        NoteSorter.outOfDate();
        JSONHandler.writeJSON(noteModel.getNoteMap());
    }
    
    public static List<Map.Entry<LocalDateTime, NoteRecord>> searchFor(String query, Iterable<Map.Entry<LocalDateTime, NoteRecord>> notes)
    {
        return NoteSearcher.searchNotes(query, notes);
    }
}
