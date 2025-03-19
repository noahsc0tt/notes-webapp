<%-- This file is used to create a reusable button that deletes a note --%>

<link rel="stylesheet" type="text/css" href="styles.css"/>

<form action="/delete_note" method="POST">
  <input type="hidden" name="key" value="<%=request.getAttribute("key")%>">
  <button type="submit">Delete Note</button>
</form>
