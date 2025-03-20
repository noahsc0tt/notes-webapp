package uk.ac.ucl.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import uk.ac.ucl.model.NoteRecord;

// This class is responsible for searching notes based on a string query

public class NoteSearcher
{
    public static List<Map.Entry<LocalDateTime, NoteRecord>> searchNotes(String query, Iterable<Map.Entry<LocalDateTime, NoteRecord>> noteList)
    {
        List<Map.Entry<LocalDateTime, NoteRecord>> searchResults = new ArrayList<>();
        query = query.toLowerCase(); // Make the search case-insensitive
        for (Map.Entry<LocalDateTime, NoteRecord> entry : noteList)
        {
            if (entry.getValue().name().toLowerCase().contains(query) || entry.getValue().body().toLowerCase().contains(query))
            {
                searchResults.add(entry);
            }
        }
        return searchResults;
    }
}
