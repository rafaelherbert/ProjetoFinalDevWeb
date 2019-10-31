/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import models.SellsDao;
import models.User;
/**
 *
 * @author rafae
 */
@WebServlet(name = "SellController", urlPatterns = {"/SellController"})
public class SellController extends HttpServlet {
    
    User logged_user;
    HttpSession user_session;
    
    boolean auth(HttpServletRequest request) {
        // Verifica se o usuï¿½rio estï¿½ logado!
        this.user_session = request.getSession();
        if (this.user_session != null) {
            this.logged_user = (User) user_session.getAttribute("user");
        }
        return this.logged_user != null;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("action") != null)
            if (request.getParameter("action").equals("new"))
                new_sell(request, response);
    }
    
    
    private void new_sell (HttpServletRequest request, HttpServletResponse response) throws IOException {
           try {
               
            if (!this.auth(request)) {
                response.sendRedirect("/ProjetoFinalDevWeb/index.jsp?login=true&alert=Disallowed");
                return;
            }
            
            List<Product> shopping_cart = (List<Product>) this.user_session.getAttribute("shopping_cart");;
            SellsDao sells_dao = new SellsDao();
            Sell sell = null;
            DateTimeFormatter dtf = null;
            LocalDateTime now = null;
            
            for(Product product : shopping_cart) {
                sell = new Sell();
                sell.setProduct(product);
                sell.setUser(this.logged_user);
                sell.setQuantity(product.getTemp_quantity());
                dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                now = LocalDateTime.now();
                sell.setCreation_date(dtf.format(now));
                sells_dao.insert(sell);
            }
            
            shopping_cart.clear();
            user_session.setAttribute("shopping_cart", shopping_cart);
            response.sendRedirect("/ProjetoFinalDevWeb/index.jsp?notify_mode=success&notify_message=Compra realizada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(SellController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
