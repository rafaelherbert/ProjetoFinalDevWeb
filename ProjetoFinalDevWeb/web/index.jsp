<%-- 
    Document   : index
    Created on : 13/09/2019, 21:36:26
    Author     : rafae
--%>

<%@page import="models.User"%>
<%@page import="java.util.List"%>
<%@page import="models.UsersDao"%>

<%
    // Exemplo de uso da função getAll do UsersDao
    UsersDao users_dao = new UsersDao();
    List<User> users = users_dao.getAll();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            for(User user : users) {
        %>
                <%= user.getEmail() %>
                <br>
        <%
            }
        %>
    </body>
</html>
