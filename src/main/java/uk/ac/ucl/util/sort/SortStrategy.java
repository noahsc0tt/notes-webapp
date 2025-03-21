package uk.ac.ucl.util.sort;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import uk.ac.ucl.model.NoteRecord;

public interface SortStrategy
{
    List<Map.Entry<LocalDateTime, NoteRecord>> sort(LinkedHashMap<LocalDateTime, NoteRecord> noteMap);
    List<Map.Entry<LocalDateTime, NoteRecord>> cached();
    void markOutOfDate();
    boolean isUpToDate();
}