package dao.inter;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO {
    public static Connection connect() {
        try {
            //        Class.forName("com.mysql.jdbc.Driver");
//        com.mysql.jdbc.Driver driver;
String url = "jdbc:mysql://localhost:3306/resume";
String username = "root";
String password = "12345";
Connection connection = DriverManager.getConnection(url, username, password);
return connection;
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
