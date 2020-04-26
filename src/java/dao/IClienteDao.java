package dao;

import java.sql.SQLException;
import model.Cliente;

/**
 *
 * @author caioc
 */
public interface IClienteDao {

    public int lastId() throws SQLException, ClassNotFoundException;

    public Cliente get(int cod) throws SQLException, ClassNotFoundException;

    public void salvar(Cliente cliente) throws SQLException, ClassNotFoundException;

    public void apagar(Cliente cliente) throws SQLException, ClassNotFoundException;

    public void alterar(Cliente cliente) throws SQLException, ClassNotFoundException;
}
