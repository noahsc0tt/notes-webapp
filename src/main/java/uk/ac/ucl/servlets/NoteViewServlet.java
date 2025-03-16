package uk.ac.ucl.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.*;

import java.io.IOException;


@WebServlet("/note_view")
public class NoteViewServlet extends AbstractJSPServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
        String key = java.net.URLDecoder.decode(request.getParameter("key"), "UTF-8");
        request.setAttribute("key", key);
        
        System.out.println("Looking for key: " + key);
        System.out.println("Available keys in map: " + Model.getNoteMap().keySet());
        
        
        request.setAttribute("note", Model.getNoteRecord(DateFormatter.stringToDate(key)));
        
        System.out.println("Retrieved note: " + Model.getNoteRecord(DateFormatter.stringToDate(key)));
        
        
        System.out.println(key);
        System.out.println(Model.getNoteRecord(DateFormatter.stringToDate(key)));
   
        invokeJSP("/noteView.jsp", request, response);
    }
}
