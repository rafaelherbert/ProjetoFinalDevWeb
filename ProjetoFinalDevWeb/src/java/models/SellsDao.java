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
public class SellsDao extends Dao{
    
    public SellsDao() throws SQLException, ClassNotFoundException {
    }
    
    @Override
    public List getAll() throws SQLException{
        List sell_list = new ArrayList();
        Statement stmt = super.conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM sells");
        
        while(result.next()){
            Sell sell = new Sell();
            sell.setId(result.getInt("id"));
            sell.setUser_id(result.getInt("user_id"));
            sell.setProduct_id(result.getInt("product_id"));
            sell.setQuantify(result.getInt("quantify"));
            sell_list.add(sell);
        }
        
        return sell_list;
    }
    
    @Override
    public List query(String query) throws SQLException {
        List sell_list = new ArrayList();
        Statement stmt = super.conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        
        while (result.next()){
            Sell sell = new Sell();
            sell.setId(result.getInt("id"));
            sell.setUser_id(result.getInt("user_id"));
            sell.setProduct_id(result.getInt("product_id"));
            sell.setQuantify(result.getInt("quantify"));
            sell_list.add(sell);
        }
        
        return sell_list;
    }
    @Override
    public Sell selectById(int id) throws SQLException {
        Statement stmt = super.conn.createStatement();
        String query = String.format("SELECT * FROM sells where id = %s", id);
        ResultSet result = stmt.executeQuery(query);
        
        if (result.next()){
            Sell sell = new Sell();
            sell.setId(result.getInt("id"));
            sell.setUser_id(result.getInt("user_id"));
            sell.setProduct_id(result.getInt("product_id"));
            sell.setQuantify(result.getInt("quantify"));
            return sell;
        } else {
            return null;
        }
    }
    
    @Override
    public int update(Object object) throws SQLException {
        Sell sell = (Sell) object;
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "UPDATE sells SET user_id = '?', product_id = '?', quantify = '?' WHERE id = ?;",
                sell.user_id,
                sell.product_id,
                sell.quantify,
                sell.id
        );
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int insert(Object object) throws SQLException{
        Sell sell = (Sell) object;
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "INSERT INTO sells(user_id, product_id, quantify) VALUES ('%s','%s','%s''%s')",
                sell.user_id,
                sell.product_id,
                sell.quantify,
                sell.sqlDate
                
                
        );
                
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int deleteById(int id) throws SQLException {
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "DELETE FROM sells WHERE id = ?",
                id
        );
        return stmt.executeUpdate(query);
    }   
    
}
