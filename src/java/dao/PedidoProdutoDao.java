package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Pedido;
import model.PedidoProduto;

/**
 *
 * @author caio.rezende
 */
public class PedidoProdutoDao extends dao {

    public static void salvar(PedidoProduto pedidoProd, Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO pedido_produtos_tb "
                + "(qtd_pprod, produto_pprod, pedido_pprod) "
                + "VALUES (?, ?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, pedidoProd.getQuantidade());
            p.setInt(2, pedidoProd.getProduto().getCodigo());
            p.setInt(3, pedido.getCodigo());
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
            ResultSet rs = stmt.executeQuery("SELECT MAX(cod_pprod) as max_cod FROM gde.pedido_produtos_tb");
            rs.next();
            return rs.getInt("max_cod");
        } finally {
            closeResources(conn, st);
        }
    }

    public static void apagar(PedidoProduto pprod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.pedido_produtos_tb WHERE cod_pprod = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, pprod.getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public static PedidoProduto get(int codigo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.pedido_produtos_tb WHERE cod_pprod = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, codigo);
            rs = p.executeQuery();
            return instanciarPProduto(rs);
        } finally {
            closeResources(conn, p);
        }
    }

    public static void alterar(PedidoProduto pproduto, Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE gde.pedido_produtos_tb "
                + "SET pedido_pprod = ?, produto_pprod = ?, qtd_pprod = ? "
                + "WHERE cod_pprod = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, pedido.getCodigo());
            p.setInt(2, pproduto.getProduto().getCodigo());
            p.setInt(3, pproduto.getQuantidade());
            p.setInt(4, pproduto.getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM pedido_produtos_tb");
            while (rs.next()) {
                categorias.add(instanciarPProduto(rs));
            }
            return categorias;
        } finally {
            closeResources(conn, st);
        }
    }

    public static ArrayList<PedidoProduto> listar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM pedido_produtos_tb WHERE pedido_pprod = ?";
        ArrayList<PedidoProduto> categorias = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cod);
            rs = p.executeQuery();
            while (rs.next()) {
                categorias.add(instanciarPProduto(rs));
            }
            return categorias;
        } finally {
            closeResources(conn, p);
        }
    }

    private static PedidoProduto instanciarPProduto(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new PedidoProduto(
                rs.getInt("cod_pprod"),
                rs.getInt("qtd_pprod"),
                ProdutoDao.get(rs.getInt("produto_pprod")));
    }

}
