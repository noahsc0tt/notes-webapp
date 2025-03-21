package uk.ac.ucl.util.sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import uk.ac.ucl.model.NoteRecord;

abstract class AbstractSortStrategy implements SortStrategy
{
    private boolean upToDate = false;
    private List<Map.Entry<LocalDateTime, NoteRecord>> cache = null;
    
    @Override
    public List<Map.Entry<LocalDateTime, NoteRecord>> sort(LinkedHashMap<LocalDateTime, NoteRecord> noteMap)
    {
        List<Map.Entry<LocalDateTime, NoteRecord>> noteList = new ArrayList<>(noteMap.entrySet());
        applySort(noteList);
        cache = noteList;
        upToDate = true;
        return noteList;
    }
    
    protected abstract void applySort(List<Map.Entry<LocalDateTime, NoteRecord>> noteList);
    
    @Override
    public List<Map.Entry<LocalDateTime, NoteRecord>> cached() { return cache; }
    
    @Override
    public void markOutOfDate() { upToDate = false; }
    
    @Override
    public boolean isUpToDate() { return upToDate; }
}