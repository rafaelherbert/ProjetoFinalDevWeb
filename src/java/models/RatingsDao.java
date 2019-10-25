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
public class RatingsDao extends Dao{
    
    public RatingsDao() throws SQLException, ClassNotFoundException {
    }
    
    @Override
    public List getAll() throws SQLException{
        List rating_list = new ArrayList();
        PreparedStatement sql = super.conn.prepareStatement("SELECT ratings.´rating´as Stars, users.`name`as Nome_Cliente, products.`name` as Produto, "
                + "products.description as Desc_Prod from ratings inner join users on users.id = ratings.user_id "
                + "inner join products on products.id = ratings.product_id;");
        ResultSet result = sql.executeQuery();
        
        while(result.next()){
            Rating rating = new Rating();
            rating.setId(result.getInt("id"));
            rating.setUser_id(result.getInt("user_id"));
            rating.setProduct_id(result.getInt("product_id"));
            rating.setRating(result.getInt("rating")); 
            
            User user = new User();
            user.setId(result.getInt("id"));
            user.setName(result.getString("Nome_Cliente"));
            
            Product product = new Product();
            product.setId(result.getInt("id"));
            product.setName(result.getString("Produto"));
            product.setDescription(result.getString("Desc_Prod"));
            
            rating_list.add(rating);              
        }      
        return rating_list;
    }
    
    @Override
    public List query(String query) throws SQLException {
        List rating_list = new ArrayList();
        Statement stmt = super.conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        
        while (result.next()){
            Rating rating = new Rating();
            rating.setId(result.getInt("id"));
            rating.setUser_id(result.getInt("user_id"));
            rating.setProduct_id(result.getInt("product_id"));
            rating.setRating(result.getInt("rating"));           
            rating_list.add(rating);
        }
        
        return rating_list;
    }
    
    @Override
    public Rating selectById(int id) throws SQLException {
        PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM ratings WHERE id = ?");
        sql.setInt(1, id);
        ResultSet result = sql.executeQuery();
        
        Rating rating = new Rating();
        if (result.next()){            
            rating.setId(result.getInt("id"));
            rating.setUser_id(result.getInt("user_id"));
            rating.setProduct_id(result.getInt("product_id"));
            rating.setRating(result.getInt("rating"));    
            return rating;
        }
        else{
            return null;
        }
    }
    
    @Override
    public int update(Object object) throws SQLException {
        Rating rating = (Rating) object;
        PreparedStatement sql = super.conn.prepareStatement("UPDATE ratings SET user_id = ?, product_id = ?, rating = ? WHERE id = ?;");
        sql.setInt(1, rating.getUser_id());
        sql.setInt(2, rating.getProduct_id());
        sql.setInt(3, rating.getRating());              
        
        return sql.executeUpdate();
    }
    
    @Override
    public int insert(Object object) throws SQLException{
       Rating rating = (Rating) object;
       PreparedStatement sql = super.conn.prepareStatement("INSERT INTO ratings(user_id, product_id, rating)VALUES(?,?,?);");
       sql.setInt(1, rating.getUser_id());
       sql.setInt(2, rating.getProduct_id());
       sql.setInt(3, rating.getRating()); 
               
       return sql.executeUpdate();
    }
    @Override
    public int deleteById(int id) throws SQLException {
        PreparedStatement sql = super.conn.prepareStatement("DELETE FROM ratings WHERE id = ?");
        sql.setInt(1, id);
        return sql.executeUpdate();
    }
       
}
