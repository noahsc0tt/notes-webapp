package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.util.DateFormatter;
import java.io.IOException;

// This servlet is used to delete a note

@WebServlet("/delete_note")
public class DeleteNoteServlet extends AbstractJSPServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model.deleteNote(DateFormatter.stringToDate(request.getParameter("key")));
        response.sendRedirect(request.getContextPath() + "/notes_list");
    }
}
