package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.NotaFiscal;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class NotaFiscalDao {

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
            p.setInt(0, cod);
            rs = p.executeQuery();
            return instanciarNotaFiscal(rs);
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void salvar(NotaFiscal nota) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO notas_fiscais_tb "
                + "(cod_nota, data_nota, venda_nota) "
                + "VALUES (?, '?', ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, nota.getCodigo());
            p.setDate(1, nota.getData());
            p.setInt(2, nota.getVenda().getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void alterar(NotaFiscal nota) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE notas_fiscais_tb "
                + "SET data_nota = ?, venda_nota = ? "
                + "WHERE cod_nota = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setDate(0, nota.getData());
            p.setInt(1, nota.getVenda().getCodigo());
            p.setInt(2, nota.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void apagar(int cod) throws SQLException, ClassNotFoundException {
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
            DaoUtils.closeResources(conn, st);
        }
    }

    private static NotaFiscal instanciarNotaFiscal(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new NotaFiscal(
                rs.getInt("cod_nota"), 
                rs.getDate("data_nota"), 
                VendaDao.get(rs.getInt("venda_nota")));
    }
}
