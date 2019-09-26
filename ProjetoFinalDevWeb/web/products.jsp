<%-- 
    Document   : products
    Created on : 19/09/2019, 18:28:30
    Author     : ramir
--%>

<%@ include file="header.html" %>

<h1>Hello World!</h1>
<form method="POST">
    <input type="hidden" name="action" value="reate">
    <input type="text" name="name" placeholder="Nome">
    <textarea name="name" placeholder="Descrição"></textarea> 
    <input type="text" name="price" placeholder="Preço">
    <input type="file" name="img_url" placeholder="Imagem">
    <input type="text" name="brand" placeholder="Marca">
    <input type="text" name="category" placeholder="Categoria">
</form>

<%@ include file="header.html" %>
