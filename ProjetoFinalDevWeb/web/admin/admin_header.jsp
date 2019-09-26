<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
// Verifica se o usuário está logado!
boolean logged_in = false;
HttpSession user_session = request.getSession();
if (!user_session.getAttribute("user_role").equals("admin"))
    response.sendRedirect("index.jsp?error=" + URLEncoder.encode("Cara, que vacilo.", "UTF-8"));
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link ref="stylesheet" type="text/css" href="/normalize.css">
        <title>JSP Page</title>
        <style>
            td, th {
                padding:10px;
                border:1px solid black;
                text-align:center;
            }
        </style>
    </head>
    <body>
 