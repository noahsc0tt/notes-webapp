package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Abstract class to be extended by all JSP servlets, providing a method to invoke a JSP page

public abstract class AbstractJSPServlet extends HttpServlet
{
    protected void invokeJSP(String fileName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (fileName == null || fileName.isEmpty()) throw new IllegalArgumentException("JSP file name cannot be null or empty");
        // Invoke the JSP page
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(fileName);
        dispatch.forward(request, response);
    }
}
