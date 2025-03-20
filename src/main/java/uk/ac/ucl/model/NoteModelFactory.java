package uk.ac.ucl.model;

// This class is a factory class that creates a NoteModel object

class NoteModelFactory
{
    private static NoteModel model = null;
    
    public static NoteModel getNoteModel()
    {
        if (model == null) model = new NoteModel();
        return model;
    }
}
