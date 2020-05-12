package service;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author caioc
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PedidoServiceTest {

    PedidoService ps;

    @Before
    public void setUp() {
        ps = new PedidoService();
    }

    @Test
    public void test01SemProduto() {
        assertEquals("Erro durante a operação: Por favor escolha um produto",
                ps.confirmarOperacao("incluir", "", "", "Interno", "1", "1", "Pendente"));
    }

    @Test
    public void test02IncluidoComSucessoInterno() {
        assertEquals("Incluido com sucesso",
                ps.confirmarOperacao("incluir", "", "1,2;", "Interno", "1", "1", "Pendente"));
    }

    @Test
    public void test03ErroQtdMaior() {
        assertEquals("Erro durante a operação: Produto HD 2TB indisponivel nessa quantidade, por favor fazer pedido interno",
                ps.confirmarOperacao("incluir", "", "1,3;", "Externo", "1", "2", "Pendente"));
    }

    @Test
    public void test04PedidoExternoErro() {
        assertEquals("Erro durante a operação: Produto HD 2TB indisponivel nessa quantidade, por favor fazer pedido interno",
                ps.confirmarOperacao("alterar", "6", "1,3;", "Externo", "1", "2", "Pendente"));
    }

    @Test
    public void test05IncluidoComSucessoExterno() {
        assertEquals("Incluido com sucesso",
                ps.confirmarOperacao("incluir", "", "1,2;", "Externo", "1", "2", "Pendente"));
    }

    @Test
    public void test06Alterado() {
        assertEquals("Alterado com sucesso",
                ps.confirmarOperacao("alterar", "5", "1,0;", "Externo", "1", "2", "Pendente"));
    }

    @Test
    public void test07OperacaoInvalida() {
        assertEquals("Erro durante a operação: Por favor escolha uma operação valida",
                ps.confirmarOperacao("", "", "1,2", "Externo", "1", "1", "Pendente"));
    }

    @Test
    public void test08Excluido() {
        assertEquals("Excluido com sucesso",
                ps.confirmarOperacao("excluir", "6", "1,2;", "Externo", "1", "2", "Pendente"));
    }

    @Test
    public void test09CodigoInvalido() {
        assertEquals("Erro durante a operação: For input string: \"e\"",
                ps.confirmarOperacao("", "e", "", "Interno", "1", "1", "Pendente"));
    }

    @Test
    public void test10OperacaoInvalida() {
        assertEquals("Erro durante a operação: Por favor escolha uma operação valida",
                ps.confirmarOperacao("", "", "1,2", "Interno", "1", "1", "Pendente"));
    }

}
