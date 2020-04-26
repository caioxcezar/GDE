package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author ccezar
 */
public class ClienteDao extends dao implements IClienteDao {

    public static final ClienteDao INSTANCE = new ClienteDao();
    
    private ClienteDao() { }
    
    @Override
    public void salvar(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO gde.clientes_tb "
                + "(cod_cli,nome_cli,telefone_cli,cnpj_cli,rua_cli,complemento_cli,numero_cli,bairro_cli,cidade_cli,cep_cli,estado_cli) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cliente.getCodigo());
            p.setString(2, cliente.getNome());
            p.setString(3, cliente.getTelefone());
            p.setString(4, cliente.getCnpj());
            p.setString(5, cliente.getRua());
            p.setString(6, cliente.getComplemento());
            p.setInt(7, cliente.getNumero());
            p.setString(8, cliente.getBairro());
            p.setString(9, cliente.getCidade());
            p.setString(10, cliente.getCep());
            p.setString(11, cliente.getEstado().getSigla());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    @Override
    public int lastId() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(cod_cli) as max_cod FROM gde.clientes_tb");
            rs.next();
            return rs.getInt("max_cod");
        } finally {
            closeResources(conn, st);
        }
    }

    @Override
    public void apagar(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM clientes_tb WHERE cod_cli = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cliente.getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    @Override
    public Cliente get(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM clientes_tb WHERE cod_cli = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cod);
            rs = p.executeQuery();
            rs.next();
            return instanciarCliente(rs);
        } finally {
            closeResources(conn, p);
        }
    }

    @Override
    public void alterar(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE gde.clientes_tb "
                + "SET numero_cli=?,complemento_cli=?,rua_cli=?,cidade_cli=?,"
                + "bairro_cli=?,nome_cli=?,cnpj_cli=?,telefone_cli=?,cep_cli=?,bairro_cli=?"
                + "WHERE cod_cli=?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cliente.getNumero());
            p.setString(2, cliente.getComplemento());
            p.setString(3, cliente.getRua());
            p.setString(4, cliente.getCidade());
            p.setString(5, cliente.getBairro());
            p.setString(6, cliente.getNome());
            p.setString(7, cliente.getCnpj());
            p.setString(8, cliente.getTelefone());
            p.setString(9, cliente.getCep());
            p.setString(10, cliente.getBairro());
            p.setInt(11, cliente.getCodigo());

            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public ArrayList<Cliente> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM clientes_tb");
            while (rs.next()) {
                clientes.add(instanciarCliente(rs));
            }
            return clientes;
        } finally {
            closeResources(conn, st);
        }
    }

    public ArrayList<Cliente> listar(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM clientes_tb WHERE nome_cli like ?";
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, "%" + nome + "%");
            rs = p.executeQuery();
            while (rs.next()) {
                clientes.add(instanciarCliente(rs));
            }
            return clientes;
        } finally {
            closeResources(conn, p);
        }
    }

    public ArrayList<Cliente> listar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM clientes_tb WHERE cod_cli = ?";
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, cod);
            rs = p.executeQuery();
            while (rs.next()) {
                clientes.add(instanciarCliente(rs));
            }
            return clientes;
        } finally {
            closeResources(conn, p);
        }
    }

    private Cliente instanciarCliente(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new Cliente(rs.getString("cnpj_cli"),
                rs.getString("nome_cli"),
                rs.getString("telefone_cli"),
                rs.getInt("numero_cli"),
                rs.getInt("cod_cli"),
                rs.getString("cep_cli"),
                rs.getString("rua_cli"),
                rs.getString("bairro_cli"), rs.getString("cidade_cli"),
                rs.getString("complemento_cli"),
                EstadoDao.get(rs.getString("estado_cli")));
    }
}
