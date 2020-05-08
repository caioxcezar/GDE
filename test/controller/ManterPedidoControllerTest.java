package controller;

import dao.EstoqueDao;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Estoque;
import org.easymock.Capture;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.newCapture;
import static org.easymock.EasyMock.replay;
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
public class ManterPedidoControllerTest {

    HttpServletRequest request;
    HttpServletResponse response;
    ManterPedidoController instance;
    Capture<String> strArgument;

    @Before
    public void setUp() {
        request = createMock(HttpServletRequest.class);
        response = createMock(HttpServletResponse.class);
        instance = new ManterPedidoController();
        strArgument = newCapture();
    }

    @Test
    public void test1ConfirmarOperacao_erroProduto() throws UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("UTF-8");

        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("incluir");
        expect(request.getParameter("inputCodigo")).andReturn("");
        expect(request.getParameter("hiddenProdutos")).andReturn("");
        expect(request.getParameter("inputTipo")).andReturn("Interno");
        expect(request.getParameter("inputFuncionario")).andReturn("1");
        expect(request.getParameter("inputCliente")).andReturn("1");
        expect(request.getParameter("inputEstado")).andReturn("Pendente");

        replay(request);
        replay(response);

        try {
            instance.processRequest(request, response);
        } catch (ServletException ex) {
            assertEquals("Erro durante a operação: Por favor escolha um produto", ex.getMessage());
        }
    }

    @Test
    public void test2ConfirmarOperacao_inclusao() throws UnsupportedEncodingException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));

        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("incluir");
        expect(request.getParameter("inputCodigo")).andReturn("");
        expect(request.getParameter("hiddenProdutos")).andReturn("1,2;");
        expect(request.getParameter("inputTipo")).andReturn("Interno");
        expect(request.getParameter("inputFuncionario")).andReturn("1");
        expect(request.getParameter("inputCliente")).andReturn("1");
        expect(request.getParameter("inputEstado")).andReturn("Pendente");

        replay(request);
        replay(response);

        instance.processRequest(request, response);
        assertEquals("/pedidos", strArgument.toString());
    }

    @Test
    public void test3ConfirmarOperacao_venda_maior_estoque() throws UnsupportedEncodingException, IOException, ServletException, SQLException, ClassNotFoundException {
        request.setCharacterEncoding("UTF-8");

        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("incluir");
        expect(request.getParameter("inputCodigo")).andReturn("");
        Estoque estoque = EstoqueDao.get(1);
        int maxEstoque = estoque.getQuantidade() + 1;
        expect(request.getParameter("hiddenProdutos")).andReturn("1,"+maxEstoque+";");
        expect(request.getParameter("inputTipo")).andReturn("Externo");
        expect(request.getParameter("inputFuncionario")).andReturn("1");
        expect(request.getParameter("inputCliente")).andReturn("2");
        expect(request.getParameter("inputEstado")).andReturn("Pendente");

        replay(request);
        replay(response);

        try {
            instance.processRequest(request, response);
        } catch (ServletException ex) {
            assertEquals("Erro durante a operação: Produto HD 2TB indisponivel nessa quantidade, por favor fazer pedido interno", ex.getMessage());
        }
    }

    @Test
    public void test4ConfirmarOperacao_venda_igual_estoque() throws UnsupportedEncodingException, IOException, ServletException, SQLException, ClassNotFoundException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));

        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("incluir");
        expect(request.getParameter("inputCodigo")).andReturn("");
        Estoque estoque = EstoqueDao.get(1);
        int maxEstoque = estoque.getQuantidade();
        expect(request.getParameter("hiddenProdutos")).andReturn("1,"+maxEstoque+";");
        expect(request.getParameter("hiddenProdutos")).andReturn("1,2;");
        expect(request.getParameter("inputTipo")).andReturn("Externo");
        expect(request.getParameter("inputFuncionario")).andReturn("1");
        expect(request.getParameter("inputCliente")).andReturn("2");
        expect(request.getParameter("inputEstado")).andReturn("Pendente");

        replay(request);
        replay(response);

        instance.processRequest(request, response);
        assertEquals("/pedidos", strArgument.toString());
    }

    @Test
    public void test5ConfirmarOperacao_alterar_maior_estoque() throws UnsupportedEncodingException, IOException, ServletException, SQLException, ClassNotFoundException {
        request.setCharacterEncoding("UTF-8");

        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("alterar");
        expect(request.getParameter("inputCodigo")).andReturn("6");
        Estoque estoque = EstoqueDao.get(1);
        int maxEstoque = estoque.getQuantidade()+1;
        expect(request.getParameter("hiddenProdutos")).andReturn("1,"+maxEstoque+";");
        expect(request.getParameter("inputTipo")).andReturn("Externo");
        expect(request.getParameter("inputFuncionario")).andReturn("1");
        expect(request.getParameter("inputCliente")).andReturn("2");
        expect(request.getParameter("inputEstado")).andReturn("Pendente");

        replay(request);
        replay(response);

        try {
            instance.processRequest(request, response);
        } catch (ServletException ex) {
            assertEquals("Erro durante a operação: Produto HD 2TB indisponivel nessa quantidade, por favor fazer pedido interno", ex.getMessage());
        }
    }

   @Test
    public void test6ConfirmarOperacao_alterar_igual_estoque() throws UnsupportedEncodingException, IOException, ServletException, SQLException, ClassNotFoundException  {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));

        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("alterar");
        expect(request.getParameter("inputCodigo")).andReturn("5").andReturn("5");
        Estoque estoque = EstoqueDao.get(1);
        int maxEstoque = estoque.getQuantidade();
        expect(request.getParameter("hiddenProdutos")).andReturn("1,"+maxEstoque+";");
        expect(request.getParameter("inputTipo")).andReturn("Externo");
        expect(request.getParameter("inputFuncionario")).andReturn("1");
        expect(request.getParameter("inputCliente")).andReturn("2");
        expect(request.getParameter("inputEstado")).andReturn("Pendente");

        replay(request);
        replay(response);

        instance.processRequest(request, response);
        assertEquals("/pedidos", strArgument.toString());
    }

    @Test
    public void test7ConfirmarOperacao_excluir() throws UnsupportedEncodingException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));

        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("excluir");
        expect(request.getParameter("inputCodigo")).andReturn("6").andReturn("6");
        expect(request.getParameter("hiddenProdutos")).andReturn("1,2;");
        expect(request.getParameter("inputTipo")).andReturn("Externo");
        expect(request.getParameter("inputFuncionario")).andReturn("1");
        expect(request.getParameter("inputCliente")).andReturn("2");
        expect(request.getParameter("inputEstado")).andReturn("Pendente");

        replay(request);
        replay(response);

        instance.processRequest(request, response);
        assertEquals("/pedidos", strArgument.toString());
    }
}
