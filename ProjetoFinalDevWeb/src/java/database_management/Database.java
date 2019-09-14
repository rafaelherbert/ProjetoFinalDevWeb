/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_management;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rafae
 */
public class Database {
    
    public Connection conn;

    public Database() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        this.conn = (Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/desenvolvimento_web",
                "dev_web",
                "Mamae#123"
        ); 
    }
    
}
