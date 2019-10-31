<%@page import="models.Favorite"%>
<%@page import="models.Sell"%>
<%@page import="models.FavoritesDao"%>
<%@page import="models.SellsDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="models.Product"%>
<%@ include file="header.jsp" %>
<%
    if (logged_user == null) {
        response.sendRedirect(getServletContext().getContextPath() + "?login=true&alert=Disallowed");
        return;
    }
    SellsDao sells_dao = new SellsDao();
    List<Sell> sells = sells_dao.getSellsByUserId(logged_user.getId());
    FavoritesDao favorites_dao = new FavoritesDao();
    List<Favorite> favorites = favorites_dao.getFavoritesByUserId(logged_user.getId());
%>

<section class="container my-5">
    <div class="row">
        <h2 class="mb-5"> Produtos Favoritos </h2>
        
        <table class="table">
            <thead>
                <th>#</th>
                <th>Nome do Produto</th>
                <th>Ações</th>
            </thead>
            <%
            for (Favorite favorite:favorites){
            %>
            <tr>
                <td><%= favorite.getId() %></td>
                <td><%= favorite.product.getName() %></td>
                <td>
                    <a href="/ProjetoFinalDevWeb/FavoriteController?action=remove&favorite_id=<%= favorite.getId() %>&referer=${pageContext.request.requestURI}">Remover Favorito</a>
                </td>
            </tr>
            <%
            }
        %>
        </table>
        
        <h2  class="mb-5"> Compras realizadas </h2>
        
        <table class="table">
            <thead>
                <th>#</th>
                <th>Nome do Produto</th>
                <th>Quantidade</th>
                <th>Preço</th>
                <th>Preço Total</th>
                <th>Ações</th>
            </thead>
            <%
            for (Sell sell:sells){
            %>
            <tr>
                <td><%= sell.getId() %></td>
                <td><%= sell.product.getName() %></td>
                <td><%= sell.getQuantity() %></td>
                <td><%= sell.product.getPrice() %></td>
                <td><%= (double) sell.getQuantity() * sell.product.getPrice() %></td>
                <td>
                    <a href="/ProjetoFinalDevWeb/rate_product.jsp?id=<%= sell.product.getId() %>">Avaliar Produto</a>
                </td>
            </tr>
            <%
            }
        %>
        </table>
    </div>
</section>
        
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/cart.js"></script>
