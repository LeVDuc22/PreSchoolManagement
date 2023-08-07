/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DbContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author My laptop
 */
public class DbContextFactory {
    private static final String ConnectString =  "jdbc:sqlserver://localhost:1433;databaseName=PreSchool;encrypt=true;trustServerCertificate=true";
    private static final String User ="sa";
    private  static  final  String Password ="123456";
    public  static Connection CreateConnect() throws SQLException {
        return DriverManager.getConnection(ConnectString,User,Password);
    }
}
