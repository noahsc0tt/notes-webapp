<%@ include file="imports.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Note Editor</title>
</head>
<body>
    <%
    Object key = request.getAttribute("key");
    String title = (key == null) ? "Add" : "Edit";
    request.setAttribute("title", title);
    %>

  <h1>${title} Note</h1>

  <form action="/save_note" method="POST">
      Note Name:
      <input type="text" name="name" size="40" value="${name}"><br>
      Note Body:
      <textarea name="body" rows="20" columns="40" style="font-family: inherit;">${body}</textarea><br>
      <input type="hidden" name="key" value="${key}"><br><br>
      <button type="submit">Save Note</button>
  </form>

  <% if (key != null) { %>
    <jsp:include page="deleteNoteButton.jsp" />
  <%}%>

  <a href="/index.html">Exit to home</a>
</body>
</html>