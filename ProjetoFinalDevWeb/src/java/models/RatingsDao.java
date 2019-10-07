/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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
        Statement stmt = super.conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM ratings");
        
        while(result.next()){
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
        Statement stmt = super.conn.createStatement();
        String query = String.format("SELECT * FROM ratings where id = %s", id);
        ResultSet result = stmt.executeQuery(query);
        
        if (result.next()){
            Rating rating = new Rating();
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
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "UPDATE rating SET user_id = '?', product_id = '?', rating = '?' WHERE id = ?;",
                rating.user_id,
                rating.product_id,
                rating.rating,
                rating.id
        );
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int insert(Object object) throws SQLException{
        Rating rating = (Rating) object;
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "INSERT INTO ratings(user_id, product_id, rating) VALUES ('%s','%s','%s')",
                rating.user_id,
                rating.product_id,
                rating.rating                
        );
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int deleteById(int id) throws SQLException {
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "DELETE FROM ratings WHERE id = ?",
                id
        );
        return stmt.executeUpdate(query);
    }
    
}
