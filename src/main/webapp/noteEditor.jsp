<%@ include file="imports.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Note Editor</title>
  <script>
      function insertLink() {
          const url = prompt("Enter URL:", "https://");
          const text = prompt("Enter link text:", "Link Text");
          if (url && text) {
              const textarea = document.getElementsByName("body")[0];
              const startPos = textarea.selectionStart;
              const endPos = textarea.selectionEnd;
              const beforeText = textarea.value.substring(0, startPos);
              const afterText = textarea.value.substring(endPos, textarea.value.length);
              textarea.value = beforeText + "[" + text + "](" + url + ")" + afterText;
              textarea.focus();
          }
      }

      function insertImage() {
          const url = prompt("Enter image URL:", "https://");
          const alt = prompt("Enter image description:", "Image");
          if (url) {
              const textarea = document.getElementsByName("body")[0];
              const startPos = textarea.selectionStart;
              const endPos = textarea.selectionEnd;
              const beforeText = textarea.value.substring(0, startPos);
              const afterText = textarea.value.substring(endPos, textarea.value.length);
              textarea.value = beforeText + "![" + alt + "](" + url + ")" + afterText;
              textarea.focus();
          }
      }
    </script>
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
      <button type="button" onclick="insertLink()">Insert Link</button>
      <button type="button" onclick="insertImage()">Insert Image</button><br>
      <input type="hidden" name="key" value="${key}"><br><br>
      <button type="submit">Save Note</button>
  </form>

  <% if (key != null) { %>
    <jsp:include page="deleteNoteButton.jsp" />
  <%}%>

  <a href="/index.html">Exit to home</a>
</body>
</html>