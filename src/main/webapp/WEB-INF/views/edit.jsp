<%@ page import="entities.Client" %><%--
  Created by IntelliJ IDEA.
  User: Clone
  Date: 10/18/2025
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Éditer le client</title>
</head>
<body>
<h1>Éditer le client</h1>

<%
  Client c = (Client) request.getAttribute("client");
%>

<form action="<%=request.getContextPath()%>/edit" method="post">
  <input type="hidden" name="id" value="<%=c.getId()%>">
  Nom : <input type="text" name="firstname" value="<%=c.getFirstName()%>" required><br>
  Prénom : <input type="text" name="lastname" value="<%=c.getLastName()%>" required><br>
  Email : <input type="email" name="email" value="<%=c.getEmail()%>" required><br>
  Téléphone : <input type="text" name="phone" value="<%=c.getPhone()%>" required><br>
  <button type="submit">Enregistrer</button>
</form>

<br>
<a href="<%=request.getContextPath()%>/liste">Retour à la liste</a>

</body>
</html>
