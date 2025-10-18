<%--
  Created by IntelliJ IDEA.
  User: Clone
  Date: 10/6/2025
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un client</title>
</head>
<body>
<h2>Ajouter un client</h2>
<form action="<%=request.getContextPath()%>/add" method="post">
    <label>First Name</label>
    <input type="text" name="firstname"><br></br>
    <label>Last Name</label>
    <input type="text" name="lastname"><br></br>
    <label>Email</label>
    <input type="email" name="email"><br></br>
    <label>Phone</label>
    <input type="text" name="phone"><br></br>
    <button type="submit">Enregistrer</button>

</form>

<a href="${pageContext.request.contextPath}/liste">Retour a l'acceuil </a>

</body>
</html>
