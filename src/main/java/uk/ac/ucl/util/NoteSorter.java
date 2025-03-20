package uk.ac.ucl.util;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import uk.ac.ucl.model.NoteRecord;

// This class is responsible for sorting the notes by different criteria

public class NoteSorter
{
    // The four lists of notes, sorted by different criteria, maintained for caching
    private static List<Map.Entry<LocalDateTime, NoteRecord>> mostRecent;
    private static List<Map.Entry<LocalDateTime, NoteRecord>> leastRecent;
    private static List<Map.Entry<LocalDateTime, NoteRecord>> alphabetical;
    private static List<Map.Entry<LocalDateTime, NoteRecord>> revAlphabetical;
    
    private static boolean mostRecentUpToDate;
    private static boolean leastRecentUpToDate;
    private static boolean alphabeticalUpToDate;
    private static boolean revAlphabeticalUpToDate;
    
    static { outOfDate(); }
    
    public static void outOfDate()
    {
        mostRecentUpToDate = false;
        leastRecentUpToDate = false;
        alphabeticalUpToDate = false;
        revAlphabeticalUpToDate = false;
    }
    
    public static List<Map.Entry<LocalDateTime, NoteRecord>> mostRecent
            (LinkedHashMap<LocalDateTime, NoteRecord> noteMap)
    {
        if (!mostRecentUpToDate)
        {
            mostRecent = new ArrayList<>(noteMap.entrySet());
            Collections.reverse(mostRecent);
            mostRecentUpToDate = true;
        }
        return mostRecent;
    }
    
    public static List<Map.Entry<LocalDateTime, NoteRecord>> leastRecent
            (LinkedHashMap<LocalDateTime, NoteRecord> noteMap)
    {
        if (!leastRecentUpToDate)
        {
            leastRecent = new ArrayList<>(noteMap.entrySet());
            leastRecentUpToDate = true;
        }
        return leastRecent;
    }
    
    public static List<Map.Entry<LocalDateTime, NoteRecord>> alphabetical
                    (LinkedHashMap<LocalDateTime, NoteRecord> noteMap)
    {
        if (!alphabeticalUpToDate)
        {
            alphabetical = new ArrayList<>(noteMap.entrySet());
            alphabetical.sort(Comparator.comparing((Map.Entry<LocalDateTime, NoteRecord> entry) ->
                    entry.getValue().name()));
            alphabeticalUpToDate = true;
        }
        return alphabetical;
    }
    
    public static List<Map.Entry<LocalDateTime, NoteRecord>> revAlphabetical
                    (LinkedHashMap<LocalDateTime, NoteRecord> noteMap)
    {
        if (!revAlphabeticalUpToDate)
        {
            revAlphabetical = new ArrayList<>(noteMap.entrySet());
            revAlphabetical.sort(Comparator.comparing((Map.Entry<LocalDateTime, NoteRecord> entry) ->
                    entry.getValue().name()).reversed());
            revAlphabeticalUpToDate = true;
        }
        return revAlphabetical;
    }
    
}
