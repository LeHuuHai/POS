
package application.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
    public static Connection getConnection(){
        Connection c = null;
        String url = "jdbc:sqlserver://DESKTOP-H3LD22V\\SQLEXPRESS;databaseName=PJ1;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String password = "123456";
        try {
            c = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    public static void closeConnection(Connection c){
        try {
            if(c!=null){
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

