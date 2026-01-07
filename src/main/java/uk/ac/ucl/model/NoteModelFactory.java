package uk.ac.ucl.model;

// This class is a factory class that creates a NoteModel object

class NoteModelFactory
{
    private static NoteModel model = new NoteModel();
    
    public static NoteModel getNoteModel()
    {
        return model;
    }
}
