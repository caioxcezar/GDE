package dao;

import dao.DataBaseLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.categorias_tb WHERE cod_cat = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public Categoria get(int codigo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM categoria_tb WHERE cod_cargo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, codigo);
            rs = p.executeQuery();
            return instanciarCategoria(rs);
        } finally {
            util.DaoUtils.closeResources(conn, p);
        }
    }

    public void alterar(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE gde.categorias_tb "
                + "SET cod_cat = '?', nome_cat = '?', descricao_cat = '?' "
                + "WHERE cod_cat = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, categoria.getCodigo());
            p.setString(1, categoria.getNome());
            p.setString(2, categoria.getDescricao());
            p.setInt(3, categoria.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public ArrayList<Categoria> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Categoria> categorias = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM gde.funcionarios_tb");
            while (rs.next()) {
                categorias.add(instanciarCategoria(rs));
            }
            return categorias;
        } finally {
            DaoUtils.closeResources(conn, st);
        }
    }
    
    public ArrayList<Categoria> listar(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.categorias_tb WHERE cod_cat like '%?%'";
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, nome);
            rs = p.executeQuery();
            while (rs.next()) {
                categorias.add(instanciarCategoria(rs));
            }
            return categorias;
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    private Categoria instanciarCategoria(ResultSet rs) throws SQLException {
        return new Categoria(
                        rs.getInt("cod_cargo"),
                        rs.getString("nome_cargo"),
                        rs.getString("descricao")
                );
    }
}
