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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ramir
 */
public class RatingsDao extends Dao{
    
    public RatingsDao() throws SQLException, ClassNotFoundException {
    }
    
    public List getRatingsByProductId(int product_id) throws SQLException {
        try {
            PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM ratings WHERE product_id = ?");
            sql.setInt(1, product_id);
            ResultSet result = sql.executeQuery();
            UsersDao users_dao = new UsersDao();
            ProductsDao products_dao = new ProductsDao();
            List ratings = new ArrayList();
            
            while(result.next()) {
                Rating rating = new Rating();
                rating.setId(result.getInt("id"));
                rating.setUser(users_dao.selectById(result.getInt("user_id")));
                rating.setProduct(products_dao.selectById(result.getInt("product_id")));
                rating.setRating(result.getInt("rating"));
                rating.setComment(result.getString("comment"));
                ratings.add(rating);
            }
            
            return ratings;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FavoritesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List getRatingsByUserId(int user_id) throws SQLException {
        try {
            PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM ratings WHERE id = ?");
            sql.setInt(1, user_id);
            ResultSet result = sql.executeQuery();
            UsersDao users_dao = new UsersDao();
            ProductsDao products_dao = new ProductsDao();
            List ratings = new ArrayList();
            
            while(result.next()) {
                Rating rating = new Rating();
                rating.setId(result.getInt("id"));
                rating.setUser(users_dao.selectById(result.getInt("user_id")));
                rating.setProduct(products_dao.selectById(result.getInt("product_id")));
                rating.setRating(result.getInt("rating"));
                rating.setComment(result.getString("comment"));
                ratings.add(rating);
            }
            
            return ratings;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FavoritesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    @Override
    public List getAll() throws SQLException {
        try {
            PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM ratings");
            ResultSet result = sql.executeQuery();
            UsersDao users_dao = new UsersDao();
            ProductsDao products_dao = new ProductsDao();
            List ratings = new ArrayList();
            
            while(result.next()) {
                Rating rating = new Rating();
                rating.setId(result.getInt("id"));
                rating.setUser(users_dao.selectById(result.getInt("user_id")));
                rating.setProduct(products_dao.selectById(result.getInt("product_id")));
                rating.setRating(result.getInt("rating"));
                rating.setComment(result.getString("comment"));
                ratings.add(rating);
            }
            
            return ratings;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FavoritesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Rating selectById(int id) throws SQLException {
        try {
            PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM ratings WHERE id = ?");
            sql.setInt(1, id);
            ResultSet result = sql.executeQuery();
            UsersDao users_dao = new UsersDao();
            ProductsDao products_dao = new ProductsDao();
            Rating rating = new Rating();

            if(result.next()) {
                rating.setId(result.getInt("id"));
                rating.setUser(users_dao.selectById(result.getInt("user_id")));
                rating.setProduct(products_dao.selectById(result.getInt("product_id")));
                rating.setRating(result.getInt("rating"));
                rating.setComment(result.getString("comment"));
            }
            
            return rating;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FavoritesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int update(Object object) throws SQLException {
        Rating rating = (Rating) object;
        PreparedStatement sql = super.conn.prepareStatement("UPDATE ratings SET user_id = ?, product_id = ?, rating = ?, comment = ? WHERE id = ?;");
        sql.setInt(1, rating.user.getId());
        sql.setInt(2, rating.product.getId());
        sql.setInt(3, rating.getRating());
        sql.setString(4, rating.getComment());
        sql.setInt(5, rating.getId());
        return sql.executeUpdate();
    }

    @Override
    public int insert(Object object) throws SQLException {
        Rating rating = (Rating) object;
        PreparedStatement sql = super.conn.prepareStatement("INSERT INTO ratings(user_id, product_id, rating, comment) VALUES (?, ?, ?, ?);");
        sql.setInt(1, rating.user.getId());
        sql.setInt(2, rating.product.getId());
        sql.setInt(3, rating.getRating());
        sql.setString(4, rating.getComment());
        return sql.executeUpdate();
    }

    @Override
    public int deleteById(int id) throws SQLException {
        PreparedStatement sql = super.conn.prepareStatement("DELETE FROM ratings WHERE id = ?");
        sql.setInt(1, id);
        return sql.executeUpdate();
    }
   
}
