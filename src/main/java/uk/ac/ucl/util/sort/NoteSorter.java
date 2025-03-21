package uk.ac.ucl.util.sort;

import java.time.LocalDateTime;
import java.util.*;

import uk.ac.ucl.model.NoteRecord;

// This class is responsible for sorting the notes by different criteria

public class NoteSorter
{
    public enum SortType
    {
        MOST_RECENT,
        LEAST_RECENT,
        ALPHABETICAL,
        REVERSE_ALPHABETICAL
    }
    
    private static final Map<SortType, SortStrategy> strategies = new HashMap<>();
    
    static
    {
        strategies.put(SortType.MOST_RECENT, new MostRecentStrategy());
        strategies.put(SortType.LEAST_RECENT, new LeastRecentStrategy());
        strategies.put(SortType.ALPHABETICAL, new AlphabeticalStrategy());
        strategies.put(SortType.REVERSE_ALPHABETICAL, new ReverseAlphabeticalStrategy());
    }
    
    public static void outOfDate() { strategies.values().forEach(SortStrategy::markOutOfDate); }
    
    public static List<Map.Entry<LocalDateTime, NoteRecord>> sortNotes(
            LinkedHashMap<LocalDateTime, NoteRecord> noteMap, SortType sortType)
    {
        SortStrategy strategy = strategies.get(sortType);
        if (strategy.isUpToDate()) { return strategy.cached(); }
        return strategy.sort(noteMap);
    }
}