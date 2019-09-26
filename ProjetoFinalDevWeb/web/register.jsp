<%-- 
    Document   : register
    Created on : 17/09/2019, 19:09:01
    Author     : rafae
--%>

<%@ include file="header.html" %>

<form method="POST" action="SessionController">
    <h2>Registro</h2>
    <input type="hidden" name="action" value="register">
    <input type="text" name="name" placeholder="Nome"/>
    <input type="email" name="email" placeholder="E-mail"/>
    <input type="password" name="password" placeholder="Password"/>
    <button type="submit">Logar</button>
    <p>Já possui uma conta? Faça o <a href="index.jsp">Login</a>.</p>
</form>

<%@ include file="footer.html" %>