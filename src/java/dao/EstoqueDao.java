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
                + "(cod_estoque, produto_estoque, quantidade_estoque, data_estoque, pedido_estoque, data_alteracao) "
                + "VALUES (?, ?, ?, ?, ?, NULL);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, estoque.getCodigo());
            p.setInt(2, estoque.getProduto().getCodigo());
            p.setInt(3, estoque.getQuantidade());
            p.setDate(4, estoque.getData());
            p.setInt(5, estoque.getPedido().getCodigo());
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
                + "SET produto_estoque = ?, quantidade_estoque = ?, data_estoque = ?, pedido_estoque = ?, data_alteracao = ?"
                + "WHERE cod_estoque = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, estoque.getProduto().getCodigo());
            p.setInt(2, estoque.getQuantidade());
            p.setDate(3, estoque.getData());
            p.setInt(4, estoque.getPedido().getCodigo());
            p.setDate(5, estoque.getDataAlteracao());
            p.setInt(6, estoque.getCodigo());
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

    public static ArrayList<Estoque> listar(int codigo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        ArrayList<Estoque> itensEstoque = new ArrayList<>();
        String sql = "SELECT * FROM estoque_tb where cod_estoque = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, codigo);
            rs = p.executeQuery();
            while (rs.next()) {
                itensEstoque.add(instanciarEstoque(rs));
            }
            return itensEstoque;
        } finally {
            closeResources(conn, p);
        }
    }
    /***
     * Listar produtos em estoque pelo codigo do produto
     * @param produto
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static ArrayList<Estoque> listarCodProduto(Produto produto) throws SQLException, ClassNotFoundException {
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

    public static ArrayList<Estoque> listarNomeProduto(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.estoque_tb e\n"
                + "inner join gde.produtos_tb p\n"
                + "on e.produto_estoque = p.cod_prod\n"
                + "WHERE p.nome_prod = ?";
        ArrayList<Estoque> produtos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, produto.getNome());
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
                ProdutoDao.get(rs.getInt("produto_estoque")),
                rs.getDate("data_estoque"),
                PedidoDao.get(rs.getInt("pedido_estoque")),
                rs.getDate("data_alteracao")
        );
    }

}
