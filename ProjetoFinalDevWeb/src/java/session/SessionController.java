/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Product;
import models.Sell;
import models.User;
import models.UsersDao;

/**
 *
 * @author rafae
 * 
 * 
 * Servlet responsável por gerenciar os logins e cadastros.
 * 
 * 
 * 
 */
@WebServlet(name = "SessionController", urlPatterns = {"/SessionController"})
public class SessionController extends HttpServlet {
    
    UsersDao users_dao;

    @Override
    public void init() throws ServletException {
        super.init();
        
        try {
            this.users_dao = new UsersDao();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            
            User user = new User();
            user.setEmail(request.getParameter("email"));
            user.setPass(request.getParameter("password"));
            
            String error = "";
            
            switch(request.getParameter("action"))
            {
                case "login":
                    if ((user = this.users_dao.login(user)) != null) {
                        HttpSession user_session = request.getSession(true);
                        List<Product> cart = new ArrayList();
                        user_session.setAttribute("shopping_cart", cart);
                        user_session.setAttribute("user", user);
                        response.sendRedirect("index.jsp");
                    } else {
                        error = "Credenciais inválidas";
                        response.sendRedirect("index.jsp?login=true&alert=" + URLEncoder.encode(error, "UTF-8"));
                    }
                    break;
                case "register":
                    user.setName(request.getParameter("name"));
                    user.setRole("client");

                    if (this.users_dao.insert(user) != 0) {
                        response.sendRedirect("index.jsp");
                    } else {
                        error = "Credenciais inválidas";
                        response.sendRedirect("index.jsp?login=true&alert=" + URLEncoder.encode(error, "UTF-8"));
                    }
                    break;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
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
