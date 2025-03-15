package uk.ac.ucl.model;

import java.io.IOException;

public class NoteModelFactory
{
    private static NoteModel model;
    
    public static NoteModel getNoteModel()
    {
        if (model == null) model = new NoteModel();
        return model;
    }
}
