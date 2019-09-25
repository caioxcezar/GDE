package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Pedido;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class PedidoDao {

    public static Pedido get(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM pedidos_tb WHERE cod_pedido = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            rs = p.executeQuery();
            return instanciarPedido(rs);
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void salvar(Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO pedidos_tb "
                + "(cod_pedido, funcionario_pedido, cliente_pedido, valor_pedido, data_pedido, tipo_pedido) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, pedido.getCodigo());
            p.setInt(1, pedido.getFuncionario().getCodigo());
            p.setInt(2, pedido.getCliente().getCodigo());
            p.setDouble(3, pedido.getValor());
            p.setDate(4, pedido.getData());
            p.setString(5, pedido.getTipo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void alterar(Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE pedidos_tb "
                + "SET funcionario_pedido = '?', cliente_pedido = '?', valor_pedido = '?'"
                + ", data_pedido = '?', tipo_pedido = '?' "
                + "WHERE cod_pedido = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(5, pedido.getCodigo());
            p.setInt(0, pedido.getFuncionario().getCodigo());
            p.setInt(1, pedido.getCliente().getCodigo());
            p.setDouble(2, pedido.getValor());
            p.setDate(3, pedido.getData());
            p.setString(4, pedido.getTipo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.pedidos_tb WHERE cod_pedido = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static ArrayList<Pedido> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pedidos_tb");
            while (rs.next()) {
                pedidos.add(instanciarPedido(rs));
            }
            return pedidos;
        } finally {
            DaoUtils.closeResources(conn, st);
        }
    }

    private static Pedido instanciarPedido(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new Pedido(rs.getInt("cod_pedido"), 
                PedidoProdutoDao.listar(rs.getInt("cod_pedido")), 
                FuncionarioDao.get(rs.getInt("funcionario_pedido")), 
                ClienteDao.get(rs.getInt("cliente_pedido")), 
                rs.getFloat("valor_pedido"), 
                rs.getDate("data_pedido"), 
                rs.getString("estado_pedido"), 
                rs.getString("estado_pedido"));
    }
}
