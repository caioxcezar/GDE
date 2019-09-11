package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Produto;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class ProdutoDao {

    private static final ProdutoDao INSTANCE = new ProdutoDao();

    private ProdutoDao() {
    }

    public Produto get(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM produtos_tb WHERE cod_prod = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            rs = p.executeQuery();
            return new Produto(
                    rs.getInt("cod_prod"),
                    rs.getString("nome_prod"),
                    rs.getString("descricao_prod"),
                    rs.getString("categoria_prod")
            );
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }
}
