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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Pending

/**
 *
 * @author ramir
 */
public class SellsDao extends Dao{
    
    public SellsDao() throws SQLException, ClassNotFoundException {
    }

    @Override
    List getAll() throws SQLException {
        try {
            PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM sells");
            ResultSet result = sql.executeQuery();
            UsersDao users_dao = new UsersDao();
            ProductsDao products_dao = new ProductsDao();
            List sells = new ArrayList();
            
            while(result.next()) {
                Sell sell = new Sell();
                sell.setId(result.getInt("id"));
                sell.setUser(users_dao.selectById(result.getInt("user_id")));
                sell.setProduct(products_dao.selectById(result.getInt("product_id")));
                sell.setQuantity(result.getInt("quantity"));
                sell.setCreation_date(result.getString("creation_date"));
//                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//                LocalDateTime now = LocalDateTime.now();  
//                sell.setCreation_date(dtf.format(now));
                sells.add(sell);
            }
            
            return sells;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FavoritesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    Object selectById(int id) throws SQLException {
       try {
            PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM sells");
            ResultSet result = sql.executeQuery();
            UsersDao users_dao = new UsersDao();
            ProductsDao products_dao = new ProductsDao();
            Sell sell = new Sell();

            if(result.next()) {
                sell.setId(result.getInt("id"));
                sell.setUser(users_dao.selectById(result.getInt("user_id")));
                sell.setProduct(products_dao.selectById(result.getInt("product_id")));
                sell.setQuantity(result.getInt("quantity"));
                sell.setCreation_date(result.getString("creation_date"));
//                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  ;
//                LocalDateTime now = LocalDateTime.now();  
//                sell.setCreation_date(dtf.format(now));
            }
            
            return sell;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FavoritesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    int update(Object object) throws SQLException {
        Sell sell = (Sell) object;
        PreparedStatement sql = super.conn.prepareStatement("UPDATE sells SET user_id = ?, product_id = ?, quantity = ?, creation_date WHERE id = ?;");
        sql.setInt(1, sell.user.getId());
        sql.setInt(2, sell.product.getId());
        sql.setInt(3, sell.getQuantity());
        sql.setString(4, sell.getCreation_date());
        sql.setInt(5, sell.getId());
        return sql.executeUpdate();
    }

    @Override
    int insert(Object object) throws SQLException {
        Sell sell = (Sell) object;
        PreparedStatement sql = super.conn.prepareStatement("INSERT INTO sells(user_id, product_id, quantity, creation_date) VALUES (?, ?, ?, ?);");
        sql.setInt(1, sell.user.getId());
        sql.setInt(2, sell.product.getId());
        sql.setInt(3, sell.getQuantity());
        sql.setString(4, sell.getCreation_date());
        return sql.executeUpdate();
    }

    @Override
    int deleteById(int id) throws SQLException {
        PreparedStatement sql = super.conn.prepareStatement("DELETE FROM sells WHERE id = ?");
        sql.setInt(1, id);
        return sql.executeUpdate();
    }
   
}
