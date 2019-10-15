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
    public List query(String query) throws SQLException {
        List product_list = new ArrayList();
        
        Statement stmt = super.conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        
        while (result.next()){
            Product product = new Product();
            product.setId(result.getInt("id"));
            product.setName(result.getString("name"));
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
        PreparedStatement sql = super.conn.prepareStatement("UPDATE products SET name = ?, description = ?,  price = ?, img_url = ?, brand = ?, category = ? WHERE id = ?;");
        sql.setString(1, product.getName());
        sql.setString(2, product.getDescription());
        sql.setDouble(3, product.getPrice());
        sql.setString(4, product.getImg_url());
        sql.setString(5, product.getBrand());
        sql.setString(6, product.getCategory());
        sql.setInt(7, product.getId());
        return sql.executeUpdate();
    }
    
    @Override
    public int insert(Object object) throws SQLException{
        Product product = (Product) object;
        PreparedStatement sql = super.conn.prepareStatement("INSERT INTO products(name, description, price, img_url, brand, category) VALUES (?,?,?,?,?,?);");
        sql.setString(1, product.getName());
        sql.setString(2, product.getDescription());
        sql.setDouble(3, product.getPrice());
        sql.setString(4, product.getImg_url());
        sql.setString(5, product.getBrand());
        sql.setString(6, product.getCategory());
        return sql.executeUpdate();
    }
    
    @Override
    public int deleteById(int id) throws SQLException {
        PreparedStatement sql = super.conn.prepareStatement("DELETE FROM products WHERE id = ?");
        sql.setInt(1, id);
        return sql.executeUpdate();
    }
}