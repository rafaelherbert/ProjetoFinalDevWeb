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
            product.setName(result.getString("name"));
            product.setDescription(result.getString("description"));
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
            product.setName(result.getString("name"));
            product.setDescription(result.getString("description"));
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
        Product product = new Product();
        if (result.next()){
            product.setId(result.getInt("id"));
            product.setName(result.getString("name"));
            product.setDescription(result.getString("description"));
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
        Product product = (Product) object;
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "UPDATE users SET name = '?', description = '?',  price = '?', img_url = '?', brand = '?', category = '?' WHERE id = ?;",
                product.price,
                product.img_url,
                product.brand,
                product.category,
                product.id
        );
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int insert(Object object) throws SQLException{
        Product product = (Product) object;
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "INSERT INTO product(name, description, price, img_url, brand, category) VALUES ('%s','%s','%s','%s','%s','%s')",
                product.price,
                product.img_url,
                product.brand,
                product.category
        );
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int deleteById(int id) throws SQLException {
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "DELETE FROM products WHERE id = ?",
                id
        );
        return stmt.executeUpdate(query);
    }
}