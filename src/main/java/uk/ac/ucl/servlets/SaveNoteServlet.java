package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uk.ac.ucl.model.*;
import uk.ac.ucl.util.DateFormatter;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.io.IOException;


@WebServlet("/save_note")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10,  // 10MB
        maxRequestSize = 1024 * 1024 * 15 // 15MB
)
public class SaveNoteServlet extends AbstractJSPServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Collection<Part> parts = request.getParts();
        
        String key = request.getParameter("key");
        String name = request.getParameter("name");
        String body = request.getParameter("body");
        
        
        Part filePart = request.getPart("imageFile");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            
            // Create img directory if it doesn't exist
            String appPath = request.getServletContext().getRealPath("");
            File imgDir = new File(appPath, "img");
            if (!imgDir.exists()) {
                imgDir.mkdir();
            }
            
            // Save the file
            try (InputStream fileContent = filePart.getInputStream()) {
                Path targetPath = Paths.get(imgDir.getAbsolutePath(), fileName);
                Files.copy(fileContent, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        
        if (key == null || key.isEmpty())
        {
            Model.addNote(name, body); //isEmpty better way to check? need "null"?
        }
        else
        {
            Model.updateNote(DateFormatter.stringToDate(key), name, body);
        }
        
        response.sendRedirect(request.getContextPath() + "/notes_list");
        
    }
}
