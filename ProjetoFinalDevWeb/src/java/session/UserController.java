/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import models.UsersDao;
import static org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode;

/**
 *
 * @author rafae
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {
    
    UsersDao users_dao;

    @Override
    public void init() throws ServletException {
        super.init();
        
        try {
            this.users_dao = new UsersDao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPass(request.getParameter("password"));
            user.setRole("client");
            
            String error = "";
            String feedback = "";
            
            switch(request.getParameter("action"))
            {
                case "login":
                    if ((user = this.users_dao.login(user)) != null) {
                        HttpSession user_session = request.getSession(true);
                        user_session.setAttribute("user_email", user.getEmail());
                        user_session.setAttribute("user_name", user.getName());
                        response.sendRedirect("index.jsp");
                    } else {
                        error = "Credenciais inválidas";
                        response.sendRedirect("index.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
                    }
                    break;
                case "register":
                    if (this.users_dao.insert(user) != 0) {
                        feedback = "Usuário registrado com sucesso! Faça o login para continuar.";
                        response.sendRedirect("index.jsp?feedback=" + URLEncoder.encode(feedback, "UTF-8"));
                    } else {
                        error = "Credenciais inválidas";
                        response.sendRedirect("index.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
                    }
                    break;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch(action) {
           case "logout":
                HttpSession session = req.getSession();
                session.invalidate();
                resp.sendRedirect("index.jsp");
                break;
        }
    }
    
}
