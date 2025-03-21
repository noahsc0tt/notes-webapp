package uk.ac.ucl.model;

import uk.ac.ucl.exceptions.JSONFileNotFoundException;
import uk.ac.ucl.exceptions.JSONWriteException;
import uk.ac.ucl.util.JSONHandler;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import uk.ac.ucl.util.NoteSearcher;
import uk.ac.ucl.util.sort.NoteSorter;

// This class is the main interface for the controller to interact with the note data through the repository pattern

public class NoteRepository
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
    
    public static List<Map.Entry<LocalDateTime, NoteRecord>> searchFor(String query, Iterable<Map.Entry<LocalDateTime, NoteRecord>> notes)
    {
        return NoteSearcher.searchNotes(query, notes);
    }
    
    private static void sendUpdates()
    {
        NoteSorter.outOfDate();
        try { JSONHandler.writeJSON(noteModel.getNoteMap()); }
        catch (JSONFileNotFoundException | JSONWriteException e) { e.printStackTrace(); }
    }
}
