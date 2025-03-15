package uk.ac.ucl.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.*;

import java.io.IOException;


@WebServlet("/note_body")
public class NoteViewServlet extends AbstractJSPServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
        String key = java.net.URLDecoder.decode(request.getParameter("key"), "UTF-8");
        request.setAttribute("key", key);
     
        Model model = ModelFactory.getModel();
        NoteRecord noteData = model.getNoteData(DateFormatter.stringToDate(key));
        request.setAttribute("note", noteData);
        
        invokeJSP("/noteView.jsp", request, response);
    }
}
