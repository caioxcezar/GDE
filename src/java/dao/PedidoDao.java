package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Pedido;

/**
 *
 * @author ccezar
 */
public class PedidoDao extends dao {

    public static Pedido get(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM pedidos_tb WHERE cod_pedido = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cod);
            rs = p.executeQuery();
            rs.next();
            return instanciarPedido(rs);
        } finally {
            closeResources(conn, p);
        }
    }

    public static void salvar(Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO pedidos_tb "
                + "(cod_pedido, funcionario_pedido, cliente_pedido, data_pedido, tipo_pedido, estado_pedido) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, pedido.getCodigo());
            p.setInt(2, pedido.getFuncionario().getCodigo());
            p.setInt(3, pedido.getCliente().getCodigo());
            p.setDate(4, pedido.getData());
            p.setString(5, pedido.getTipo());
            p.setString(6, pedido.getEstado());
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
            ResultSet rs = stmt.executeQuery("SELECT MAX(cod_pedido) as max_cod FROM gde.pedidos_tb");
            rs.next();
            return rs.getInt("max_cod");
        } finally {
            closeResources(conn, st);
        }
    }

    public static void alterar(Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE pedidos_tb "
                + "SET funcionario_pedido = ?, cliente_pedido = ?"
                + ", data_pedido = ?, tipo_pedido = ?, estado_pedido = ? "
                + "WHERE cod_pedido = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, pedido.getFuncionario().getCodigo());
            p.setInt(2, pedido.getCliente().getCodigo());
            p.setDate(3, pedido.getData());
            p.setString(4, pedido.getTipo());
            p.setString(5, pedido.getEstado());
            p.setInt(6, pedido.getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public static void apagar(Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.pedidos_tb WHERE cod_pedido = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, pedido.getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
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
            closeResources(conn, st);
        }
    }

    public static ArrayList<Pedido> listar(int codigo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement("SELECT * FROM pedidos_tb where cod_pedido = ?");
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                pedidos.add(instanciarPedido(rs));
            }
            return pedidos;
        } finally {
            closeResources(conn, p);
        }
    }

    public static ArrayList<Pedido> listarPendente() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pedidos_tb where estado_pedido = 'pendente'");
            while (rs.next()) {
                pedidos.add(instanciarPedido(rs));
            }
            return pedidos;
        } finally {
            closeResources(conn, st);
        }
    }

    public static ArrayList<Pedido> listarPendentesInterno() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pedidos_tb "
                    + "where estado_pedido = 'Pendente' "
                    + "and tipo_pedido = 'Interno'");
            while (rs.next()) {
                pedidos.add(instanciarPedido(rs));
            }
            return pedidos;
        } finally {
            closeResources(conn, st);
        }
    }

    public static ArrayList<Pedido> listarPendentesExterno() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pedidos_tb "
                    + "where estado_pedido = 'Pendente' "
                    + "and tipo_pedido = 'Externo'");
            while (rs.next()) {
                pedidos.add(instanciarPedido(rs));
            }
            return pedidos;
        } finally {
            closeResources(conn, st);
        }
    }

    private static Pedido instanciarPedido(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new Pedido(
                rs.getInt("cod_pedido"),
                PedidoProdutoDao.listar(rs.getInt("cod_pedido")),
                FuncionarioDao.get(rs.getInt("funcionario_pedido")),
                ClienteDao.INSTANCE.get(rs.getInt("cliente_pedido")),
                rs.getDate("data_pedido"),
                rs.getString("estado_pedido"),
                rs.getString("tipo_pedido"));
    }
}
