package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.EstoqueProduto;
import model.PedidoProduto;
import util.DaoUtils;

/**
 *
 * @author caio.rezende
 */
public class EstoqueProdutoDao {
    
    public static void salvar(PedidoProduto pedidoProd) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO estoque_tb "
                + "(cod_estoque, quantidade_estoque, produto_estoque) "
                + "VALUES (?, ?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, pedidoProd.getCodigo());
            p.setInt(2, pedidoProd.getQuantidade());
            p.setInt(3, pedidoProd.getProduto().getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.estoque_tb WHERE cod_estoque = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cod);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static EstoqueProduto get(int codigo) throws SQLException, ClassNotFoundException {
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
            return instanciarEProduto(rs);
        } finally {
            util.DaoUtils.closeResources(conn, p);
        }
    }

    public static void alterar(PedidoProduto pproduto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE INTO gde.estoque_tb "
                + "SET quantidade_estoque = ?, produto_estoque = ?, produto_estoque = ?"
                + "WHERE cod_estoque = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(3, pproduto.getCodigo());
            p.setInt(1, pproduto.getQuantidade());
            p.setInt(2, pproduto.getProduto().getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static ArrayList<EstoqueProduto> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<EstoqueProduto> estoqueProdutos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM estoque_tb");
            while (rs.next()) {
                estoqueProdutos.add(instanciarEProduto(rs));
            }
            return estoqueProdutos;
        } finally {
            DaoUtils.closeResources(conn, st);
        }
    }
    
    public static ArrayList<EstoqueProduto> listar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM estoque_tb WHERE cod_estoque = ?";
        ArrayList<EstoqueProduto> estoqueProdutos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            rs = p.executeQuery();
            while (rs.next()) {
                estoqueProdutos.add(instanciarEProduto(rs));
            }
            return estoqueProdutos;
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    private static EstoqueProduto instanciarEProduto(ResultSet rs) throws SQLException, ClassNotFoundException {
                return new EstoqueProduto(
                rs.getInt("quantidade_estoque"), 
                rs.getInt("cod_estoque"), 
                ProdutoDao.get(rs.getInt("produto_estoque")));
    }
    
}
