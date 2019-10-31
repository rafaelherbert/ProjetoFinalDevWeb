<%-- 
    Document   : product
    Created on : 28/10/2019, 22:04:15
    Author     : rafae
--%>
<%@page import="models.Rating"%>
<%@page import="models.RatingsDao"%>
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
    if (logged_user == null) {
        response.sendRedirect(getServletContext().getContextPath() + "?login=true&alert=Disallowed");
        return;
    }

    int product_id = Integer.parseInt(request.getParameter("id"));
    ProductsDao products_dao = new ProductsDao();
    Product product = products_dao.selectById(product_id);    
    
    String action = "insert";
    Rating rating = null;
    String rating_rating = "?";
    String rating_comment = "";
    
    int rating_id = -1;
    rating_id = users_dao.isRated(logged_user, product);
    System.out.println("rating_id: " + rating_id);
    
    if (rating_id != -1) {
        // Updating!
        action = "update";
        RatingsDao ratings_dao = new RatingsDao();
        rating = ratings_dao.selectById(rating_id);
        if (rating != null) {
            rating_rating = Integer.toString(rating.getRating());
            rating_comment = rating.getComment();
        }
    }
    
    

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
                <form action="${pageContext.request.contextPath}/RatingController" method="POST">
                <h2> Avaliar
                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
                    <input type="hidden" name="rating_id" value="<%= rating_id %>">
                    <input type="hidden" name="rating" id="rating_input">
                    <div id="stars_group">
                        <span class="fa fa-star" data-star_value="1"></span>
                        <span class="fa fa-star" data-star_value="2"></span>
                        <span class="fa fa-star" data-star_value="3"></span>
                        <span class="fa fa-star" data-star_value="4"></span>
                        <span class="fa fa-star" data-star_value="5"></span>
                        <span id="rating_value"><%= rating_rating %></span> / 5
                    </div>
                </h2>
                <textarea class="form-control mt-2" name="comment"><%= rating_comment %></textarea>
                <input type="submit" class="btn btn-primary mt-3" name="submit" value="<%= action %>"/>
            </div>
                
        </div>
    </section>
</div>
                
<%@ include file="footer.jsp" %>