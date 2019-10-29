package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Categoria;

/**
 *
 * @author ccezar
 */
public class CategoriaDao extends dao {

    public static void salvar(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO categorias_tb "
                + "(cod_cat, nome_cat, descricao_cat) "
                + "VALUES (?, ?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, categoria.getCodigo());
            p.setString(2, categoria.getNome());
            p.setString(3, categoria.getDescricao());
            p.executeUpdate();
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
            ResultSet rs = stmt.executeQuery("SELECT MAX(cod_cat) as max_cod FROM gde.categorias_tb");
            rs.next();
            return rs.getInt("max_cod");
        } finally {
            closeResources(conn, st);
        }
    }

    public static void apagar(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.categorias_tb WHERE cod_cat = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, categoria.getCodigo());
            p.executeUpdate();
        } finally {
            closeResources(conn, p);
        }
    }

    public static Categoria get(int codigo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM categorias_tb WHERE cod_cat = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, codigo);
            rs = p.executeQuery();
            rs.next();
            return instanciarCategoria(rs);
        } finally {
            closeResources(conn, p);
        }
    }

    public static void alterar(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE gde.categorias_tb "
                + "SET nome_cat = ?, descricao_cat = ? "
                + "WHERE cod_cat = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, categoria.getNome());
            p.setString(2, categoria.getDescricao());
            p.setInt(3, categoria.getCodigo());
            p.executeUpdate();
        } finally {
            closeResources(conn, p);
        }
    }

    public static ArrayList<Categoria> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Categoria> categorias = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias_tb");
            while (rs.next()) {
                categorias.add(instanciarCategoria(rs));
            }
            return categorias;
        } finally {
            closeResources(conn, st);
        }
    }

    public static ArrayList<Categoria> listar(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM categorias_tb WHERE nome_cat like ?";
        ArrayList<Categoria> categorias = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, "%" + nome + "%");
            rs = p.executeQuery();
            while (rs.next()) {
                categorias.add(instanciarCategoria(rs));
            }
            return categorias;
        } finally {
            closeResources(conn, p);
        }
    }

    public static ArrayList<Categoria> listar(int codigo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM categorias_tb WHERE cod_cat = ?";
        ArrayList<Categoria> categorias = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, codigo);
            rs = p.executeQuery();
            while (rs.next()) {
                categorias.add(instanciarCategoria(rs));
            }
            return categorias;
        } finally {
            closeResources(conn, p);
        }
    }

    private static Categoria instanciarCategoria(ResultSet rs) throws SQLException {
        return new Categoria(
                rs.getInt("cod_cat"),
                rs.getString("nome_cat"),
                rs.getString("descricao_cat")
        );
    }
}
