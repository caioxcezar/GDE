package util;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author ccezar
 */
public class DaoUtils {
        public static void closeResources(Connection conn, Statement st) {
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
