package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Funcionario;

/**
 *
 * @author ccezar
 */
public class FuncionarioDao extends dao {

    public static void salvar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO gde.funcionarios_tb "
                + "(cod_func,nome_func,telefone_func,cpf_func,rua_func,numero_func,"
                + "complemento_func,bairro_func,cep_func,cargo_func,estado_func,cidade_func, salario_func)\n"
                + "	VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, funcionario.getCodigo());
            p.setString(2, funcionario.getNome());
            p.setString(3, funcionario.getTelefone());
            p.setString(4, funcionario.getCpf());
            p.setString(5, funcionario.getRua());
            p.setInt(6, funcionario.getNumero());
            p.setString(7, funcionario.getComplemento());
            p.setString(8, funcionario.getBairro());
            p.setString(9, funcionario.getCep());
            p.setInt(10, funcionario.getCargo().getCodigo());
            p.setString(11, funcionario.getEstado().getSigla());
            p.setString(12, funcionario.getCidade());
            p.setFloat(13, funcionario.getSalario());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public static void apagar(Funcionario func) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.funcionarios_tb WHERE cod_func = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, func.getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public static ArrayList<Funcionario> listar() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM gde.funcionarios_tb");
            while (rs.next()) {
                funcionarios.add(instanciarFuncionario(rs));
            }
            return funcionarios;
        } finally {
            closeResources(conn, st);
        }
    }

    public static Funcionario get(int codFunc) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.funcionarios_tb WHERE cod_func = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, codFunc);
            rs = p.executeQuery();
            rs.next();
            return instanciarFuncionario(rs);
        } finally {
            closeResources(conn, p);
        }
    }

    public static void alterar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE gde.funcionarios_tb "
                + "SET nome_func = ?, telefone_func = ?, cpf_func = ?, rua_func = ?, "
                + "numero_func = ?, complemento_func = ?, bairro_func = ?, cep_func = ?, "
                + "cargo_func = ?, cidade_func = ?, salario_func = ? "
                + "WHERE cod_func = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1, funcionario.getNome());
            p.setString(2, funcionario.getTelefone());
            p.setString(3, funcionario.getCpf());
            p.setString(4, funcionario.getRua());
            p.setInt(5, funcionario.getNumero());
            p.setString(6, funcionario.getComplemento());
            p.setString(7, funcionario.getBairro());
            p.setString(8, funcionario.getCep());
            p.setInt(9, funcionario.getCargo().getCodigo());
            p.setString(10, funcionario.getCidade());
            p.setFloat(11, funcionario.getSalario());
            p.setInt(12, funcionario.getCodigo());
            p.execute();
        } finally {
            closeResources(conn, p);
        }
    }

    public static ArrayList<Funcionario> listar(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.funcionarios_tb WHERE nome_func like ?";
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(1,"%" + nome + "%");
            rs = p.executeQuery();
            while (rs.next()) {
                funcionarios.add(instanciarFuncionario(rs));
            }
            return funcionarios;
        } finally {
            closeResources(conn, p);
        }
    }

    public static ArrayList<Funcionario> listar(int codigo) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.funcionarios_tb WHERE cod_func = ?";
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(1, codigo);
            rs = p.executeQuery();
            while (rs.next()) {
                funcionarios.add(instanciarFuncionario(rs));
            }
            return funcionarios;
        } finally {
            closeResources(conn, p);
        }
    }

    public static ArrayList<Funcionario> listarVendedores() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM gde.funcionarios_tb f\n"
                    + "inner join gde.cargos_tb c\n"
                    + "on c.cod_cargo = f.cargo_func\n"
                    + "where c.nome_cargo = \"Vendedor\";");
            while (rs.next()) {
                funcionarios.add(instanciarFuncionario(rs));
            }
            return funcionarios;
        } finally {
            closeResources(conn, st);
        }
    }

    public static int lastId() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(cod_func) as max_cod FROM gde.funcionarios_tb");
            rs.next();
            return rs.getInt("max_cod");
        } finally {
            closeResources(conn, st);
        }
    }

    private static Funcionario instanciarFuncionario(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new Funcionario(rs.getString("cpf_func"),
                CargoDao.get(rs.getInt("cargo_func")),
                rs.getString("nome_func"),
                rs.getString("telefone_func"),
                rs.getInt("numero_func"),
                rs.getInt("cod_func"),
                rs.getString("cep_func"),
                rs.getString("rua_func"),
                rs.getString("bairro_func"),
                rs.getString("cidade_func"),
                rs.getString("complemento_func"),
                EstadoDao.get(rs.getString("estado_func")),
                rs.getFloat("salario_func")
        );
    }
}
