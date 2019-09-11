package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class PedidoDao {
    private static final PedidoDao INSTANCE = new PedidoDao();

    private PedidoDao() {
    }
            public void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.pedidos_tb WHERE cod_pedido = ?";
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
