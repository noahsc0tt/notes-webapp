package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.*;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;


@WebServlet("/delete_note")
public class DeleteNoteServlet extends AbstractJSPServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
        Model model = ModelFactory.getModel();
        model.deleteNoteData(DateFormatter.stringToDate(request.getParameter("key")));
        
        response.sendRedirect(request.getContextPath() + "/notes_list");
        
    }
}
