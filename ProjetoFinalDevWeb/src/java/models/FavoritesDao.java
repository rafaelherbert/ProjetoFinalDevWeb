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
public class FavoritesDao extends Dao{
    
    public FavoritesDao() throws SQLException, ClassNotFoundException {
    }

    @Override
    public List getAll() throws SQLException {
        try {
            PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM favorites");
            ResultSet result = sql.executeQuery();
            UsersDao users_dao = new UsersDao();
            ProductsDao products_dao = new ProductsDao();
            List favorites = new ArrayList();
            
            while(result.next()) {
                Favorite favorite = new Favorite();
                favorite.setId(result.getInt("id"));
                favorite.setUser(users_dao.selectById(result.getInt("user_id")));
                favorite.setProduct(products_dao.selectById(result.getInt("product_id")));
                favorites.add(favorite);
            }
            
            return favorites;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FavoritesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Object selectById(int id) throws SQLException {
        try {
            PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM favorites WHERE id = ?");
            sql.setInt(1, id);
            ResultSet result = sql.executeQuery();
            UsersDao users_dao = new UsersDao();
            ProductsDao products_dao = new ProductsDao();
            Favorite favorite = new Favorite();
            
            if (result.next()) {
                favorite.setId(result.getInt("id"));
                favorite.setUser(users_dao.selectById(result.getInt("user_id")));
                favorite.setProduct(products_dao.selectById(result.getInt("product_id")));
            }
            
            return favorite;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FavoritesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int update(Object object) throws SQLException {
        Favorite favorite = (Favorite) object;
        PreparedStatement sql = super.conn.prepareStatement("UPDATE favorites SET user_id = ?, product_id = ? WHERE id = ?;");
        sql.setInt(1, favorite.user.getId());
        sql.setInt(2, favorite.product.getId());
        sql.setInt(3, favorite.getId());
        return sql.executeUpdate();
    }

    @Override
    public int insert(Object object) throws SQLException {
        Favorite favorite = (Favorite) object;
        PreparedStatement sql = super.conn.prepareStatement("INSERT INTO favorites(user_id, product_id) VALUES (?, ?);");
        sql.setInt(1, favorite.user.getId());
        sql.setInt(2, favorite.product.getId());
        return sql.executeUpdate();
    }

    @Override
    public int deleteById(int id) throws SQLException {
        PreparedStatement sql = super.conn.prepareStatement("DELETE FROM favorites WHERE id = ?");
        sql.setInt(1, id);
        return sql.executeUpdate();
    }
   
}
