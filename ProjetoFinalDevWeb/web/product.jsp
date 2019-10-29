<%-- 
    Document   : product
    Created on : 28/10/2019, 22:04:15
    Author     : rafae
--%>

<%@page import="models.Product"%>
<%@page import="models.ProductsDao"%>
<%@ include file="header.jsp" %>

<%
    int product_id = Integer.parseInt(request.getParameter("id"));
    ProductsDao products_dao = new ProductsDao();
    Product product = products_dao.selectById(product_id);
%>

<div class="py-5" style="background-color:white;">
    <section class="container" style="background-color:white;">
        <div class="row">
            <div class="col-md-6">
                <img src="<%= product.getImg_url() %>" style="width:100%"/>
            </div>
            <div class="col-md-6">
                <h2><%= product.getName() %></h2>
                <h5 style="color:grey;"><%= product.getDescription() %></h4>
                <hr>
                <h2>R$ <%= product.getPrice() %></h2>

                <div class="mt-5">
                    <a href="#" id="add_to_cart" data-product_id="<%= product.getId() %>" class="btn btn-success">Adicionar ao carrinho</a>
                    <a href="#" class="btn btn-secondary">Carrinho</a>
                </div>
            </div>
        </div>
    </section>
</div>
                
<section class="container my-4">
    <div class="row">
        <div class="col-md-4">
            <div class="p-3" style="border-radius:5px;background-color:white;">
                <h5>Ver mais</h5>
                <hr>
                <!-- Display more products of the same caregory -->
            </div>
        </div>
        <div class="col-md-8">
            <div class="p-3" style="border-radius:5px;background-color:white;">
                <h5>Avaliações<h5>
                <hr>
                <!-- Display the ratings -->
            </div>
        </div>
    </div>
</section>
<%@ include file="footer.jsp" %>