package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author caioc
 */
public class ManterPedidoControllerTest {

    HttpServletRequest request;
    HttpServletResponse response;
    ManterPedidoController instance;

    @Before
    public void setUp() {
        request = createMock(HttpServletRequest.class);
        response = createMock(HttpServletResponse.class);
        instance = new ManterPedidoController();
    }

    @Test
    public void testConfirmarOperacao_erroProduto() throws UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("incluir");
        expect(request.getParameter("inputCodigo")).andReturn("");
        expect(request.getParameter("hiddenProdutos")).andReturn("");
        expect(request.getParameter("inputTipo")).andReturn("Interno");
        expect(request.getParameter("inputFuncionario")).andReturn("1");
        expect(request.getParameter("inputCliente")).andReturn("1");
        
        replay(request);
        replay(response);

        try {
            instance.processRequest(request, response);
        } catch (ServletException ex) {
            assertEquals("Erro ao processar controller: Por favor escolha um produto", ex.getMessage());
        }
    }

    @Test
    public void testConfirmarOperacao_inclusao() throws UnsupportedEncodingException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("htttp://teste.teste");
        
        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("incluir");
        expect(request.getParameter("inputCodigo")).andReturn("");
        expect(request.getParameter("hiddenProdutos")).andReturn("");
        expect(request.getParameter("inputTipo")).andReturn("Interno");
        expect(request.getParameter("inputFuncionario")).andReturn("1");
        expect(request.getParameter("inputCliente")).andReturn("1");
        
        replay(request);
        replay(response);
        
        instance.processRequest(request, response);
        
        response.sendRedirect("htttp://teste.teste/pedidos");
    }
}
