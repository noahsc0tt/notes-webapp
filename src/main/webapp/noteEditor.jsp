<%-- This JSP file is used to render the note editor page --%>

<%@ include file="imports.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="styles.css"/>
  <title>Note Editor</title>
  <script src="javascript/noteEditor.js"></script>
</head>
<body>
    <%
    Object key = request.getAttribute("key");
    String title = (key == null) ? "Add" : "Edit";
    request.setAttribute("title", title);
    %>

  <h1>${title} Note</h1>

   <%-- The form is used to submit the note name and body to the server --%>
  <form class="note-editor-form" action="/save_note" method="POST" enctype="multipart/form-data">
      <label for="name">Note Title:</label>
      <input type="text" id="name" name="name" size="40" value="${name}"><br>
      <label for="body">Note body:</label>
      <textarea id="body" name="body" rows="20" columns="40" style="font-family: inherit;">${body}</textarea><br>

      <button type="button" onclick="insertLink()">Insert Link</button>
      <button type="button" onclick="insertImage()">Insert Image</button>
      <input type="file" id="imageUpload" name="imageFile" accept="image/*" style="display:none"><br>
      <input type="hidden" name="key" value="${param.key}"><br><br>
      <button type="submit">Save Note</button>
  </form>

  <% if (key != null) { %> <jsp:include page="deleteNoteButton.jsp" /> <%}%>

  <br><a href="/index.html">Exit to home</a>
</body>
</html>