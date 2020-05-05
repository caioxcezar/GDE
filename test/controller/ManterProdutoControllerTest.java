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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

 @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManterProdutoControllerTest {

    HttpServletRequest request;
    HttpServletResponse response;
    ManterProdutoController instance;
    Capture<String> strArgument;
    
    @Before
    public void setUp() {
        request = createMock(HttpServletRequest.class);
        response = createMock(HttpServletResponse.class);
        instance = new ManterProdutoController();
        strArgument = newCapture();
    }

    @Test
    public void test1ConfirmarOperacao_inclusao() throws UnsupportedEncodingException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));
        
        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("incluir");
        expect(request.getParameter("inputCodigo")).andReturn("");
        expect(request.getParameter("inputNome")).andReturn("Teclado");
        expect(request.getParameter("inputDescricao")).andReturn("USB");
        expect(request.getParameter("inputValor")).andReturn("100.0");
        expect(request.getParameter("inputCategoria")).andReturn("1");
        
        replay(request);
        replay(response);
        
        instance.processRequest(request, response);
        assertEquals("/produtos", strArgument.toString());
    }
 
  @Test
    public void test2ConfirmarOperacao_alteracao() throws UnsupportedEncodingException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));
        
        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("alterar");
        expect(request.getParameter("inputCodigo")).andReturn("1").andReturn("1");
        expect(request.getParameter("inputNome")).andReturn("Teclado");
        expect(request.getParameter("inputDescricao")).andReturn("USB");
        expect(request.getParameter("inputValor")).andReturn("80.0");
        expect(request.getParameter("inputCategoria")).andReturn("1");
        
        replay(request);
        replay(response);
        
        instance.processRequest(request, response);
        assertEquals("/produtos", strArgument.toString());
    }

    @Test
    public void test3ConfirmarOperacao_exclusao() throws UnsupportedEncodingException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        expect(request.getContextPath()).andReturn("");
        response.sendRedirect(capture(strArgument));
        
        expect(request.getParameter("acao")).andReturn("confirmarOperacao");
        expect(request.getParameter("operacao")).andReturn("excluir");
        expect(request.getParameter("inputCodigo")).andReturn("2").andReturn("2");
        expect(request.getParameter("inputNome")).andReturn("Teclado");
        expect(request.getParameter("inputDescricao")).andReturn("USB");
        expect(request.getParameter("inputValor")).andReturn("80.0");
        expect(request.getParameter("inputCategoria")).andReturn("1");
        
        replay(request);
        replay(response);
        
        instance.processRequest(request, response);
        assertEquals("/produtos", strArgument.toString());
    }

}
