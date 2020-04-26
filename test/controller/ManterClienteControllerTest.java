package controller;

import dao.IClienteDao;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import org.easymock.Capture;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.newCapture;
import static org.easymock.EasyMock.replay;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maxz_
 */
public class ManterClienteControllerTest {

    HttpServletRequest request;
    HttpServletResponse response;
    ManterClienteController instance;
    Capture<String> strArgument;

    @Before
    public void setUp() {
        request = createMock(HttpServletRequest.class);
        response = createMock(HttpServletResponse.class);
        instance = new ManterClienteController();
        instance.cliDao = createMock(IClienteDao.class);

        strArgument = newCapture();
    }

    @Test
    public void testConfirmarOperacao_erroCliente() throws Exception {
        request.setCharacterEncoding("UTF-8");
        expect(instance.cliDao.lastId()).andReturn(1);
        
        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("incluir");
        expect(request.getParameter("inputCodigo")).andReturn("p").andReturn("p");
        expect(request.getParameter("inputCnpj")).andReturn("71.118.576/0001-04");
        expect(request.getParameter("inputNome")).andReturn("José");
        expect(request.getParameter("inputTelefone")).andReturn("40028922");
        expect(request.getParameter("inputNumero")).andReturn("72");
        expect(request.getParameter("inputCep")).andReturn("36015475");
        expect(request.getParameter("inputRua")).andReturn("Rua Teresa");
        expect(request.getParameter("inputBairro")).andReturn("Jardins");
        expect(request.getParameter("inputCidade")).andReturn("Juiz de Fora");
        expect(request.getParameter("inputComplemento")).andReturn("Casa 10");
        expect(request.getParameter("inputEstado")).andReturn("MG");

        replay(request);
        replay(response);
        replay(instance.cliDao);
        try {
            instance.processRequest(request, response);
        } catch (ServletException ex) {
            assertEquals("Erro ao processar controller: For input string: \"p\"", ex.getMessage());
        }
    }

    @Test
    public void testConfirmarOperacao_inclusao() throws Exception {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));

        expect(instance.cliDao.lastId()).andReturn(1);
        instance.cliDao.salvar(isA(Cliente.class));
        
        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("incluir");
        expect(request.getParameter("inputCodigo")).andReturn("");
        expect(request.getParameter("inputCnpj")).andReturn("71.118.576/0001-04");
        expect(request.getParameter("inputNome")).andReturn("José");
        expect(request.getParameter("inputTelefone")).andReturn("40028922");
        expect(request.getParameter("inputNumero")).andReturn("72");
        expect(request.getParameter("inputCep")).andReturn("36015475");
        expect(request.getParameter("inputRua")).andReturn("Rua Teresa");
        expect(request.getParameter("inputBairro")).andReturn("Jardins");
        expect(request.getParameter("inputCidade")).andReturn("Juiz de Fora");
        expect(request.getParameter("inputComplemento")).andReturn("Casa 10");
        expect(request.getParameter("inputEstado")).andReturn("MG");

        replay(request);
        replay(response);
        replay(instance.cliDao);
        
        instance.processRequest(request, response);
        assertEquals("/clientes", strArgument.toString());
    }

    @Test
    public void testConfirmarOperacao_alteracao() throws Exception {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));

        instance.cliDao.alterar(isA(Cliente.class));
        
        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("alterar");
        expect(request.getParameter("inputCodigo")).andReturn("2").andReturn("2");
        expect(request.getParameter("inputCnpj")).andReturn("71.118.576/0001-04");
        expect(request.getParameter("inputNome")).andReturn("Jose Maria");
        expect(request.getParameter("inputTelefone")).andReturn("40028922");
        expect(request.getParameter("inputNumero")).andReturn("72");
        expect(request.getParameter("inputCep")).andReturn("36015475");
        expect(request.getParameter("inputRua")).andReturn("Rua Madre Teresa");
        expect(request.getParameter("inputBairro")).andReturn("Jardins");
        expect(request.getParameter("inputCidade")).andReturn("Juiz de Fora");
        expect(request.getParameter("inputComplemento")).andReturn("Casa 11");
        expect(request.getParameter("inputEstado")).andReturn("MG");

        replay(request);
        replay(response);
        replay(instance.cliDao);
        
        instance.processRequest(request, response);
        assertEquals("/clientes", strArgument.toString());
    }

    @Test
    public void testConfirmarOperacao_exclusao() throws Exception {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));

        Cliente cliente = new Cliente("", "", "");
        cliente.setCodigo(3);
        instance.cliDao.apagar(isA(Cliente.class));
        
        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("excluir");
        expect(request.getParameter("inputCodigo")).andReturn("3").andReturn("3");
        expect(request.getParameter("inputCnpj")).andReturn("71.118.576/0001-04");
        expect(request.getParameter("inputNome")).andReturn("Jose Maria");
        expect(request.getParameter("inputTelefone")).andReturn("40028922");
        expect(request.getParameter("inputNumero")).andReturn("72");
        expect(request.getParameter("inputCep")).andReturn("36015475");
        expect(request.getParameter("inputRua")).andReturn("Rua Madre Teresa");
        expect(request.getParameter("inputBairro")).andReturn("Jardins");
        expect(request.getParameter("inputCidade")).andReturn("Juiz de Fora");
        expect(request.getParameter("inputComplemento")).andReturn("Casa 11");
        expect(request.getParameter("inputEstado")).andReturn("MG");

        replay(request);
        replay(response);
        replay(instance.cliDao);
        
        instance.processRequest(request, response);
        assertEquals("/clientes", strArgument.toString());
    }

}
