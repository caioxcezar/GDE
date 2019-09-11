package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class ClienteDao {

    private static final ClienteDao INSTANCE = new ClienteDao();

    private ClienteDao() {
    }

    public void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.clientes_tb WHERE cod_cli = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public Cliente get(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM clientes_tb WHERE cod_cargo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            rs = p.executeQuery();
            return instanciarCliente(rs);
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    private Cliente instanciarCliente(ResultSet rs) throws SQLException {
        return new Cliente(
                    rs.getInt("cnpj_cli"),
                    rs.getString("nome_cli"),
                    rs.getString("telefone_cli"),
                    rs.getInt("numero_cli"),
                    rs.getInt("cod_cli"),
                    rs.getInt("cep_cli"),
                    rs.getString("rua_cli"),
                    rs.getString("bairro_cli"),
                    rs.getString("cidade_cli"),
                    rs.getString("complemento_cli")
            );
    }
}
