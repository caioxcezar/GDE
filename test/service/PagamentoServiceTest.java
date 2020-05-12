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
public class PagamentoServiceTest {
    
    PagamentoService cs;

    @Before
    public void setUp() {
        cs = new PagamentoService();
    }

    @Test
    public void test1Incluir() throws SQLException, ClassNotFoundException {
        assertEquals("Incluido com sucesso", cs.confirmarOperacao("incluir", "", "3"));
    }

    @Test
    public void test2Alterar() throws SQLException, ClassNotFoundException {
        assertEquals("É uma visualização, nada a fazer por aqui", cs.confirmarOperacao("visualizar", "1", "3"));
    }

    @Test
    public void test3Excluir() throws SQLException, ClassNotFoundException {
        assertEquals("Excluido com sucesso", cs.confirmarOperacao("excluir", "1", "3"));
    }

    @Test
    public void test4CodigoInvalido() throws SQLException, ClassNotFoundException {
        assertEquals("Erro durante a operação: For input string: \"s\"", cs.confirmarOperacao("", "s", "3"));
    }

    @Test
    public void test5CodigoInvalido2() throws SQLException, ClassNotFoundException {
        assertEquals("", cs.confirmarOperacao("", "", "3"));
    }
}
