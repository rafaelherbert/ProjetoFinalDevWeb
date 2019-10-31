<%-- 
    Document   : remove_from_cart.jsp
    Created on : 29/10/2019, 21:16:00
    Author     : rafae
--%>

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
    // Verifica de o usuário está logado
    HttpSession user_session = request.getSession();
    User logged_user = null;
    if (user_session != null) {
        logged_user = (User) user_session.getAttribute("user");
    }
    
    if (logged_user != null) {
        // Se o usuário estiver logado, recupera o carrinho e remove o produto com o product_id enviado no requisição.
        String product_id_str = request.getParameter("product_id");
        int product_id = Integer.parseInt(product_id_str);
        List<Product> cart = (ArrayList) user_session.getAttribute("shopping_cart");
        ProductsDao products_dao = new ProductsDao();
        Product product = products_dao.selectById(product_id);
        Product product_to_remove = null;

        for (Product prod:cart) {
            if(prod.getId() == product.getId()) {
                product_to_remove = prod;
            }
        }
        
        cart.remove(product_to_remove);
        user_session.setAttribute("shopping_cart", cart);
        response.sendRedirect("/ProjetoFinalDevWeb/cart.jsp");
        
    } else {
        response.sendRedirect("/ProjetoFinalDevWeb/index.jsp?login=true&alert=Acesso invalido.");
        return;
    }

%>
            