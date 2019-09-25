package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Categoria;
import model.Produto;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class ProdutoDao {
    
    public static Produto get(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM produtos_tb WHERE cod_prod = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cod);
            rs = p.executeQuery();
            rs.next();
            return instanciarProd(rs);
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }
    
    public static void salvar(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO gde.produtos_tb "
                + "(cod_prod,nome_prod,decricao_prod,categoria_prod,valor_produto)\n"
                + "VALUES (?,'?','?',?,?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, produto.getCodigo());
            p.setString(1, produto.getNome());
            p.setString(2, produto.getDescricao());
            p.setInt(3, produto.getCategoria().getCodigo());
            p.setFloat(4, produto.getValor());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }
    
    public static void alterar(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE INTO gde.produtos_tb "
                + "SET nome_prod=?,decricao_prod=?,categoria_prod=?,valor_produto=?"
                + "WHERE cod_prod=?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, produto.getNome());
            p.setString(1, produto.getDescricao());
            p.setInt(2, produto.getCategoria().getCodigo());
            p.setFloat(3, produto.getValor());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }
    
    public static void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.produtos_tb WHERE cod_prod = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }
    
    public static ArrayList<Produto> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produtos_tb");
            while (rs.next()) {
                produtos.add(instanciarProd(rs));
            }
            return produtos;
        } finally {
            DaoUtils.closeResources(conn, st);
        }
    }
    
    public static ArrayList<Produto> listar(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.produtos_tb WHERE nome_prod like '%?%'";
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, nome);
            rs = p.executeQuery();
            while (rs.next()) {
                produtos.add(instanciarProd(rs));
            }
            return produtos;
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }
    
    private static Produto instanciarProd(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new Produto(
                rs.getInt("cod_prod"), 
                rs.getString("nome_prod"),
                rs.getString("descricao_prod"),
                CategoriaDao.get(rs.getInt("categoria_prod")),
                rs.getFloat("valor_produto")
        );
    }
}
