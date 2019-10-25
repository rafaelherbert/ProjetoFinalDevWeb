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
public class FavoritesDao extends Dao{
    
    public FavoritesDao() throws SQLException, ClassNotFoundException {
    }
    
    @Override
    public List getAll() throws SQLException{
        List favorite_list = new ArrayList();
        PreparedStatement sql = super.conn.prepareStatement("SELECT users.`name`as Nome_Cliente, products.`name` as Produto, "
                + "products.description as Desc_Prod from favorites inner join users on users.id = favorites.user_id "
                + "inner join products on products.id = favorites.product_id;");
        ResultSet result = sql.executeQuery();
        
        while(result.next()){
            Favorite favorite = new Favorite();
            favorite.setId(result.getInt("id"));
            favorite.setUser_id(result.getInt("user_id"));
            favorite.setProduct_id(result.getInt("product_id"));
            
            User user = new User();
            user.setId(result.getInt("id"));
            user.setName(result.getString("Nome_Cliente"));
            
            Product product = new Product();
            product.setId(result.getInt("id"));
            product.setName(result.getString("Produto"));
            product.setDescription(result.getString("Desc_Prod"));
            favorite_list.add(favorite);
        }
        
        return favorite_list;
    }
    
    @Override
    public List query(String query) throws SQLException {
        List favorite_list = new ArrayList();
        Statement stmt = super.conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        
        while (result.next()){
            Favorite favorite = new Favorite();
            favorite.setId(result.getInt("id"));
            favorite.setUser_id(result.getInt("user_id"));
            favorite.setProduct_id(result.getInt("product_id"));
            favorite_list.add(favorite);
        }
        
        return favorite_list;
    }
    
    @Override
    public Favorite selectById(int id) throws SQLException {
        PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM favorites where id = ?");
        sql.setInt(1, id);
        ResultSet result = sql.executeQuery();
        
        Favorite favorite = new Favorite();
        if (result.next()){            
            favorite.setId(result.getInt("id"));
            favorite.setUser_id(result.getInt("user_id"));
            favorite.setProduct_id(result.getInt("product_id"));
            return favorite;
        } else {
            return null;
        }
    }
    
   @Override
    public int update(Object object) throws SQLException {
        Favorite favorite = (Favorite) object;
        PreparedStatement sql = super.conn.prepareStatement("UPDATE favorites SET user_id = '?', product_id = '?' WHERE id = ?;");
        
                sql.setInt(1, favorite.getUser_id());
                sql.setInt(2, favorite.getProduct_id());
                sql.setInt(3, favorite.getId());
        
        return sql.executeUpdate();
    }
    
    @Override
    public int insert(Object object) throws SQLException{
        Favorite favorite = (Favorite) object;
        PreparedStatement sql = super.conn.prepareStatement("INSERT INTO favorites(user_id,user_id) VALUES (?,?)");
        
                sql.setInt (1, favorite.getUser_id());
                sql.setInt (2, favorite.getProduct_id());
        
        return sql.executeUpdate();
    }
    
    @Override
    public int deleteById(int id) throws SQLException {
        PreparedStatement sql = super.conn.prepareStatement("DELETE FROM favorites WHERE id = ?");
       
                sql.setInt (1, id);
       
        return sql.executeUpdate();
    }     
   
}
