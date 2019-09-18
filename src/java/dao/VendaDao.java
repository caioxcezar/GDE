package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Venda;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class VendaDao {

    private static final VendaDao INSTANCE = new VendaDao();

    private VendaDao() {
    }

    public static VendaDao getINSTANCE() {
        return INSTANCE;
    }
    
    public Venda get(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM vendas_tb WHERE cod_venda = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            rs = p.executeQuery();
            return instanciarVenda(rs);
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public void salvar(Venda venda) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO vendas_tb "
                + "(cod_venda, data_venda, cliente_venda, pedido_venda, funcionario_venda, "
                + "pago, total_venda) "
                + "VALUES (?, '?', ?, ?, ?, ?, ?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, venda.getCodigo());
            p.setDate(1, venda.getData());
            p.setInt(2, venda.getCliente().getCodigo());
            p.setInt(3, venda.getPedido().getCodigo());
            p.setInt(4, venda.getFuncionario().getCodigo());
            p.setBoolean(5, venda.isPago());
            p.setFloat(6, venda.getTotal());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public void alterar(Venda venda) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE vendas_tb "
                + "data_venda = ?, cliente_venda = ?, pedido_venda ?, funcionario_venda = ?, "
                + "pago = ?, total_venda = ? "
                + "WHERE cod_venda = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setDate(0, venda.getData());
            p.setInt(1, venda.getCliente().getCodigo());
            p.setInt(2, venda.getPedido().getCodigo());
            p.setInt(3, venda.getFuncionario().getCodigo());
            p.setBoolean(4, venda.isPago());
            p.setFloat(5, venda.getTotal());
            p.setInt(6, venda.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.vendas_tb WHERE cod_venda = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public ArrayList<Venda> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Venda> vendas = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vendas_tb");
            while (rs.next()) {
                vendas.add(instanciarVenda(rs));
            }
            return vendas;
        } finally {
            DaoUtils.closeResources(conn, st);
        }
    }

    private Venda instanciarVenda(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new Venda(
                rs.getInt("cod_venda"),
                rs.getDate("data_venda"),
                ClienteDao.getINSTANCE().get(rs.getInt("cliente_venda")),
                PedidoDao.getINSTANCE().get(rs.getInt("pedido_venda")),
                FuncionarioDao.getINSTANCE().get(rs.getInt("funcionario_venda")),
                rs.getBoolean("pago"),
                rs.getFloat("total_venda")
        );
    }
}
