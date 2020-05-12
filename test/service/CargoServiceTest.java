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
public class CargoServiceTest {
    
    CargoService cs;

    @Before
    public void setUp() {
        cs = new CargoService();
    }

    @Test
    public void test1Incluir() throws SQLException, ClassNotFoundException {
        assertEquals("Incluido com sucesso", cs.confirmarOperacao("incluir", "", "CEO", "Chefia"));
    }

    @Test
    public void test2Alterar() throws SQLException, ClassNotFoundException {
        assertEquals("Alterado com sucesso", cs.confirmarOperacao("alterar", "3", "CEO", "Chefia"));
    }

    @Test
    public void test3Excluir() throws SQLException, ClassNotFoundException {
        assertEquals("Excluido com sucesso", cs.confirmarOperacao("excluir", "3", "CEO", "Chefia"));
    }

    @Test
    public void test4CodigoInvalido() throws SQLException, ClassNotFoundException {
        assertEquals("Erro durante a operação: For input string: \"T\"", cs.confirmarOperacao("excluir", "T", "DHO", "DHO"));
    }

    @Test
    public void test5CodigoInvalido2() throws SQLException, ClassNotFoundException {
        assertEquals("", cs.confirmarOperacao("", "1", "Vendedor", "Vendedor"));
    }
}
