package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Funcionario;
import util.DaoUtils;
/**
 *
 * @author ccezar
 */
public class FuncionarioDao {

    public static void salvar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "INSERT INTO gde.funcionarios_tb "
                + "(cod_func,nome_func,telefone_func,cpf_func,rua_func,numero_func,"
                + "complemento_func,bairro_func,cep_func,cargo_func)\n" +
"	VALUES (0,'0','0','0','0',0,'0','0','0',0);";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, funcionario.getCodigo());
            p.setString(1, funcionario.getNome());
            p.setString(2, funcionario.getTelefone());
            p.setString(3, funcionario.getCpf());
            p.setString(4, funcionario.getRua());
            p.setInt(5, funcionario.getNumero());
            p.setString(6, funcionario.getComplemento());
            p.setString(7, funcionario.getBairro());
            p.setString(8, funcionario.getCep());
            p.setInt(9, funcionario.getCargo().getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void apagar(int codFunc) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "DELETE FROM gde.funcionarios_tb WHERE cod_func = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setInt(0, codFunc);
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
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
            DaoUtils.closeResources(conn, st);
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
            p.setInt(0, codFunc);
            rs = p.executeQuery();
            return instanciarFuncionario(rs);
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static void alterar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        String sql = "UPDATE gde.funcionarios_tb "
                + "SET nome_func = '?', telefone_func = '?', cpf_func = '?', rua_func = '?', "
                + "numero_func = '?', complemento_func = '?', bairro_func = '?', cep_func = '?', cargo_func = '?' "
                + "WHERE cod_func = ?";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, funcionario.getNome());
            p.setString(1, funcionario.getTelefone());
            p.setString(2, funcionario.getCpf());
            p.setString(3, funcionario.getRua());
            p.setInt(4, funcionario.getNumero());
            p.setString(5, funcionario.getComplemento());
            p.setString(6, funcionario.getBairro());
            p.setString(7, funcionario.getCep());
            p.setInt(8, funcionario.getCargo().getCodigo());
            p.setInt(9, funcionario.getCodigo());
            p.execute();
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    public static ArrayList<Funcionario> listar(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM gde.funcionarios_tb WHERE cod_cargo like '%?%'";
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            p = conn.prepareStatement(sql);
            p.setString(0, nome);
            rs = p.executeQuery();
            while (rs.next()) {
                funcionarios.add(instanciarFuncionario(rs));
            }
            return funcionarios;
        } finally {
            DaoUtils.closeResources(conn, p);
        }
    }

    private static Funcionario instanciarFuncionario(ResultSet rs) throws SQLException, ClassNotFoundException {
        return new Funcionario(
                        rs.getString("cpf_func"),
                        CargoDao.get(rs.getInt("cargo_func")),
                        rs.getString("nome_func"),
                        rs.getString("telefone_func"),
                        rs.getInt("numero_func"),
                        rs.getInt("cod_func"),
                        rs.getString("cep_func"),
                        rs.getString("rua_func"),
                        rs.getString("bairro_func"),
                        rs.getString("cidade_func"),
                        rs.getString("complemento_func")
                );
    }
}
