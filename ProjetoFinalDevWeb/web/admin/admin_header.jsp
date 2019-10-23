<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
// Verifica se o usuário está logado!
boolean logged_in = false;
HttpSession user_session = request.getSession();
if (!user_session.getAttribute("user_role").equals("admin"))
    response.sendRedirect("../index.jsp?error=" + URLEncoder.encode("Cara, que vacilo.", "UTF-8"));
%>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/main.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/main.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin.css" />

</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="admin_products_dashboard.jsp">Produtos<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Avaliações</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Vendas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Usuários</a>
                </li>
            </ul>
        </div>
        <a class="nav-link" href="../SessionController?action=logout">Logout</a>
    </nav>
    
    <section class="container my-5">
