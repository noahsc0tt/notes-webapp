package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.DateFormatter;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.NoteRecord;

import java.io.IOException;



@WebServlet("/note_editor")
public class NoteEditorServlet extends AbstractJSPServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String key = request.getParameter("key");
        
        if (key != null)
        {
            NoteRecord note = Model.getNoteRecord(DateFormatter.stringToDate(key));
            request.setAttribute("key", key); //could be done inside jsp
            request.setAttribute("name", note.name());
            request.setAttribute("body", note.body());
        }
        else
        {
            request.setAttribute("name", "Enter note name");
            request.setAttribute("body", "Enter note body");
            
        }
        invokeJSP("/noteEditor.jsp", request, response);
    }
}
