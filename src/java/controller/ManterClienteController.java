package controller;

import dao.ClienteDao;
import dao.EstadoDao;
import dao.IClienteDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Estado;
import service.ClienteService;

/**
 *
 * @author ccezar
 */
public class ManterClienteController extends HttpServlet {

    public IClienteDao cliDao = ClienteDao.INSTANCE;
    private final ClienteService clienteService = new ClienteService();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");
        switch (acao) {
            case "prepararOperacao":
                prepararOperacao(request, response);
                break;
            case "confirmarOperacao":
                confirmarOperacao(request, response);
                break;
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void prepararOperacao(HttpServletRequest request, HttpServletResponse response) {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("estados", EstadoDao.listar());
            if (!operacao.equals("incluir")) {
                int cod = Integer.parseInt(request.getParameter("cod"));
                request.setAttribute("cliente", cliDao.get(cod));
            }
            request.getRequestDispatcher("/manterCliente.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String retorno;
        try {
            String cnpj = request.getParameter("inputCnpj");
            String nome = request.getParameter("inputNome");
            String telefone = request.getParameter("inputTelefone");
            int numero = Integer.parseInt(request.getParameter("inputNumero"));
            String cep = request.getParameter("inputCep");
            String rua = request.getParameter("inputRua");
            String bairro = request.getParameter("inputBairro");
            String cidade = request.getParameter("inputCidade");
            String complemento = request.getParameter("inputComplemento");
            Estado estado = EstadoDao.get(request.getParameter("inputEstado"));

            Cliente cliente = new Cliente(cnpj, nome, telefone, numero, -1, cep, rua, bairro, cidade, complemento, estado);
            retorno = clienteService.confirmarOperacao(request.getParameter("operacao"), request.getParameter("inputCodigo"), cliente);
            if (retorno.contains("Erro durante a operação: ")) {
                throw new ServletException(retorno);
            }
            response.sendRedirect(request.getContextPath() + "/clientes");
        } catch (Exception e) {
            throw new ServletException("Erro ao processar controller: " + e.getMessage());
        }

    }
}
