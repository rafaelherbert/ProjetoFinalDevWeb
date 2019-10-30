/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Favorite;
import models.FavoritesDao;
import models.Product;
import models.ProductsDao;
import models.User;

/**
 *
 * @author rafae
 */
public class FavoriteController extends HttpServlet {
    
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!auth(request)) {
                response.sendRedirect("/ProjetoFinalDevWeb/index.jsp?login=true&alert=Disallowed");
                return;
            }
            String product_id_str = request.getParameter("product_id");
            String favorite_id_str = request.getParameter("favorite_id");
            String action = request.getParameter("action");
            
            FavoritesDao favorites_dao = new FavoritesDao();
            Favorite favorite = new Favorite();
            
            ProductsDao products_dao = new ProductsDao();
            Product product = null;
            
            if (product_id_str != null)
                product = products_dao.selectById(Integer.parseInt(product_id_str));
            
            if (action.equals("add")) {
                favorite.setProduct(product);
                favorite.setUser(this.logged_user);
                favorites_dao.insert(favorite);
            } else if (action.equals("remove")) {
                if (favorite_id_str != null)
                    favorites_dao.deleteById(Integer.parseInt(favorite_id_str));
            }
            
            response.sendRedirect(request.getParameter("referer"));
            
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FavoriteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
