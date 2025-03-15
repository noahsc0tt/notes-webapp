<%@ include file="imports.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Note Body</title>
    </head>
<body>
  <%
    NoteRecord note = (NoteRecord) request.getAttribute("note");
  %>
  <h1><%=note.name()%></h1>
  <p><%=note.body()%></p>

  <form action = "/note_editor" method="GET">
    <input type="hidden" name="key" value="${key}">
    <button type="submit">Edit Note</button>
  </form>

  <jsp:include page="deleteNoteButton.jsp" />
  <a href="notes_list">Back to Notes</a>
</body>
</html>