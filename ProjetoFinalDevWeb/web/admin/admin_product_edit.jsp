<%-- 
    Document   : admin_product_edit
    Created on : 26/09/2019, 19:58:10
    Author     : ramir
--%>
<%@ include file="admin_header.jsp" %>

<form action="ProductController" method="POST">
    <input type="text" name="name"/>
    <textarea name="description"></textarea>
    <input type="number" name="price"/>
    <input type="file" name="img_url"/>
</form>

<%@ include file="admin_footer.jsp" %>
