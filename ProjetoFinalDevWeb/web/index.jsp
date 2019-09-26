<%-- 
    Document   : index
    Created on : 13/09/2019, 21:36:26
    Author     : rafae
--%>

<%@page import="models.User"%>
<%@page import="java.util.List"%>
<%@page import="models.UsersDao"%>
<%

// Verifica se o usuário está logado!
boolean logged_in = false;
HttpSession user_session = request.getSession();
if (user_session.getAttribute("user_email") != null)
    logged_in = true;

%>

<%@ include file="header.jsp" %>

    <% if (logged_in) {%>
    <h1>Seja bem vindo, <%= user_session.getAttribute("user_name") %>. <a href="SessionController?action=logout">Logout</a></h1>
    <% } %>

    <% if (request.getParameter("feedback") != null) { %>
    <div class="feedback"><%= request.getParameter("feedback") %></div>
    <% } %>

    <% if (request.getParameter("error") != null) { %>
    <div class="error"><%= request.getParameter("error") %></div>
    <% } %>

    <form method="POST" action="SessionController">
        <h2>Login</h2>
        <input type="hidden" name="action" value="login">
        <input type="email" name="email" placeholder="E-mail"/>
        <input type="password" name="password" placeholder="Password"/>
        <button type="submit">Logar</button>
        <p>Não tem uma conta? <a href="register.jsp">Cadastre-se</a>.</p>
    </form>
    
<%@ include file="footer.html" %>