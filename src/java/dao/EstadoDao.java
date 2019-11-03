package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Estado;

/**
 *
 * @author fundamental1
 */
public class EstadoDao extends dao {

    public static void salvar(Estado estado) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO gde.clientes_tb "
                + "(sigla_estado, nome_estado) "
                + "VALUES (?,?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, estado.getSigla());
            p.setString(2, estado.getNome());
            p.execute();
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
            ResultSet rs = stmt.executeQuery("SELECT MAX(sigla_estado) as max_cod FROM gde.estados_tb");
            rs.next();
            return rs.getInt("max_cod");
        } finally {
            closeResources(conn, st);
        }
    }
    
    public static void apagar(Estado estado) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM estados_tb WHERE sigla_estado = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, estado.getSigla());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }
    
    public static Estado get(String sigla) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM estados_tb WHERE sigla_estado = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, sigla);
            rs = p.executeQuery();
            rs.next();
            return instanciarEstado(rs);
        } finally {
            closeResources(conn, p);
        }
    }
    
    public static void alterar(Estado estado) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE gde.estados_tb "
                + "SET sigla_estado=?,nome_estado=? "
                + "WHERE sigla_estado=?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, estado.getSigla());
            p.setString(2, estado.getNome());
            p.setString(3, estado.getSigla());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }
    
    public static ArrayList<Estado> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Estado> estados = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM estados_tb");
            while (rs.next()) {
                estados.add(instanciarEstado(rs));
            }
            return estados;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public static ArrayList<Estado> listar(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM estados_tb WHERE nome_estado like '%?%'";
        ArrayList<Estado> estados = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, nome);
            rs = p.executeQuery();
            while (rs.next()) {
                estados.add(instanciarEstado(rs));
            }
            return estados;
        } finally {
            closeResources(conn, p);
        }
    }
    
    private static Estado instanciarEstado(ResultSet rs) throws SQLException {
        return new Estado(rs.getString("sigla_estado"),
                rs.getString("nome_estado"));
    }
}
