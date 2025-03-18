package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.NoteRecord;
import uk.ac.ucl.model.NoteSorter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Objects;


@WebServlet("/notes_list")
public class NoteListServlet extends AbstractJSPServlet
{
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException, IllegalArgumentException
  {
    String sortChoice = Objects.requireNonNullElse(request.getParameter("sort"), "mostRecent");
    request.setAttribute("noteList", sortNoteList(sortChoice));
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
