package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Estoque;
import model.Produto;

/**
 *
 * @author caio.rezende
 */
public class EstoqueDao extends dao {

    public static void salvar(Estoque estoque) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO estoque_tb "
                + "(cod_estoque, produto_estoque, quantidade_estoque) "
                + "VALUES (?, ?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, estoque.getCodigo());
            p.setInt(2, estoque.getProduto().getCodigo());
            p.setInt(3, estoque.getQuantidade());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public static void apagar(Estoque estoque) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.estoque_tb WHERE cod_estoque = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, estoque.getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public static Estoque get(int codigo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.estoque_tb WHERE cod_estoque = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, codigo);
            rs = p.executeQuery();
            rs.next();
            return instanciarEstoque(rs);
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
            ResultSet rs = stmt.executeQuery("SELECT MAX(cod_estoque) as max_cod FROM gde.estoque_tb");
            rs.next();
            return rs.getInt("max_cod");
        } finally {
            closeResources(conn, st);
        }
    }

    public static void alterar(Estoque estoque) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE gde.estoque_tb "
                + "SET produto_estoque = ?, quantidade_estoque = ? "
                + "WHERE cod_estoque = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, estoque.getProduto().getCodigo());
            p.setInt(2, estoque.getQuantidade());
            p.setInt(3, estoque.getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public static ArrayList<Estoque> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Estoque> itensEstoque = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM estoque_tb");
            while (rs.next()) {
                itensEstoque.add(instanciarEstoque(rs));
            }
            return itensEstoque;
        } finally {
            closeResources(conn, st);
        }
    }

    public static ArrayList<Estoque> listarPorProduto(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.estoque_tb e\n"
                + "inner join gde.produtos_tb p\n"
                + "on e.produto_estoque = p.cod_prod\n"
                + "WHERE p.cod_prod = ?";
        ArrayList<Estoque> produtos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, produto.getCodigo());
            rs = p.executeQuery();
            while (rs.next()) {
                produtos.add(instanciarEstoque(rs));
            }
            return produtos;
        } finally {
            closeResources(conn, p);
        }
    }

    private static Estoque instanciarEstoque(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new Estoque(
                rs.getInt("cod_estoque"),
                rs.getInt("quantidade_estoque"),
                ProdutoDao.get(rs.getInt("produto_estoque")));
    }

}
