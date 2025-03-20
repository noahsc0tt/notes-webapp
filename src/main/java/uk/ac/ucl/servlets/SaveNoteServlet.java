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
import java.io.IOException;

// This servlet is responsible for saving a note.

@WebServlet("/save_note")
@MultipartConfig( // Configure the servlet to handle file uploads
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10,  // 10MB
        maxRequestSize = 1024 * 1024 * 15 // 15MB
)
public class SaveNoteServlet extends AbstractJSPServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("key");
        String name = request.getParameter("name");
        String body = request.getParameter("body");
        
        processImageUpload(request);
        
        saveNote(key, name, body);
        
        response.sendRedirect(request.getContextPath() + "/notes_list");
    }
    
    // Calls other methods to handle uploading the image file
    private void processImageUpload(HttpServletRequest request) throws ServletException, IOException {
        Part filePart = request.getPart("imageFile");
        if (!isValidImageUpload(filePart)) {
            return;
        }
        
        String fileName = getFileName(filePart);
        File imgDir = createImageDirectory(request);
        saveUploadedFile(filePart, imgDir, fileName);
    }
    
    private boolean isValidImageUpload(Part filePart) {
        return filePart != null && filePart.getSize() > 0;
    }
    
    private String getFileName(Part filePart) {
        return Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
    }
    
    private File createImageDirectory(HttpServletRequest request) {
        String appPath = request.getServletContext().getRealPath("");
        File imgDir = new File(appPath, "img");
        if (!imgDir.exists()) {
            imgDir.mkdir();
        }
        return imgDir;
    }
    
    private void saveUploadedFile(Part filePart, File imgDir, String fileName) throws IOException {
        try (InputStream fileContent = filePart.getInputStream()) {
            Path targetPath = Paths.get(imgDir.getAbsolutePath(), fileName);
            Files.copy(fileContent, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
    
    private void saveNote(String key, String name, String body) {
        if (key == null || key.isEmpty()) {
            NoteRepository.addNote(name, body);
        } else {
            NoteRepository.updateNote(DateFormatter.stringToDate(key), name, body);
        }
    }
}