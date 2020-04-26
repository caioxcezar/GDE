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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maxz_
 */
public class ManterCargoControllerTest {
    
    HttpServletRequest request;
    HttpServletResponse response;
    ManterCargoController instance;
    Capture<String> strArgument;
    
    @Before
    public void setUp() {
        request = createMock(HttpServletRequest.class);
        response = createMock(HttpServletResponse.class);
        instance = new ManterCargoController();
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
        expect(request.getParameter("inputNome")).andReturn("CEO");
        expect(request.getParameter("inputDescricao")).andReturn("Chefia");

        
        replay(request);
        replay(response);
        
        instance.processRequest(request, response);
        assertEquals("/cargos", strArgument.toString());
    }
    
    @Test
    public void testConfirmarOperacao_alteracao() throws UnsupportedEncodingException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));
        
        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("alterar");
        expect(request.getParameter("inputCodigo")).andReturn("4").andReturn("4");
        expect(request.getParameter("inputNome")).andReturn("Chefia");
        expect(request.getParameter("inputDescricao")).andReturn("CEO");

        
        replay(request);
        replay(response);
        
        instance.processRequest(request, response);
        assertEquals("/cargos", strArgument.toString());
    }
        
        @Test
        public void testConfirmarOperacao_exclusao() throws UnsupportedEncodingException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));
        
        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("excluir");
        expect(request.getParameter("inputCodigo")).andReturn("4").andReturn("4");
        expect(request.getParameter("inputNome")).andReturn("Chefia");
        expect(request.getParameter("inputDescricao")).andReturn("CEO");

        
        replay(request);
        replay(response);
        
        instance.processRequest(request, response);
        assertEquals("/cargos", strArgument.toString());
    }
}
