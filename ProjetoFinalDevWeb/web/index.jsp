<%-- 
    Document   : index
    Created on : 13/09/2019, 21:36:26
    Author     : rafae
--%>

<%@page import="models.Product"%>
<%@page import="models.ProductsDao"%>
<%@page import="models.User"%>
<%@page import="java.util.List"%>
<%@page import="models.UsersDao"%>

<%
   
ProductsDao products_dao = new ProductsDao();
List<Product> products = products_dao.getAll();

%>

<%@ include file="header.jsp" %>

<section class="container my-5">
    

    <div class="row">
    <%
        for(Product product:products) {
    %>
    <div class="col-md-3 mx-3">
        <div class="card" style="width: 18rem;">
          <img src="<%= product.getImg_url() %>" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title"><%= product.getName() %></h5>
            <p class="card-text"><%= product.getDescription() %></p>
            <a href="product.jsp?id=<%= product.getId() %>" class="btn btn-primary">Visualizar</a>
          </div>
        </div>
    </div>
    <%
        }
    %>
    </div>

</section>

<%@ include file="footer.jsp" %>