package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.NoteRecord;
import uk.ac.ucl.util.NoteSorter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Objects;

// This servlet is responsible for preparing the note list page.

@WebServlet("/notes_list")
public class NoteListServlet extends AbstractJSPServlet
{
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException, IllegalArgumentException
  {
    String sortChoice = Objects.requireNonNullElse(request.getParameter("sort"), "mostRecent"); //default to sort by most recent
    List <Map.Entry<LocalDateTime, NoteRecord>> noteList = sortNoteList(sortChoice);
    
    String searchQuery = request.getParameter("search");
    
    if (searchQuery != null) { noteList = Model.searchFor(searchQuery, noteList); } //filter list by search query if present
    request.setAttribute("noteList", noteList);
    invokeJSP("/notesList.jsp", request, response);
  }
  
  private List<Map.Entry<LocalDateTime, NoteRecord>> sortNoteList(String sortChoice)
  {
      LinkedHashMap<LocalDateTime, NoteRecord> noteMap = Model.getNoteMap();
      return switch (sortChoice)
      {
          case "leastRecent" -> NoteSorter.leastRecent(noteMap);
          case "alphabetical" -> NoteSorter.alphabetical(noteMap);
          case "revAlphabetical" -> NoteSorter.revAlphabetical(noteMap);
          case "mostRecent" -> NoteSorter.mostRecent(noteMap);
          default -> throw new IllegalArgumentException("Invalid sort choice");
      };
  }
}
