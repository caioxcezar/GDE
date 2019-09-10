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

    private static final CargoDao INSTANCE = new CargoDao();

    private CargoDao() {
    }

    public static CargoDao getInstance() {
        return INSTANCE;
    }

    public Cargo getCargo(int codCargo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cargo_tb WHERE cod_cargo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, codCargo);
            rs = p.executeQuery();
            return new Cargo(
                    rs.getInt("cod_cargo"),
                    rs.getString("nome_cargo"),
                    rs.getString("descricao_cargo")
            );
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public void salvar(Cargo cargo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO gde.funcionarios_tb "
                + "(nome_cargo,descricao_cargo) "
                + "VALUES ('?','?');";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, cargo.getNome());
            p.setString(1, cargo.getDescricao());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public void alterar(Cargo cargo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE gde.cargos_tb "
                + "SET cod_cargo = '?', nome_cargo = '?', descricao_cargo = '?' "
                + "WHERE cod_cargo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, cargo.getNome());
            p.setString(1, cargo.getDescricao());
            p.setInt(2, cargo.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public void apagar(int codCargo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.cargos_tb WHERE cod_cargo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, codCargo);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public ArrayList<Cargo> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Cargo> cargos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM gde.funcionarios_tb");
            while (rs.next()) {
                Cargo c = new Cargo(
                        rs.getInt("cod_cargo"),
                        rs.getString("nome_cargo"),
                        rs.getString("descricao")
                );
                cargos.add(c);
            }
            return cargos;
        } finally {
            DaoUtils.closeResources(conn, st);
        }
    }

    public ArrayList<Cargo> listar(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.funcionarios_tb WHERE cod_cargo like '%?%'";
        ArrayList<Cargo> cargos = new ArrayList<Cargo>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, nome);
            rs = p.executeQuery();
            while (rs.next()) {
                Cargo c = new Cargo(
                        rs.getInt("cod_cargo"),
                        rs.getString("nome_cargo"),
                        rs.getString("descricao")
                );
                cargos.add(c);
            }
            return cargos;
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }
}
