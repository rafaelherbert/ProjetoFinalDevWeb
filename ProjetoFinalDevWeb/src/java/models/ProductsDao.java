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
        Statement stmt = super.conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM products");
        
        while(result.next()){
            Product product = new Product();
            product.setId(result.getInt("id"));
            product.setPrice(result.getString("price"));
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
            product.setPrice(result.getString("price"));
            product.setImg_url(result.getString("img_url"));
            product.setBrand(result.getString("brand"));
            product.setCategory(result.getString("category"));
            product_list.add(product);
        }
        
        return product_list;
    }
    
    @Override
    public Product selectById(int id) throws SQLException {
        Statement stmt = super.conn.createStatement();
        String query = String.format("SELECT * FROM products where id = %s", id);
        ResultSet result = stmt.executeQuery(query);
        
        if (result.next()){
            product.setId(result.getInt("id"));
            product.setPrice(result.getString("price"));
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
        User user = (User) object;
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "UPDATE users SET name = '?', email = '?', pass = '?', role = '?') WHERE id = ?;",
                user.name,
                user.email,
                user.pass,
                user.role,
                user.id
        );
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int insert(Object object) throws SQLException{
        User user = (User) object;
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "INSERT INTO users(name, email, pass, role) VALUES ('%s','%s','%s','%s')",
                user.name,
                user.email,
                user.pass,
                user.role
        );
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int deleteById(int id) throws SQLException {
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "DELETE FROM users WHERE id = ?",
                id
        );
        return stmt.executeUpdate(query);
    }
    
    public User login(User user) throws SQLException {
        PreparedStatement stmt = super.conn.prepareStatement("SELECT * FROM users WHERE email = ? and pass = ?");
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getPass());
        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            return this.selectById(result.getInt("id"));
        } else {
            return null;
        }
    }
}
