
package controller;



import java.sql.*;


public class ConnectDB {
    protected Connection conn;

    public ConnectDB() {

        String connUrl = "jdbc:sqlserver://DESKTOP-I4OVIU7\\TRANDOAN:1433;" + //localhost
                "user=sa;password=sa;databaseName=He_Thong_Truong_Mam_Non;encrypt=false"; //encrypt = false
        try{conn = DriverManager.getConnection(connUrl);
            System.out.println("Kết nối thành công SQL Server DataBase!");
            

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        new ConnectDB();
//    }
}
