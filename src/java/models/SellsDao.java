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
public class SellsDao extends Dao{
    
    public SellsDao() throws SQLException, ClassNotFoundException {
    }
    
    @Override
    public List getAll() throws SQLException{
        List sell_list = new ArrayList();
        PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM sells");
        ResultSet result = sql.executeQuery();
        
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
        PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM sells where id = ?");
        sql.setInt(1, id);
        ResultSet result = sql.executeQuery();
        
        Sell sell = new Sell();
        if (result.next()){            
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
        PreparedStatement sql = super.conn.prepareStatement( "UPDATE sells SET user_id = '?', product_id = '?', quantify = '?' WHERE id = ?;");
                sql.setInt (1, sell.getUser_id());
                sql.setInt(2, sell.getProduct_id());
                sql.setInt (3, sell.getQuantify());
                sql.setInt(4, sell.getId());
        
        return sql.executeUpdate();
    }
    
    @Override
    public int insert(Object object) throws SQLException{
        Sell sell = (Sell) object;
        PreparedStatement sql = super.conn.prepareStatement("INSERT INTO sells(user_id, product_id, quantify) VALUES (?,?,?,?)");
                sql.setInt (1, sell.getUser_id());
                sql.setInt(2, sell.getProduct_id());
                sql.setInt(3, sell.getQuantify());
                sql.setDate(4, sell.getSqlDate());
                
                
        
                
        return sql.executeUpdate();
    }
    
    @Override
    public int deleteById(int id) throws SQLException {
        PreparedStatement sql = super.conn.prepareStatement("DELETE FROM sells WHERE id = ?");
        
                sql.setInt(1,id);        
                return sql.executeUpdate();
    }   
    
}
