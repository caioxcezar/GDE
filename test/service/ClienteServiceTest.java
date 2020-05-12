package service;

import controller.ManterClienteController;
import dao.IClienteDao;
import java.sql.SQLException;
import model.Cliente;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.isA;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author maxz_
 */
public class ClienteServiceTest {

    ClienteService cs;
    ManterClienteController instance;

    @Before
    public void setUp() {
        cs = new ClienteService();
        cs.cliDao = createMock(IClienteDao.class);
    }

    @Test
    public void testIncluir() throws SQLException, ClassNotFoundException {
        expect(cs.cliDao.lastId()).andReturn(1);
        cs.cliDao.salvar(isA(Cliente.class));
        replay(cs.cliDao);
        assertEquals("Incluido com sucesso", cs.confirmarOperacao("incluir", "", new Cliente()));
    }

    @Test
    public void testAlterar() throws SQLException, ClassNotFoundException {
        cs.cliDao.alterar(isA(Cliente.class));
        replay(cs.cliDao);
        assertEquals("Alterado com sucesso", cs.confirmarOperacao("alterar", "1", new Cliente()));
    }

    @Test
    public void testExcluir() throws SQLException, ClassNotFoundException {
        cs.cliDao.apagar(isA(Cliente.class));
        replay(cs.cliDao);
        assertEquals("Excluido com sucesso", cs.confirmarOperacao("excluir", "1", new Cliente()));
    }

    @Test
    public void testCodigoInvalido() throws SQLException, ClassNotFoundException {
        expect(cs.cliDao.lastId()).andReturn(1);
        replay(cs.cliDao);
        assertEquals("Erro durante a operação: For input string: \"p\"", cs.confirmarOperacao("", "p", new Cliente()));
    }

    @Test
    public void testCodigoInvalido2() throws SQLException, ClassNotFoundException {
        assertEquals("", cs.confirmarOperacao("", "1", new Cliente()));
    }
}
