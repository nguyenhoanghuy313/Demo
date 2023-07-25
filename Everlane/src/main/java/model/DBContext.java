package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    protected Connection connection;

    public DBContext() {
        try {
//            String user = "root";
//            String pass = "123456";
//            String url = "jdbc:mysql://localhost:3306/js_5_397?allowPublicKeyRetrieval=true&useSSL=false";
            String user = "linhndm1_397";
            String pass = "Test@123456";
            String url = "jdbc:mysql://ongbantat.store:3306/JS_5_397?allowPublicKeyRetrieval=true&useSSL=false";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
