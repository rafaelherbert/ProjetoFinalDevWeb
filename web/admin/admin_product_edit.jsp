<%-- 
    Document   : admin_product_edit
    Created on : 26/09/2019, 19:58:10
    Author     : ramir
--%>
<%@ include file="config.jsp" %>
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

<div class="row">

    <form action="${pageContext.request.contextPath}/ProductController" method="POST" enctype="multipart/form-data">
        <input class="form-control" type="hidden" value="<%= id %>" name="id">
        <div class="form-group">
            <label for="name">Nome:</label>
            <input class="form-control" type="text" name="name" id="name" value="<%= name %>" required />
        </div>
        <div class="form-group">
            <label for="quantity">Quantity: </label>
            <input class="form-control" type="number" name="quantity" id="quantity" value="<%= quantity %>" required />
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input class="form-control" type="text" name="price" id="price" value="<%= price %>" required />
        </div>
        <div class="form-group">
            <label for="brand">Marca:</label>
            <select class="form-control" name="brand" id="brand">
                <% for(String brand_str:BRANDS_ARRAY) { %>
                <option value="<%= brand_str %>" <% if (brand_str.equals(brand)) { %>selected<% } %>>
                    <%= brand_str %>
                </option>
                <% } %>
            </select>
        </div>
        <div class="form-group">
            <label for="category">Categoria:</label>
            <select class="form-control" name="category" id="category">
                <% for(String category_str:CATEGORIES_ARRAY) { %>
                <option value="<%= category_str %>" <% if (category_str.equals(category)) { %>selected<% } %>>
                    <%= category_str %>
                </option>
                <% } %>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Descrição:</label>
            <textarea class="form-control" name="description" id="description" required><%= description %></textarea>
        </div>
        <div class="form-group">
            <label for="img_url">Imagem:</label>
            <input class="form-control-file" type="file" name="img_url" id="img_url" value="<%= img_url %>" />
        </div>
        <input class="form-control" type="submit" name="submit" class="btn btn-primary" value="<%= action %>">
    </form>

</div>

<%@ include file="admin_footer.jsp" %>