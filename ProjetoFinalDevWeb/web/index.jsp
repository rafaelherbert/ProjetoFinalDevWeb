<%-- 
    Document   : index
    Created on : 13/09/2019, 21:36:26
    Author     : rafae
--%>

<%

// Verifica se o usuário está logado!
boolean logged_in = false;
HttpSession user_session = request.getSession();
System.out.println(user_session.getAttribute("user_name"));
if (user_session.getAttribute("user_email") != null)
    logged_in = true;

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <% if (logged_in) {%>
        <h1>Seja bem vindo, <%= user_session.getAttribute("user_name") %>. <a href="UserController?action=logout">Logout</a></h1>
        <% } %>
        
        <% if (request.getParameter("feedback") != null) { %>
        <div class="feedback"><%= request.getParameter("feedback") %></div>
        <% } %>
        
        <% if (request.getParameter("error") != null) { %>
        <div class="error"><%= request.getParameter("error") %></div>
        <% } %>
        
        <form method="POST" action="UserController">
            <h2>Login</h2>
            <input type="hidden" name="action" value="login">
            <input type="email" name="email" placeholder="E-mail"/>
            <input type="password" name="password" placeholder="Password"/>
            <button type="submit">Logar</button>
            <p>Não tem uma conta? <a href="register.jsp">Cadastre-se</a>.</p>
        </form>
    </body>
</html>
