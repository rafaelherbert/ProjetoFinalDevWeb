<%-- 
    Document   : add_to_cart
    Created on : 28/10/2019, 23:12:14
    Author     : rafae
--%>

<%@page import="models.User"%>
<%@page import="models.ProductsDao"%>
<%@page import="models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
    // Verifica se o usu�rio est� logado!
    HttpSession user_session = request.getSession();
    User logged_user = null;
    if (user_session != null) {
        logged_user = (User) user_session.getAttribute("user");
    }
    
    // JSON response
    response.setContentType("application/json");

    if (logged_user != null) {
        String product_id_str = request.getParameter("product_id");
        String action = request.getParameter("action");

        if (product_id_str == null || action == null) {
            response.getWriter().write("{\"response\":\"Not enough parameters.\"}");
            return;
        }
        
        int product_id = Integer.parseInt(product_id_str);
        List<Product> cart = (ArrayList) user_session.getAttribute("shopping_cart");
        ProductsDao products_dao = new ProductsDao();
        Product product = products_dao.selectById(product_id);
        Boolean result = false;

        if (action.equals("add")) {
            cart.add(product);
            user_session.setAttribute("shopping_cart", cart);
            response.getWriter().write("{\"response\":true}");
        } else if (action.equals("remove")) {
            cart.remove(product);
            user_session.setAttribute("shopping_cart", cart);
            response.getWriter().write("{\"response\":true}");
        }  
        
    } else {
        response.getWriter().write("{\"response\":\"User not logged in\"}");
        return;
    }

%>