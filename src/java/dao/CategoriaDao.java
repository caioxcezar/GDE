package dao;

import dao.DataBaseLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Categoria;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class CategoriaDao {

    private static final CategoriaDao INSTANCE = new CategoriaDao();

    private CategoriaDao() {
    }

    public void salvar(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO gde.categorias_tb "
                + "(nome_cat,descricao_cat) "
                + "VALUES ('?','?');";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, categoria.getNome());
            p.setString(0, categoria.getDescricao());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public Categoria getCategoria(int codigo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM categoria_tb WHERE cod_cargo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, codigo);
            rs = p.executeQuery();
            return new Categoria(
                    rs.getInt("cod_cat"),
                    rs.getString("nome_cat"),
                    rs.getString("descricao_cat")
            );
        } finally {
            util.DaoUtils.closeResources(conn, p);
        }
    }
}
