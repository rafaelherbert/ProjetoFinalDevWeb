<%-- 
    Document   : admin_product_edit
    Created on : 26/09/2019, 19:58:10
    Author     : ramir
--%>
<%@page import="models.ProductsDao"%>
<%@page import="models.Product"%>
<%@ include file="admin_header.jsp" %>
<%
    String action = "insert";
    Product product;

    if (request.getParameter("id") != null) {
        // Updating!
        action = "update";
        ProductsDao products_dao = new ProductsDao();
        product = products_dao.selectById(Integer.parseInt(request.getParameter("id")));
    } else {
        product = new Product();
    }
    
    // Workaroung to the "null" string issue.
    String id = request.getParameter("id") == null ? "" : request.getParameter("id");
    String name = product.getName() == null ? "" : product.getName();
    String quantity =  Integer.toString(product.getQuantity()) == null ? "" : Integer.toString(product.getQuantity());
    String price =  product.getPrice() == null ? "" : Double.toString(product.getPrice());
    String brand = product.getBrand() == null ? "" : product.getBrand();
    String description = product.getDescription() == null ? "" : product.getDescription();
    String category = product.getCategory() == null ? "" : product.getCategory();
    String img_url = product.getImg_url() == null ? "" : product.getImg_url();
%>

<form action="${pageContext.request.contextPath}/ProductController" method="POST">
    <input type="hidden" value="<%= id %>" name="id">
    <div class="form-group">
        <label for="name">Nome:</label>
        <input type="text" name="name" id="name" value="<%= name %>" required/>
    </div>
    <div class="form-group">
        <label for="quantity">Price:</label>
        <input type="number" name="quantity" id="quantity" value="<%= quantity %>" required/>
    </div>
    <div class="form-group">
        <label for="price">Price:</label>
        <input type="text" name="price" id="price" value="<%= price %>" required/>
    </div>
    <div class="form-group">
        <label for="brand">Marca:</label>
        <input type="text" name="brand" id="brand" value="<%= brand %>" required/>
    </div>
    <div class="form-group">
        <label for="category">Categoria:</label>
        <input type="text" name="category" id="category" value="<%= category %>" required/>
    </div>
    <div class="form-group">
        <label for="description">Descrição:</label>
        <textarea name="description" id="description" required><%= description %></textarea>
    </div>
    <div class="form-group">
        <label for="img_url">Imagem:</label>
        <input type="file" name="img_url" id="img_url" value="<%= img_url %>"/>
    </div>
    <input type="submit" name="submit" value="<%= action %>">
</form>

<%@ include file="admin_footer.jsp" %>
