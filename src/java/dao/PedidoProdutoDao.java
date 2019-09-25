package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.PedidoProduto;
import util.DaoUtils;

/**
 *
 * @author caio.rezende
 */
public class PedidoProdutoDao {
    
    public static void salvar(PedidoProduto pedidoProd) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO pedido_produto_tb "
                + "(cod_pprod, quantidade_pprod, produto_pprod, pedido_pprod) "
                + "VALUES (?, ?, ?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, pedidoProd.getCodigo());
            p.setInt(1, pedidoProd.getQuantidade());
            p.setInt(2, pedidoProd.getProduto().getCodigo());
            p.setInt(3, pedidoProd.getCodigoPedido());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.pedido_produto_tb WHERE cod_pprod = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static PedidoProduto get(int codigo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.pedido_produto_tb WHERE cod_pprod = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, codigo);
            rs = p.executeQuery();
            return instanciarPProduto(rs);
        } finally {
            util.DaoUtils.closeResources(conn, p);
        }
    }

    public static void alterar(PedidoProduto pproduto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE INTO gde.pedido_produto_tb "
                + "SET quantidade_pprod = ?, produto_pprod = ?, produto_pprod = ?, pedido_pprod = ? "
                + "WHERE cod_pprod = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(3, pproduto.getCodigo());
            p.setInt(0, pproduto.getQuantidade());
            p.setInt(1, pproduto.getProduto().getCodigo());
            p.setInt(2, pproduto.getCodigoPedido());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static ArrayList<PedidoProduto> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<PedidoProduto> categorias = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pedido_produto_tb");
            while (rs.next()) {
                categorias.add(instanciarPProduto(rs));
            }
            return categorias;
        } finally {
            DaoUtils.closeResources(conn, st);
        }
    }
    
    public static ArrayList<PedidoProduto> listar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM pedido_produto_tb WHERE cod_pprod = ?";
        ArrayList<PedidoProduto> categorias = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            rs = p.executeQuery();
            while (rs.next()) {
                categorias.add(instanciarPProduto(rs));
            }
            return categorias;
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }
    
    private static PedidoProduto instanciarPProduto(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new PedidoProduto(
                rs.getInt("quantidade_pprod"), 
                rs.getInt("cod_pprod"), 
                rs.getInt("pedido_pprod"), 
                ProdutoDao.get(rs.getInt("produto_pprod")));
    }
    
}
