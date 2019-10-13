<%-- 
    Document   : projects_dashboard
    Created on : 26/09/2019, 19:07:16
    Author     : ramir
--%>

<%@page import="java.util.List"%>
<%@page import="models.Product"%>
<%@page import="models.ProductsDao"%>
<%@ include file="admin_header.jsp" %>
<a href="admin_product_edit.jsp">Criar produto</a>
<table>
    <thead>
        <th>#</th>
        <th>Nome</th>
        <th>Descrição</th>
        <th>Preço</th>
        <th>URL da imagem</th>
        <th>Marca</th>
        <th>Categoria</th>
        <th>Ações</th>
    </thead>
    <%
        ProductsDao products_dao = new ProductsDao();
        List<Product> products = products_dao.getAll();
        for (Product product:products){
    %>
    <tr>
        <td><%= product.getId() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getDescription() %></td>
        <td><%= product.getPrice() %></td>
        <td><%= product.getImg_url() %></td>
        <td><%= product.getBrand() %></td>
        <td><%= product.getCategory() %></td>
        <td>
            <a href="admin_product_edit.jsp?id=<%= product.getId() %>">Update<a/>
            <a href="admin_object_delete.jsp?model=product&id=<%= product.getId() %>">Delete<a/>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%@ include file="admin_footer.jsp" %>
