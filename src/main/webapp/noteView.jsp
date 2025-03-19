<%-- This JSP renders the note for viewing. --%>

<%@ include file="imports.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>${name}</title>
</head>
<body>

  <h1><%=request.getAttribute("name")%></h1>
  <div><%=request.getAttribute("body")%></div>

  <form action = "/note_editor" method="GET">
    <input type="hidden" name="key" value="${param.key}">
    <button type="submit">Edit Note</button>
  </form>

  <jsp:include page="deleteNoteButton.jsp" />
  <a href="notes_list">Back to Notes</a>
</body>
</html>