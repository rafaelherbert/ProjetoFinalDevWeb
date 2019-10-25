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
public class UsersDao extends Dao {

    public UsersDao() throws SQLException, ClassNotFoundException {}

    @Override
    public List getAll() throws SQLException {
        List user_list = new ArrayList();
        PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM user");
        ResultSet result = sql.executeQuery();

        while (result.next()) {
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

        while (result.next()) {
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
        PreparedStatement sql = super.conn.prepareStatement("SELECT * FROM user WHERE id = ?");
        //String query = String.format("SELECT * FROM users where id = %s", id);
        sql.setInt(1, id);
        ResultSet result = sql.executeQuery();
        User user = new User();
        if (result.next()) {

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

    /*  Product product = (Product) object;
        PreparedStatement sql = super.conn.prepareStatement("UPDATE products SET name = ?, quantity = ?, description = ?,  price = ?, img_url = ?, brand = ?, category = ? WHERE id = ?;");
        sql.setString(1, product.getName());
        sql.setInt(2, product.getQuantity());
        sql.setString(3, product.getDescription());
        sql.setDouble(4, product.getPrice());
        sql.setString(5, product.getImg_url());
        sql.setString(6, product.getBrand());
        sql.setString(7, product.getCategory());
        sql.setInt(8, product.getId());
        return sql.executeUpdate();
    }
    */
    @Override
    public int update(Object object) throws SQLException {
        User user = (User) object;
        PreparedStatement sql = super.conn.prepareStatement("UPDATE users SET name = '?', email = '?', pass = '?', role = '?' WHERE id = ?;");
        sql.setString(1, user.getName());
        sql.setString(2, user.getEmail());
        sql.setString(3, user.getPass());
        sql.setString(4, user.getRole());
        sql.setInt(5, user.getId());
        return sql.executeUpdate();


    }

    @Override
    public int insert(Object object) throws SQLException {
        User user = (User) object;
        PreparedStatement sql = super.conn.prepareStatement("INSERT INTO users(name, email, pass, role) VALUES (?,?,?,?)");


        sql.setString(1, user.getName());
        sql.setString(2, user.getEmail());
        sql.setString(3, user.getPass());
        sql.setString(4, user.getRole());

        return sql.executeUpdate();
    }

    @Override
    public int deleteById(int id) throws SQLException {

        PreparedStatement sql = super.conn.prepareStatement("DELETE FROM users WHERE id = ?");

        sql.setInt(1, id);

        return sql.executeUpdate();
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