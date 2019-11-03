package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ccezar
 */
public class DataBaseLocator {

    private final static DataBaseLocator INSTANCE = new DataBaseLocator();

    public static DataBaseLocator getInstance() {
        return INSTANCE;
    }

    private DataBaseLocator() {
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gde", "gde", "");
        return conn;
    }
    
    private void closeResources(Connection conn, Statement st) {
        try {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {

        }
    }
}
