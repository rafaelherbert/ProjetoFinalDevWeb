/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ramir
 */
public class ProductsDao extends Dao{
    
    public ProductsDao() throws SQLException, ClassNotFoundException {
    }
    
    @Override
    public List getAll() throws SQLException{
        List product_list = new ArrayList();
        
        PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM products");
        ResultSet result = sql.executeQuery();
        
        while(result.next()){
            Product product = new Product();
            product.setId(result.getInt("id"));
            product.setName(result.getString("name"));
            product.setQuantity(result.getInt("quantity"));
            product.setDescription(result.getString("description"));
            product.setPrice(result.getDouble("price"));
            product.setImg_url(result.getString("img_url"));
            product.setBrand(result.getString("brand"));
            product.setCategory(result.getString("category"));
            product_list.add(product);
        }
        
        return product_list;
    }
    
    @Override
    public Product selectById(int id) throws SQLException {
        PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM products WHERE id = ?");
        sql.setInt(1, id);
        ResultSet result = sql.executeQuery();
        
        Product product = new Product();
        if (result.next()){
            product.setId(result.getInt("id"));
            product.setName(result.getString("name"));
            product.setQuantity(result.getInt("quantity"));
            product.setDescription(result.getString("description"));
            product.setPrice(result.getDouble("price"));
            product.setImg_url(result.getString("img_url"));
            product.setBrand(result.getString("brand"));
            product.setCategory(result.getString("category"));
            return product;
        } else {
            return null;
        }
    }
    
    @Override
    public int update(Object object) throws SQLException {
        Product product = (Product) object;
        PreparedStatement sql = super.conn.prepareStatement("UPDATE products SET name = ?, quantity = ?, description = ?,  price = ?, img_url = ?, brand = ?, category = ? WHERE id = ?;");
        sql.setString(1, product.getName());
        sql.setInt(2, product.getQuantity());
        sql.setString(3, product.getDescription());
        sql.setDouble(4, product.getPrice());
        sql.setString(5, product.getImg_url());
        sql.setString(6, product.getBrand());
        sql.setString(7, product.getCategory());
        sql.setInt(8, product.getId());
        return sql.executeUpdate();
    }
    
    @Override
    public int insert(Object object) throws SQLException{
        Product product = (Product) object;
        PreparedStatement sql = super.conn.prepareStatement("INSERT INTO products(name, quantity, description, price, img_url, brand, category) VALUES (?,?,?,?,?,?,?);");
        sql.setString(1, product.getName());
        sql.setInt(2, product.getQuantity());
        sql.setString(3, product.getDescription());
        sql.setDouble(4, product.getPrice());
        sql.setString(5, product.getImg_url());
        sql.setString(6, product.getBrand());
        sql.setString(7, product.getCategory());
        return sql.executeUpdate();
    }
    
    @Override
    public int deleteById(int id) throws SQLException {
        PreparedStatement sql = super.conn.prepareStatement("DELETE FROM products WHERE id = ?");
        sql.setInt(1, id);
        return sql.executeUpdate();
    }
}