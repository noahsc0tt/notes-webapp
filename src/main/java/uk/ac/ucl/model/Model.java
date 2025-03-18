package uk.ac.ucl.model;

import uk.ac.ucl.util.JSONHandler;
import uk.ac.ucl.util.MarkdownConverter;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class Model
{
    private static final NoteModel noteModel;
    
    static { noteModel = NoteModelFactory.getNoteModel(); }
    
    public static LinkedHashMap<LocalDateTime, NoteRecord> getNoteMap() { return noteModel.getNoteData(); }
    public static NoteRecord getNoteRecord(LocalDateTime key){ return noteModel.getNoteData(key); }
    
    
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
        JSONHandler.writeJSON(noteModel.getNoteData());
    }
}
