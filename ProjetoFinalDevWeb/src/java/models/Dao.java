/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Connection;
import database.Database;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rafae
 */
abstract class Dao extends Database{
    
    Connection conn;

    public Dao() throws SQLException, ClassNotFoundException {
        this.conn = super.conn;
    }
    
    abstract List getAll() throws SQLException;
    abstract Object selectById(int id) throws SQLException;
    abstract int update(Object object) throws SQLException;
    abstract int insert(Object object) throws SQLException;
    abstract int deleteById(int id) throws SQLException;
}
