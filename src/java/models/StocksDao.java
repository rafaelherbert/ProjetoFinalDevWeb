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
public class StocksDao extends Dao{
    
    public StocksDao() throws SQLException, ClassNotFoundException {
    }
    
    @Override
    public List getAll() throws SQLException{
        List stock_list = new ArrayList();
        Statement stmt = super.conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM users");
        
        while(result.next()){
            Stock stock = new Stock();
            stock.setId(result.getInt("id"));
            stock.setProduct_id(result.getInt("product_id"));
            stock.setQuantify(result.getInt("quantify"));            
            stock_list.add(stock);
        }
        
        return stock_list;
    }
    
    @Override
    public List query(String query) throws SQLException {
        List stock_list = new ArrayList();
        Statement stmt = super.conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        
        while (result.next()){
            Stock stock = new Stock();
            stock.setId(result.getInt("id"));
            stock.setProduct_id(result.getInt("product_id"));
            stock.setQuantify(result.getInt("quantify"));            
            stock_list.add(stock);
        }
        
        return stock_list;
    }
    
    @Override
    public Stock selectById(int id) throws SQLException {
        Statement stmt = super.conn.createStatement();
        String query = String.format("SELECT * FROM stock where id = %s", id);
        ResultSet result = stmt.executeQuery(query);
        
        if (result.next()){
            Stock stock = new Stock();
            stock.setId(result.getInt("id"));
            stock.setProduct_id(result.getInt("product_id"));
            stock.setQuantify(result.getInt("quantify"));      
            return stock;
        } else {
            return null;
        }
    }
    
    @Override
    public int update(Object object) throws SQLException {
        Stock stock = (Stock) object;
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "UPDATE stock SET produc_id = '?', quantify = '?' WHERE id = ?;",
                stock.product_id,
                stock.quantify,               
                stock.id
        );
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int insert(Object object) throws SQLException{
       Stock stock = (Stock) object;
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "INSERT INTO stock(produc_id, quantify) VALUES ('%s','%s')",
                stock.product_id,
                stock.quantify
        );
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int deleteById(int id) throws SQLException {
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "DELETE FROM stock WHERE id = ?",
                id
        );
        return stmt.executeUpdate(query);
    }   
}
