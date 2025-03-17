package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.*;
import uk.ac.ucl.util.DateFormatter;

import java.time.LocalDateTime;

import java.io.IOException;


@WebServlet("/save_note")
public class SaveNoteServlet extends AbstractJSPServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String key = request.getParameter("key");
        String name = request.getParameter("name");
        String body = request.getParameter("body");
        
        if (key == null || key.isEmpty())
        {
            Model.addNote(name, body); //isEmpty better way to check? need "null"?
        }
        else
        {
            LocalDateTime created = DateFormatter.stringToDate(key);
            Model.updateNote(created, name, body);
            System.out.println("Updated note: " + name + " " + body);
        }
        
        response.sendRedirect(request.getContextPath() + "/notes_list");
        
    }
}
