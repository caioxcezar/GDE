package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class NotaFiscalDao {

    private static final NotaFiscalDao INSTANCE = new NotaFiscalDao();

    private NotaFiscalDao() {
    }

    public void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.notas_fiscais_tb WHERE cod_nota = ?";
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
