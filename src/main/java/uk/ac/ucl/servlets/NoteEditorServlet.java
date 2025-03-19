package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.util.DateFormatter;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.NoteRecord;

import java.io.IOException;

// This servlet is responsible for preparing the note editor.

@WebServlet("/note_editor")
public class NoteEditorServlet extends AbstractJSPServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String key = request.getParameter("key");
        NoteRecord note;
        
        if (key == null) { note = new NoteRecord("Enter note title", "Enter note body"); } // default prompt text
        else { note = Model.getNoteRecord(DateFormatter.stringToDate(key)); }
        
        request.setAttribute("name", note.name());
        request.setAttribute("body", note.body());
        invokeJSP("/noteEditor.jsp", request, response);
    }
}
