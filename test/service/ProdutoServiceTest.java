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
public class ProdutoServiceTest {

    ProdutoService ps;

    @Before
    public void setUp() {
        ps = new ProdutoService();
    }

    @Test
    public void test1Incluir() throws SQLException, ClassNotFoundException {
        assertEquals("Incluido com sucesso",
                ps.confirmarOperacao("incluir", "", "Teclado", "100.00", "1", "USB"));
    }

    @Test
    public void test2Alterar() throws SQLException, ClassNotFoundException {
        assertEquals("Alteraco com sucesso",
                ps.confirmarOperacao("alterar", "3", "Teclado", "100.00", "1", "USB"));
    }

    @Test
    public void test3Excluir() throws SQLException, ClassNotFoundException {
        assertEquals("Excluido com sucesso",
                ps.confirmarOperacao("excluir", "3", "Teclado", "80.00", "1", "USB"));
    }

    @Test
    public void test4CodigoInvalido() throws SQLException, ClassNotFoundException {
        assertEquals("Erro durante a operação: For input string: \"S\"",
                ps.confirmarOperacao("", "S", "Teclado", "80.00", "1", "USB"));
    }

    @Test
    public void test5CodigoInvalido2() throws SQLException, ClassNotFoundException {
        assertEquals("",
                ps.confirmarOperacao("", "", "Teclado", "80.00", "1", "USB"));
    }
}
