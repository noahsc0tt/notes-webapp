package uk.ac.ucl.util.sort;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import uk.ac.ucl.model.NoteRecord;

// Strategy to sort notes by most recent

class MostRecentStrategy extends AbstractSortStrategy
{
    @Override
    public void applySort(List<Map.Entry<LocalDateTime, NoteRecord>> noteList) { Collections.reverse(noteList); }
}