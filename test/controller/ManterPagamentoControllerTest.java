package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.easymock.Capture;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.newCapture;
import static org.easymock.EasyMock.replay;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author caioc
 */
public class ManterPagamentoControllerTest {
    
    HttpServletRequest request;
    HttpServletResponse response;
    ManterPagamentoController instance;
    Capture<String> strArgument;

    @Before
    public void setUp() {
        request = createMock(HttpServletRequest.class);
        response = createMock(HttpServletResponse.class);
        instance = new ManterPagamentoController();
        strArgument = newCapture();
    }
    
    @Test
    public void testConfirmarOperacao_inclusao() throws UnsupportedEncodingException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));

        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("incluir");
        expect(request.getParameter("inputCodigo")).andReturn("");
        expect(request.getParameter("inputPedido")).andReturn("3");

        replay(request);
        replay(response);

        instance.processRequest(request, response);
        assertEquals("/notasFiscais", strArgument.toString());
    }
    
    @Test
    public void testConfirmarOperacao_visualizar() throws UnsupportedEncodingException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));

        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("visualizar");
        expect(request.getParameter("inputCodigo")).andReturn("");
        expect(request.getParameter("inputPedido")).andReturn("3");

        replay(request);
        replay(response);

        instance.processRequest(request, response);
        assertEquals("/notasFiscais", strArgument.toString());
    }
    
    @Test
    public void testConfirmarOperacao_excluir() throws UnsupportedEncodingException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));

        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("visualizar");
        expect(request.getParameter("inputCodigo")).andReturn("");
        expect(request.getParameter("inputPedido")).andReturn("3");

        replay(request);
        replay(response);

        instance.processRequest(request, response);
        assertEquals("/notasFiscais", strArgument.toString());
    }
}
