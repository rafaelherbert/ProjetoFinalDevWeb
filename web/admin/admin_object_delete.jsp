<%-- 
    Document   : admin_object_delete
    Created on : 23/10/2019, 00:11:49
    Author     : rafae
--%>

<%@page import="models.ProductsDao"%>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    if (request.getParameter("model").equals("product")) {
        // Lembrar de excluir a imagem associada ao produto no deleção do mesmo!
        ProductsDao products_dao = new ProductsDao();
        products_dao.deleteById(id);
    }
    response.sendRedirect(request.getParameter("referer"));
%>