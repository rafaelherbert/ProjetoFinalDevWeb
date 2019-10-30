<%@page import="models.UsersDao"%>
<%@page import="models.User"%>
<%
    // Verifica se o usuï¿½rio estï¿½ logado!
    HttpSession user_session = request.getSession();
    User logged_user = null;
    if (user_session != null) {
        logged_user = (User) user_session.getAttribute("user");
    }
%>

<%@ include file="admin/config.jsp" %>


<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/main.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/client.css" />
    <title>JSP Page</title>
</head>

<body>
    
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">Projeto Dev Web</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Categorias
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <%
                            for(String category:CATEGORIES_ARRAY) {
                        %>
                               <a class="dropdown-item" href="${pageContext.request.contextPath}?category=<%= category %>">
                                   <%= category %>
                               </a>
                        <%
                            }
                        %>
<!--                        <a class="dropdown-item" href="#">Another action</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Something else here</a>-->
                    </div>
                </li>
            </ul>

        <% if (logged_user != null) { %>
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Bem vindo <%= logged_user.getName() %>!
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <% if (logged_user.getRole().equals("admin")) { %>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/admin_home.jsp">Dashboard</a>
                        <div class="dropdown-divider"></div>
                    <% } %>
                    <a class="dropdown-item" href="cart.jsp">Carrinho</a>
                    <a class="dropdown-item" href="SessionController?action=logout">Logout</a></div>
                </div>
            </div>
        <% } else { %>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#login_modal">
            Login
        </button>
        <% } %>


        <!-- <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form> -->
        </div>
    </nav>
        
    <!-- Login Modal -->
    <div class="modal fade" id="login_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Login</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <% if (request.getParameter("alert") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getParameter("alert") %>
                    </div>
                    <% } %>
                    <form method="POST" action="SessionController" id="loginForm">
                        <input type="hidden" id="accessAction" name="action" value="login">
                        <div class="form-group" id="name-form-group" style='display:none;'>
                            <label for="name">Name</label>
                            <input type="text" name="name" class="form-control" id="name" placeholder="Name">
                        </div>
                        <div class="form-group">
                            <label for="name">Email address</label> 
                            <input type="email" name="email" class="form-control" id="email" aria-describedby="emailHelp"
                                placeholder="Enter email" required>
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                                else.</small>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" class="form-control" id="password" placeholder="Password" required>
                        </div>
                        <button type="submit" id="formSubmit" class="btn btn-primary">Login</button>
                        <div id="formLoginQuestion">
                            <p>NÃ£o possui login? <a href="#" id="registerLink">Cadastre-se.</a></p>
                        </div>
                        <div id="formRegisterQuestion" style="display:none;">
                            <p>Possui registro? <a href="#" id="loginLink">Faça login.</a></p>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
                    
    <div class="modal fade" id="cart_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Carrinho</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                
                <div class="modal-body">
                    <div class="alert alert-success" role="alert">
                        Um novo item foi adicionado ao seu carrinho de compras.
                    </div>
                </div>
                
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <a href="/ProjetoFinalDevWeb/cart.jsp" class="btn btn-success">Carrinho de compras</a>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Toast message -->