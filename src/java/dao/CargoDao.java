package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cargo;
import util.DaoUtils;
/**
 *
 * @author ccezar
 */
public class CargoDao {

    public static Cargo get(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cargos_tb WHERE cod_cargo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cod);
            rs = p.executeQuery();
            rs.next();
            return instanciarCargo(rs);
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void salvar(Cargo cargo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO cargos_tb "
                + "(cod_cargo, nome_cargo, descricao_cargo) "
                + "VALUES (?, '?', '?');";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cargo.getCodigo());
            p.setString(2, cargo.getNome());
            p.setString(3, cargo.getDescricao());
            p.executeUpdate();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void alterar(Cargo cargo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE cargos_tb "
                + "SET nome_cargo = '?', descricao_cargo = '?' "
                + "WHERE cod_cargo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, cargo.getNome());
            p.setString(2, cargo.getDescricao());
            p.setInt(3, cargo.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.cargos_tb WHERE cod_cargo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cod);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static ArrayList<Cargo> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Cargo> cargos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cargos_tb");
            while (rs.next()) {
                cargos.add(instanciarCargo(rs));
            }
            return cargos;
        } finally {
            DaoUtils.closeResources(conn, st);
        }
    }

    public static ArrayList<Cargo> listar(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.cargo WHERE nome_cargo like '%?%'";
        ArrayList<Cargo> cargos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, nome);
            rs = p.executeQuery();
            while (rs.next()) {
                cargos.add(instanciarCargo(rs));
            }
            return cargos;
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    private static Cargo instanciarCargo(ResultSet rs) throws SQLException {
        return new Cargo(
                        rs.getInt("cod_cargo"),
                        rs.getString("nome_cargo"),
                        rs.getString("descricao_cargo")
                );
    }
}
