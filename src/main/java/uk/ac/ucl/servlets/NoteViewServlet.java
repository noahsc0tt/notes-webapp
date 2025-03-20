package uk.ac.ucl.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.exceptions.MarkdownParseException;
import uk.ac.ucl.model.*;
import uk.ac.ucl.util.DateFormatter;
import uk.ac.ucl.util.MarkdownConverter;

import java.io.IOException;

// This servlet is responsible for preparing the note view page.

@WebServlet("/note_view")
public class NoteViewServlet extends AbstractJSPServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String key = java.net.URLDecoder.decode(request.getParameter("key"), "UTF-8");
        NoteRecord note = NoteRepository.getNoteRecord(DateFormatter.stringToDate(key));
        request.setAttribute("name", note.name());
        
        String body;
        try { body = MarkdownConverter.convertToHtml(note.body()); }
        catch (MarkdownParseException e) { body = note.body(); }
        request.setAttribute("body", body);
        
        invokeJSP("/noteView.jsp", request, response);
    }
}
