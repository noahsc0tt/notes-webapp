package uk.ac.ucl.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoteSearcher
{
    public static List<Map.Entry<LocalDateTime, NoteRecord>> searchNotes(String query, Iterable<Map.Entry<LocalDateTime, NoteRecord>> noteList)
    {
        List<Map.Entry<LocalDateTime, NoteRecord>> searchResults = new ArrayList<>();
        for (Map.Entry<LocalDateTime, NoteRecord> entry : noteList)
        {
            if (entry.getValue().name().contains(query) || entry.getValue().body().contains(query))
            {
                searchResults.add(entry);
            }
        }
        return searchResults;
    }
}
