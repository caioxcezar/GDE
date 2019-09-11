package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class VendaDao {

    private static final VendaDao INSTANCE = new VendaDao();

    private VendaDao() {
    }
        public void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.vendas_tb WHERE cod_venda = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }
}
