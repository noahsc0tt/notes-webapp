package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.NoteRepository;
import uk.ac.ucl.model.NoteRecord;
import uk.ac.ucl.util.sort.NoteSorter;

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
    
    if (searchQuery != null) noteList = NoteRepository.searchFor(searchQuery, noteList); //filter list by search query if present
    request.setAttribute("noteList", noteList);
    invokeJSP("/notesList.jsp", request, response);
  }
  
  private List<Map.Entry<LocalDateTime, NoteRecord>> sortNoteList(String sortChoice)
  {
      LinkedHashMap<LocalDateTime, NoteRecord> noteMap = NoteRepository.getNoteMap();
      NoteSorter.SortType sortType = switch (sortChoice)
      {
          case "leastRecent" -> NoteSorter.SortType.LEAST_RECENT;
          case "mostRecent" -> NoteSorter.SortType.MOST_RECENT;
          case "alphabetical" -> NoteSorter.SortType.ALPHABETICAL;
          case "revAlphabetical" -> NoteSorter.SortType.REVERSE_ALPHABETICAL;
          
          default -> throw new IllegalArgumentException("Invalid sort choice");
      };
      return NoteSorter.sortNotes(noteMap, sortType);
  }
}
