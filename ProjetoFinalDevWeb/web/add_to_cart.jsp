<%-- 
    Document   : add_to_cart
    Created on : 28/10/2019, 23:12:14
    Author     : rafae

    Essa página é utilizada para inserir produtos no carrinho.
    Essa é a única página que é acessada via requisição assíncrona no trabalho (Ajax)
    Ela retorna o json equivalente com o procedimento que foi feito no script.

--%>

<%@page import="models.User"%>
<%@page import="models.ProductsDao"%>
<%@page import="models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
    // Verifica se o usuï¿½rio estï¿½ logado!
    HttpSession user_session = request.getSession();
    User logged_user = null;
    if (user_session != null) {
        logged_user = (User) user_session.getAttribute("user");
    }
    
    // JSON response
    response.setContentType("application/json");

    if (logged_user != null) {
        String product_id_str = request.getParameter("product_id");
        String temp_quantity_str = request.getParameter("temp_quantity");

        if (product_id_str == null || temp_quantity_str == null) {
            response.getWriter().write("{\"response\":\"Not enough parameters.\"}");
            return;
        }
        
        int product_id = Integer.parseInt(product_id_str);
        int temp_quantity = Integer.parseInt(temp_quantity_str);
        List<Product> cart = (ArrayList) user_session.getAttribute("shopping_cart");
        ProductsDao products_dao = new ProductsDao();
        Product product = products_dao.selectById(product_id);
        product.setTemp_quantity(temp_quantity);

        for (Product prod:cart) {
            if(prod.getId() == product.getId()) {
                response.getWriter().write("{\"response\":\"Product already in cart.\"}");
                return;
            }
        }

        cart.add(product);
        System.out.println(cart);
        user_session.setAttribute("shopping_cart", cart);
        response.getWriter().write("{\"response\":true}");
    } else {
        response.getWriter().write("{\"response\":false}");
        return;
    }

%>