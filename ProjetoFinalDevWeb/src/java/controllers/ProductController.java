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
import models.User;

/**
 *
 * @author ramir
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
@MultipartConfig(
    location = "C:\\dev\\ProjetoFinalDevWeb\\ProjetoFinalDevWeb\\web\\storage"
)
public class ProductController extends HttpServlet {
    
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
            
            ProductsDao products_dao = new ProductsDao();
            Product product = new Product();
            product.setName(request.getParameter("name"));
            product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            product.setPrice(Double.parseDouble(request.getParameter("price")));
            product.setBrand(request.getParameter("brand"));
            product.setCategory(request.getParameter("category"));
            product.setDescription(request.getParameter("description"));
            
            
            // O bloco de código abaixo faz o upload da imagem e a armazena com o hashmd5 do
            // nome da imagem enviada concatenado com o nome do produto.
            
            // Handling image
            Part filePart = request.getPart("img_url");
            
            // Generating MD5 hash (Img name)
            String s = product.getName() + filePart.getSubmittedFileName();
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            m.update(s.getBytes(),0,s.length());
            
            // Storing img pathname to be used in the img html tag.
            String filename = new BigInteger(1,m.digest()).toString(16) + "." + getFileExtension(filePart);
            String pathname = getServletContext().getContextPath() + "/storage/" + filename;
            filePart.write(filename); // Storing file.
            product.setImg_url(pathname);
            
            switch(request.getParameter("submit"))
            {
                case "insert":
                    if (products_dao.insert(product)!= 0) {
                        String feedback = "Produto criado com sucesso.";
                        response.sendRedirect("/ProjetoFinalDevWeb/admin/admin_products_dashboard.jsp?feedback=" + URLEncoder.encode(feedback, "UTF-8"));
                    }
                case "update":
                    if (!request.getParameter("id").isEmpty()) {
                        product.setId(Integer.parseInt(request.getParameter("id")));
                    }
                    
                    if (products_dao.update(product)!= 0) {
                        String feedback = "Produto editado com sucesso.";
                        response.sendRedirect("/ProjetoFinalDevWeb/admin/admin_products_dashboard.jsp?feedback=" + URLEncoder.encode(feedback, "UTF-8"));
                    }
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static String getFileExtension(Part file) {
        String fileName = file.getSubmittedFileName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
