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
        double total_price = 0;
        double product_total_price = 0;
        
        if (cart.size() != 0) {
            for(Product product:cart) {
                product_total_price = product.getTemp_quantity() * product.getPrice();
                total_price += product_total_price;
                %>
                <div data-class="product" class="col-md-12">
                    <h3><%= product.getName() %></h3>
                    <p style="color:grey;"><%= product.getDescription() %></p>
                    <div class="d-flex align-items-center justify-content-between">
                        <div>
                            Quantity: <span class="font-weight-bold"><%= product.getTemp_quantity() %></span>
                        </div>
                        <div>
                            Total Value: R$ <%= product_total_price %>
                        </div>
                    </div>
                    <hr>
                        <a href="/ProjetoFinalDevWeb/remove_from_cart.jsp?product_id=<%= product.getId() %>">Remover</a>
                    <hr>
                </div>
                <%
            }

            %>
            <div class="col-md-12">
                <div class="d-flex align-items-center justify-content-between">
                    <div>
                        <a href="/ProjetoFinalDevWeb/SellController?action=new" class="btn btn-success">Finalizar Compra</a>
                    </div>
                    <div class='h5'>
                        Valor total da compra: <%= total_price %>
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
