package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.NotaFiscal;

/**
 *
 * @author ccezar
 */
public class NotaFiscalDao extends dao {

    private static final NotaFiscalDao INSTANCE = new NotaFiscalDao();

    private NotaFiscalDao() {
    }

    public static NotaFiscal get(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM notas_fiscais_tb WHERE cod_nota = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cod);
            rs = p.executeQuery();
            rs.next();
            return instanciarNotaFiscal(rs);
        } finally {
            closeResources(conn, p);
        }
    }
    public static int lastId() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(cod_nota) as max_cod FROM gde.notas_fiscais_tb");
            rs.next();
            return rs.getInt("max_cod");
        } finally {
            closeResources(conn, st);
        }
    }
    public static void salvar(NotaFiscal nota) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO notas_fiscais_tb "
                + "(cod_nota, data_nota, pedido_nota) "
                + "VALUES (?, ?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, nota.getCodigo());
            p.setDate(2, nota.getData());
            p.setInt(3, nota.getPedido().getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public static void alterar(NotaFiscal nota) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE notas_fiscais_tb "
                + "SET data_nota = ?, pedido_nota = ? "
                + "WHERE cod_nota = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setDate(1, nota.getData());
            p.setInt(2, nota.getPedido().getCodigo());
            p.setInt(3, nota.getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public static void apagar(NotaFiscal nota) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.notas_fiscais_tb WHERE cod_nota = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, nota.getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public static ArrayList<NotaFiscal> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<NotaFiscal> cargos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM notas_fiscais_tb");
            while (rs.next()) {
                cargos.add(instanciarNotaFiscal(rs));
            }
            return cargos;
        } finally {
            closeResources(conn, st);
        }
    }

    private static NotaFiscal instanciarNotaFiscal(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new NotaFiscal(
                rs.getInt("cod_nota"), 
                rs.getDate("data_nota"), 
                PedidoDao.get(rs.getInt("pedido_nota")));
    }
}
