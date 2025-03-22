<%-- This JSP renders the note for viewing. --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="styles.css"/>
  <title>${name}</title>
</head>
<body>

  <h1>${name}</h1>
  <div>${body}</div>


  <div class="buttons-container">

      <form action = "/note_editor" method="GET">
        <input type="hidden" name="key" value="${param.key}">
        <button type="submit">Edit Note</button>
      </form>

      <jsp:include page="deleteNoteButton.jsp" />
    </div>
  <a href="notes_list">Back to Notes</a>
</body>
</html>