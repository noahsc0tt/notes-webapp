package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import java.io.IOException;


@WebServlet("/save_note")
public class SaveNoteServlet extends AbstractJSPServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
        Model model = ModelFactory.getModel();
        
        String key = request.getParameter("key");
        String name = request.getParameter("name");
        String body = request.getParameter("body");
        
        if (key.equals("")) model.addNoteData(name, body); //isEmpty better way to check? need "null"?
        else
        {
            LocalDateTime created = DateFormatter.stringToDate(key);
            model.updateNoteData(created, name, body);
        }
        
        response.sendRedirect(request.getContextPath() + "/notes_list");
        
    }
}
