<%-- 
    Document   : product
    Created on : 28/10/2019, 22:04:15
    Author     : rafae
--%>
<%@page import="models.Rating"%>
<%@page import="models.RatingsDao"%>
<%@page import="java.util.List"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.checked {
  color: orange;
}
</style>

<%@page import="java.sql.SQLException"%>
<%@page import="models.Product"%>
<%@page import="models.ProductsDao"%>
<%@ include file="header.jsp" %>

<%
    /**
     * Recuperando os dados do produto para serem exibidos na tela.
     */
    int product_id = Integer.parseInt(request.getParameter("id"));
    ProductsDao products_dao = new ProductsDao();
    Product product = products_dao.selectById(product_id);
    List<Product> products = products_dao.getProductsByCategory(product.getCategory());
    RatingsDao ratings_dao = new RatingsDao();
    List<Rating> ratings = ratings_dao.getRatingsByProductId(product.getId());
    int product_rating = products_dao.getAverageRating(product);
    int is_favorite = -1;
    if (logged_user != null)
        is_favorite = users_dao.isFavorite(logged_user, product);
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
                
                <div id="stars_group">
                    <span class="fa fa-star" data-star_value="1"></span>
                    <span class="fa fa-star" data-star_value="2"></span>
                    <span class="fa fa-star" data-star_value="3"></span>
                    <span class="fa fa-star" data-star_value="4"></span>
                    <span class="fa fa-star" data-star_value="5"></span>
                    <span id="rating_value"><%= product_rating %></span> / 5
                </div>
                <hr>
                <h2>R$ <%= product.getPrice() %></h2>
                <h6>
                    Quantidade: <input type="number" id="temp_quantity" class="form-control" value="1" style="display:inline-block;width:100px;" min="1" max="100">
                </h6>
                <div class="mt-5">
                    <a href="#" id="add_to_cart" data-product_id="<%= product.getId() %>" class="btn btn-success">Adicionar ao carrinho</a>
                    <a href="/ProjetoFinalDevWeb/cart.jsp" class="btn btn-primary">Carrinho</a>
                    <% if (is_favorite == -1) { %>
                    <a href="/ProjetoFinalDevWeb/FavoriteController?action=add&product_id=<%= product.getId() %>&referer=${pageContext.request.requestURI}?id=<%= product.getId()%>"
                       class="btn btn-danger">
                        Favoritar
                    </a>
                    <% } else { %>
                    <a href="/ProjetoFinalDevWeb/FavoriteController?action=remove&favorite_id=<%= is_favorite %>&referer=${pageContext.request.requestURI}?id=<%= product.getId()%>"
                       class="btn btn-danger">
                        Desfavoritar
                    </a>
                    <% } %>
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
                <% for (Product product2:products) { %>
                <div class="text-center">
                    <h6><%= product2.getName() %></h6>
                </div>
                <div class="d-flex justify-content-center">
                    <a href="/ProjetoFinalDevWeb/product.jsp?id=<%= product2.getId() %>">
                        <img class="img-fluid" src="<%= product2.getImg_url() %>">
                    </a>
                </div>
                <hr>
                <% } %>
            </div>
        </div>
        <div class="col-md-8">
            <div class="p-3" style="border-radius:5px;background-color:white;">
                <h5>Avaliações<h5>
                <hr>
                <!-- Display the ratings -->
                <% for (Rating rating:ratings) { %>
                <div class="d-flex justify-content-between">
                    <div>
                        <% for (int i = 0; i < rating.getRating() ; i++) { %>
                              <span class="fa fa-star checked"></span>
                        <% } %>
                    </div>
                    <div>
                        <h5><%= rating.user.getName() %></h5>
                    </div>
                </div>
                <p style="color:grey;"><%= rating.getComment() %></p>
                <hr>
                <% } %>
            </div>
        </div>
    </div>
</section>
<%@ include file="footer.jsp" %>