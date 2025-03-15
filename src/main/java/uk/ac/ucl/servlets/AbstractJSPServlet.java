package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class AbstractJSPServlet extends HttpServlet
{
    protected void invokeJSP(String fileName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(fileName);
        dispatch.forward(request, response);
    }
}
