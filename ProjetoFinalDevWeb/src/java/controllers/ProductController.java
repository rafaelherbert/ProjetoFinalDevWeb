/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Product;
import models.ProductsDao;

/**
 *
 * @author ramir
 */
public class ProductController extends HttpServlet {

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
            ProductsDao products_dao = new ProductsDao();
            Product product = new Product();
            product.setName(request.getParameter("name"));
            product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            product.setPrice(Double.parseDouble(request.getParameter("price")));
            product.setBrand(request.getParameter("brand"));
            product.setCategory(request.getParameter("category"));
            product.setDescription(request.getParameter("description"));
            product.setImg_url(request.getParameter("img_url"));

            switch(request.getParameter("submit"))
            {
                case "insert":
                    if (products_dao.insert(product)!= 0) {
                        String feedback = "Produto criado com sucesso.";
                        response.sendRedirect("admin/admin_products_dashboard.jsp?feedback=" + URLEncoder.encode(feedback, "UTF-8"));
                    }
                case "update":
                    if (!request.getParameter("id").isEmpty()) {
                        product.setId(Integer.parseInt(request.getParameter("id")));
                    }
                    
                    if (products_dao.update(product)!= 0) {
                        String feedback = "Produto editado com sucesso.";
                        response.sendRedirect("admin/admin_products_dashboard.jsp?feedback=" + URLEncoder.encode(feedback, "UTF-8"));
                    }
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
