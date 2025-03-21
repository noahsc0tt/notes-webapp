package uk.ac.ucl.util.sort;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import uk.ac.ucl.model.NoteRecord;

public class LeastRecentStrategy extends AbstractSortStrategy
{
    @Override
    // No extra sorting needed, as entrySet of LinkedHashMap is already sorted by insertion order
    public void applySort(List<Map.Entry<LocalDateTime, NoteRecord>> noteList) {}
}