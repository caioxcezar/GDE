package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;
import util.DaoUtils;

/**
 *
 * @author ccezar
 */
public class ClienteDao {

    private static final ClienteDao INSTANCE = new ClienteDao();

    private ClienteDao() {
    }

    public static ClienteDao getINSTANCE(){
        return INSTANCE;
    }
    public void salvar(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO gde.clientes_tb "
                + "(cod_cli,nome_cli,telefone_cli,cnpj_cli,rua_cli,complemento_cli,numero_cli,bairro_cli,cidade_cli,cep_cli) "
                + "VALUES (?,'?','?','?','?','?',?,'?','?','?');";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cliente.getCodigo());
            p.setString(1, cliente.getNome());
            p.setString(2, cliente.getTelefone());
            p.setString(3, cliente.getCnpj());
            p.setString(4, cliente.getRua());
            p.setString(5, cliente.getComplemento());
            p.setInt(6, cliente.getNumero());
            p.setString(7, cliente.getBairro());
            p.setString(8, cliente.getCidade());
            p.setString(9, cliente.getCep());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public void apagar(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM clientes_tb WHERE cod_cli = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public Cliente get(int cod) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM clientes_tb WHERE cod_cli = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cod);
            rs = p.executeQuery();
            return instanciarCliente(rs);
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public void alterar(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE gde.clientes_tb "
                + "SET numero_cli=?,complemento_cli='?',rua_cli='?',cidade_cli='?',"
                + "bairro_cli='?',nome_cli='?',cnpj_cli='?',telefone_cli='?',cep_cli='?' "
                + "WHERE cod_cli=?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, cliente.getNumero());
            p.setString(1, cliente.getComplemento());
            p.setString(2, cliente.getRua());
            p.setString(3, cliente.getCidade());
            p.setString(4, cliente.getBairro());
            p.setString(5, cliente.getNome());
            p.setString(6, cliente.getCnpj());
            p.setString(7, cliente.getTelefone());
            p.setString(8, cliente.getCep());
            p.setInt(9, cliente.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
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
            DaoUtils.closeResources(conn, st);
        }
    }
    
    public ArrayList<Cliente> listar(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM clientes_tb WHERE cod_cli like '%?%'";
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, nome);
            rs = p.executeQuery();
            while (rs.next()) {
                clientes.add(instanciarCliente(rs));
            }
            return clientes;
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }
    
    private Cliente instanciarCliente(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getString("cnpj_cli"),
                rs.getString("nome_cli"),
                rs.getString("telefone_cli"),
                rs.getInt("numero_cli"),
                rs.getInt("cod_cli"),
                rs.getString("cep_cli"),
                rs.getString("rua_cli"),
                rs.getString("bairro_cli"),
                rs.getString("cidade_cli"),
                rs.getString("complemento_cli")
        );
    }
}
