package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

public class UserDAO {
    public static Connection openConnection() {
        Connection conn = null;
        try {
            System.out.println("Hello from DBConfig.driver " + DBconfig.driver);
            Class.forName(DBconfig.driver);
            conn = DriverManager.getConnection(DBconfig.url, DBconfig.user, DBconfig.password);
            System.out.println("connected successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    public static User handleLogin(String username, String password) {
        try ( Connection c = openConnection()) {
            String select = String.format("select * from users where username = '%s' and passwordd = '%s'", username, password);
            PreparedStatement ps = c.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("passwordd"));
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean insertUser(User user) {
        try ( Connection c = openConnection()) {
            String insert = String.format("INSERT INTO USERS(username, passwordd) VALUES ('%s', '%s');", user.getUsername(), user.getPassword());
            PreparedStatement ps = c.prepareStatement(insert);
            int row = ps.executeUpdate(insert);
            return row >= 1 ? true : false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = openConnection();
    }
}
