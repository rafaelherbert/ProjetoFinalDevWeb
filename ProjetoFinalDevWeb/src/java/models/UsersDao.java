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
 * @author rafae
 */
public class UsersDao extends Dao{
    
    public UsersDao() throws SQLException, ClassNotFoundException {
    }
    
    @Override
    public List getAll() throws SQLException{
        List user_list = new ArrayList();
        Statement stmt = super.conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM users");
        
        while(result.next()){
            User user = new User();
            user.setId(result.getInt("id"));
            user.setName(result.getString("name"));
            user.setEmail(result.getString("email"));
            user.setPass(result.getString("pass"));
            user.setRole(result.getString("role"));
            user_list.add(user);
        }
        
        return user_list;
    }
    
    @Override
    public List query(String query) throws SQLException {
        List user_list = new ArrayList();
        Statement stmt = super.conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        
        while (result.next()){
            User user = new User();
            user.setId(result.getInt("id"));
            user.setName(result.getString("name"));
            user.setEmail(result.getString("email"));
            user.setPass(result.getString("pass"));
            user.setRole(result.getString("role"));
            user_list.add(user);
        }
        
        return user_list;
    }
    
    @Override
    public User selectById(int id) throws SQLException {
        Statement stmt = super.conn.createStatement();
        String query = String.format("SELECT * FROM users where id = %s", id);
        ResultSet result = stmt.executeQuery(query);
        
        if (result.next()){
            User user = new User();
            user.setId(result.getInt("id"));
            user.setName(result.getString("name"));
            user.setEmail(result.getString("email"));
            user.setPass(result.getString("pass"));
            user.setRole(result.getString("role"));
            return user;
        } else {
            return null;
        }
    }
    
    @Override
    public int update(Object object) throws SQLException {
        User user = (User) object;
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "UPDATE users SET name = '?', email = '?', pass = '?', role = '?' WHERE id = ?;",
                user.name,
                user.email,
                user.pass,
                user.role,
                user.id
        );
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int insert(Object object) throws SQLException{
        User user = (User) object;
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "INSERT INTO users(name, email, pass, role) VALUES ('%s','%s','%s','%s')",
                user.name,
                user.email,
                user.pass,
                user.role
        );
        return stmt.executeUpdate(query);
    }
    
    @Override
    public int deleteById(int id) throws SQLException {
        Statement stmt = super.conn.createStatement();
        String query = String.format(
                "DELETE FROM users WHERE id = ?",
                id
        );
        return stmt.executeUpdate(query);
    }
    
    public User login(User user) throws SQLException {
        PreparedStatement stmt = super.conn.prepareStatement("SELECT * FROM users WHERE email = ? and pass = ?");
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getPass());
        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            return this.selectById(result.getInt("id"));
        } else {
            return null;
        }
    }
}
