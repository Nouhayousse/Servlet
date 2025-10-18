<%@ page import="java.util.List" %>
<%@ page import="entities.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Liste des clients</title>
</head>
<body>
<h1>Liste des clients</h1>

<%
    List<Client> clients = (List<Client>) request.getAttribute("clients");
    if (clients != null && !clients.isEmpty()) {
%>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Prenom</th>
        <th>Nom</th>
        <th>Email</th>
        <th>Telephone</th>
    </tr>
    <%
        for (Client c : clients) {
    %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getFirstName() %></td>
        <td><%= c.getLastName() %></td>
        <td><%= c.getEmail() %></td>
        <td><%= c.getPhone()%></td>
        <td>
            <a href="<%=request.getContextPath()%>/edit?id=<%=c.getId()%>">
                <button>Éditer</button>
            </a>
            <a href="<%=request.getContextPath()%>/delete?id=<%=c.getId()%>" onclick="return confirm('Supprimer ce client ?');">
                Supprimer
            </a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<p>Aucun client trouvé.</p>
<%
    }
%>

<a href="<%=request.getContextPath()%>/add">
    <button>Ajouter un client</button>
</a>

</body>
</html>
