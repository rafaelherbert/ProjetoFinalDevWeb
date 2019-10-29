<%-- 
    Document   : cart
    Created on : 29/10/2019, 00:04:58
    Author     : rafae
--%>



<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="models.Product"%>
<%@ include file="header.jsp" %>
<%
    if (logged_user == null) {
        response.sendRedirect(getServletContext().getContextPath());
        return;
    }
%>
<section class="container my-5">
    <div class="row">
        
    <%
        List<Product> cart = (ArrayList) user_session.getAttribute("shopping_cart");
        if (cart.size() != 0) {
            for(Product product:cart) {
                %>
                <div data-class="product" class="col-md-12">
                    <h3><%= product.getName() %></h3>
                    <p style="color:grey;"><%= product.getDescription() %></p>
                    <div class="d-flex align-items-center justify-content-between">
                        <div>
                            Quantity: 
                            <a href="#" data-class="plus" class="font-weight-bold h5">+</a>
                            <span data-class="product_quantity" data-price="<%= product.getPrice() %>">1</span>
                            <a href="#" data-class="minus" class="font-weight-bold h5">-</a>
                        </div>
                        <div>
                            Total Value: <span data-class="product_total_price"></span>
                        </div>
                    </div>
                    <hr>
                        <a href="#">Remover</a>
                    <hr>

                </div>
                <%
            }

            %>
            <div class="col-md-12">
                <div class="d-flex align-items-center justify-content-between">
                    <div>
                        <a href="#" class="btn btn-success">Finalizar Compra</a>
                    </div>
                    <div class='h5'>
                        Valor total da compra: <span id="cart_total_price"></span>
                    </div>
                </div>
            </div>
            <%
        } else {
            %><h1>Seu carrinho está vazio. Vá fazer compras!</h1><%
        }
    %>
    </div>
</section>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/cart.js"></script>
