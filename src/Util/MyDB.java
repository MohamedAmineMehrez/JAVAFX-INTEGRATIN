/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author EXTRA
 */
public class MyDB {
     private String url = "jdbc:mysql://localhost:3306/aaaa";
    private String username="root";
    private String password="";
    private Connection connection;
    static MyDB instance;
    public MyDB() {
        try {
            connection=DriverManager.getConnection(url,username,password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
}
    public static MyDB getInstance(){
        if(instance ==null) {
            instance = new MyDB(); 
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
     Connection conn = null;
    public static Connection conDB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aaaa", "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("MyDB : "+ex.getMessage());
           return null;
        }
    }
}
