<%-- 
    Document   : index
    Created on : 13/09/2019, 21:36:26
    Author     : rafae
--%>

<%@page import="models.User"%>
<%@page import="java.util.List"%>
<%@page import="models.UsersDao"%>

<%
// Verifica se o usu�rio est� logado!
boolean logged_in = false;
String user_name = "";
String user_role = "";

HttpSession user_session = request.getSession();

if (user_session.getAttribute("user_email") != null) {
    logged_in = true;
    user_name = (String) user_session.getAttribute("user_name");
    user_role = (String) user_session.getAttribute("user_role");
}

%>

<%@ include file="header.jsp" %>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
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
                <form method="POST" action="SessionController" id="loginForm">
                    <input type="hidden" id="accessAction" name="action" value="login">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                            placeholder="Enter email">
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                            else.</small>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <button type="submit" id="formSubmit" class="btn btn-primary">Login</button>
                    <div id="formLoginQuestion" style="display:none;">
                        <p>Não possui login? <a href="#" id="registerLink">Cadastre-se.</a></p>
                    </div>
                    <div id="formRegisterQuestion">
                        <p>Possui registro? <a href="#" id="loginLink">Faça login.</a></p>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>