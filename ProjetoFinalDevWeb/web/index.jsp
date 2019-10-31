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

/**
 * Recupera a página atual da paginação.
 * A implementação da paginação está basicamente no ProductsDao.
*/
int current_page = request.getParameter("current_page") != null ? Integer.parseInt(request.getParameter("current_page")) : 0;
String category = request.getParameter("category");
List<Product> products = null;

if (category != null && category != "") {
    products = products_dao.getPageFromCategory(current_page, category);
} else {
    products = products_dao.getPage(current_page);
}   

int total_pages = products_dao.number_of_pages;



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
    
    <div class="row d-flex mt-5">
                <nav aria-label="...">
          <ul class="pagination">
            <li class="page-item <%= current_page == 0 ? "disabled":"" %>">
              <a class="page-link" href="/ProjetoFinalDevWeb/?current_page=<%= current_page - 1 %>&category=<%= category!=null?category:"" %>" tabindex="-1" <%= current_page == 0 ? "aria-disabled='true'":"" %>>Previous</a>
            </li>
            <%
                for (int i = 0; i < total_pages; i++) {
                    %>
                        <li class="page-item <%= i == current_page?"active":"" %>">
                            <a class="page-link" href="/ProjetoFinalDevWeb/?current_page=<%= i %>&category=<%= category!=null?category:""%>">
                                <%= i + 1 %>
                            </a>
                        </li>
                    <%
                }
            %>
            <li class="page-item <%= current_page == total_pages - 1 ? "disabled":"" %>">
              <a class="page-link" href="/ProjetoFinalDevWeb/?current_page=<%= current_page + 1 %>&category=<%= category!=null?category:"" %>" <%= current_page == total_pages - 1 ? "aria-disabled='true'":""%>>Next</a>
            </li>
  </ul>
</nav>
    </div>

</section>

<%@ include file="footer.jsp" %>