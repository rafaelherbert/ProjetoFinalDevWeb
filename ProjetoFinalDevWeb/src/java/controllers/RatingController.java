/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import models.Product;
import models.ProductsDao;
import models.Rating;
import models.RatingsDao;
import models.User;

/**
 *
 * @author ramir
 */
@WebServlet(name = "RatingController", urlPatterns = {"/RatingController"})
public class RatingController extends HttpServlet {
    
    HttpSession user_session;
    User logged_user;
    
    boolean auth(HttpServletRequest request) {
        // Verifica se o usuï¿½rio estï¿½ logado!
        this.user_session = request.getSession();
        if (this.user_session != null) {
            this.logged_user = (User) user_session.getAttribute("user");
        }
        return this.logged_user != null;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Not working yet. We'll need to work on this.
        
        try {
            
            if (!this.auth(request)) {
                response.sendRedirect("/ProjetoFinalDevWeb/index.jsp?login=true&alert=Disallowed");
                return;
            }
            
            RatingsDao ratings_dao = new RatingsDao();
            ProductsDao products_dao = new ProductsDao();
            Product product = products_dao.selectById(Integer.parseInt(request.getParameter("product_id")));
            Rating rating = new Rating();
            rating.setUser(this.logged_user);
            rating.setProduct(product);
            rating.setRating(Integer.parseInt(request.getParameter("rating")));
            rating.setComment(request.getParameter("comment"));
            
            switch(request.getParameter("submit"))
            {
                case "insert":
                    if (ratings_dao.insert(rating)!= 0) {
                        String feedback = "Produto avaliado com sucesso.";
                        response.sendRedirect("/ProjetoFinalDevWeb/user.jsp?notify_mode=success&notify_message=" + feedback);
                    }
                case "update":
                    String rating_id_str = request.getParameter("rating_id");
                    if (rating_id_str != null)
                        rating.setId(Integer.parseInt(rating_id_str));
                    
                    if (ratings_dao.update(rating) != 0) {
                        String feedback = "Avaliação editada com sucesso.";
                        response.sendRedirect("/ProjetoFinalDevWeb/user.jsp?notify_mode=success&notify_message=" + feedback);
                    }
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
