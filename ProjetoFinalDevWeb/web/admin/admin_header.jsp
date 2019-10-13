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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
 