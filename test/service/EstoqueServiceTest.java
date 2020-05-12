package service;

import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author maxz_
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstoqueServiceTest {

    EstoqueService cs;

    @Before
    public void setUp() {
        cs = new EstoqueService();
    }

    @Test
    public void test1Incluir() throws SQLException, ClassNotFoundException {
        assertEquals("Estoque incluido com sucesso", cs.confirmarOperacao("incluir", "", "1"));
    }

    @Test
    public void test2Incluir() throws SQLException, ClassNotFoundException {
        assertEquals("Estoque incluido com sucesso", cs.confirmarOperacao("incluir", "", "2"));
    }

    @Test
    public void test3Incluir() throws SQLException, ClassNotFoundException {
        assertEquals("Estoque incluido com sucesso", cs.confirmarOperacao("incluir", "2", "2"));
    }

    @Test
    public void test4Excluir() throws SQLException, ClassNotFoundException {
        assertEquals("Estoque excluido com sucesso", cs.confirmarOperacao("excluir", "1", "1"));
    }

    @Test
    public void test5Visualizar() throws SQLException, ClassNotFoundException {
        assertEquals("É uma visualização, nada a fazer por aqui", cs.confirmarOperacao("visualizar", "2", "2"));
    }

    @Test
    public void test6CodigoInvalido() throws SQLException, ClassNotFoundException {
        assertEquals("Erro durante a operação: For input string: \"p\"", cs.confirmarOperacao("incluir", "p", ""));
    }
}
