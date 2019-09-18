package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import model.Pedido;
import model.Produto;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class PedidoDao {

    private static final PedidoDao INSTANCE = new PedidoDao();

    private PedidoDao() {
    }

    public static PedidoDao getINSTANCE() {
        return INSTANCE;
    }

    public Pedido get(int cod) throws SQLException, ClassNotFoundException {
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

    public void salvar(Cargo cargo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO cargos_tb "
                + "(cod_cargo, nome_cargo, descricao_cargo) "
                + "VALUES (?, '?', '?');";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cargo.getCodigo());
            p.setString(1, cargo.getNome());
            p.setString(2, cargo.getDescricao());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public void alterar(Cargo cargo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE cargos_tb "
                + "SET nome_cargo = '?', descricao_cargo = '?' "
                + "WHERE cod_cargo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, cargo.getNome());
            p.setString(1, cargo.getDescricao());
            p.setInt(2, cargo.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public void apagar(int codCargo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.cargos_tb WHERE cod_cargo = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, codCargo);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public ArrayList<Cargo> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Cargo> cargos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cargos_tb");
            while (rs.next()) {
                cargos.add(instanciarCargo(rs));
            }
            return cargos;
        } finally {
            DaoUtils.closeResources(conn, st);
        }
    }

    public ArrayList<Pedido> listar(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.funcionarios_tb WHERE cod_cargo like '%?%'";
        ArrayList<Pedido> cargos = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, nome);
            rs = p.executeQuery();
            while (rs.next()) {
                cargos.add(instanciarPedido(rs));
            }
            return cargos;
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    private Pedido instanciarPedido(ResultSet rs) throws SQLException, ClassNotFoundException {
        ArrayList<Produto> produtos = new ArrayList<>();
        Pedido pedido = new Pedido();
        pedido.setCodigo(rs.getInt("cod_pedido"));
        pedido.setData(rs.getDate("data_pedido"));
        pedido.setCliente(ClienteDao.getINSTANCE().get(rs.getInt("cliente_pedido")));
        pedido.setFuncionario(FuncionarioDao.getINSTANCE().get(rs.getInt("funcionario_pedido")));
        pedido.setEstado(rs.getString("estado"));
        pedido.setTipo(rs.getString("tipo_pedido"));
        while (rs.next()) {
            
        }
        Set<Pedido> produtos = new HashSet(ProdutoDao.getINSTANCE().listar(rs.getInt("produto_pedido")));
        return new Pedido(produtos, cliente, 0, data);
    }
}
