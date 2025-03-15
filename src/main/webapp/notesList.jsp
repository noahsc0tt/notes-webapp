<%@ include file="imports.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Notes List</title>
</head>
<body>
<div class="main">
  <h1>Notes List:</h1>

  <form action="/notes_list" method="GET">
    <label for="sort">Sort by: </label>
    <select name="sort" onchange="this.form.submit()">
        <%
            String selectedSort = Objects.requireNonNullElse(request.getParameter("sort"), "mostRecent");

            List<Map.Entry<String, String>> sortLabels = List.of(
                Map.entry("mostRecent", "Newest"),
                Map.entry("leastRecent", "Oldest"),
                Map.entry("alphabetical", "Alphabetical"),
                Map.entry("revAlphabetical", "Reverse Alphabetical")
            );

            for (Map.Entry<String, String> label: sortLabels) {%>
                <option value="<%= label.getKey() %>" <%= label.getKey().equals(selectedSort) ? "selected" : "" %>><%= label.getValue() %></option>
            <%}%>

    </select>
  </form>

  <%
    List<Map.Entry<LocalDateTime, NoteRecord>> noteList = (List<Map.Entry<LocalDateTime, NoteRecord>>) request.getAttribute("noteList");
    if (noteList.size() !=0)
        {
        %>
        <ul>
          <%
            for (Map.Entry<LocalDateTime, NoteRecord> entry: noteList)
            {
                NoteRecord note = entry.getValue(); //irrelevant, only used once below


          %>
                <li><a href="note_body?key=<%=java.net.URLEncoder.encode(DateFormatter.dateToString(entry.getKey()))%>"><%=note.name()%></a></li>
         <%
            }
    } else {
    %>
      <p>No notes.</p>
    <%}%>
        </ul>
  <a href="note_editor">Add note</a><br>
  <a href="/index.html">Back to Home</a>
</div>

</body>
</html>