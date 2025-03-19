// This JSP file is used to display the list of notes, with searching and sorting options.

<%@ include file="imports.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Notes List</title>
</head>
<body>
<div class="main">
  <h1>Notes List:</h1>


  // This form is used to sort the notes by different criteria.
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

            <% if (request.getParameter("search") != null && !request.getParameter("search").isEmpty()) { %>
                <input type="hidden" name="search" value="${param.search}">
            <% } %>
    </select>
  </form>

    // This form is used to search for notes
  <form action="/notes_list" method="GET">
    <label for="search">Search: </label>
    <input type="text" name="search" value=${param.search}>
    <input type="submit" value="Search">

   <% if (request.getParameter("sort") != null && !request.getParameter("sort").isEmpty()) { %>
       <input type="hidden" name="sort" value="${param.sort}">
     <% } %>
  </form>

  // This section displays the list of notes with links to view them
  <%
    List<Map.Entry<LocalDateTime, NoteRecord>> noteList = (List<Map.Entry<LocalDateTime, NoteRecord>>) request.getAttribute("noteList");
    if (noteList.size() !=0) {
        %>
        <ul>
          <%
            for (Map.Entry<LocalDateTime, NoteRecord> entry: noteList)
            {%>
                <li><a href="note_view?key=<%=java.net.URLEncoder.encode(DateFormatter.dateToString(entry.getKey()))%>"><%=entry.getValue().name()%></a></li>
         <%}
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