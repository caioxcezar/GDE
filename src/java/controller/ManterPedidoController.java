package controller;

import dao.ClienteDao;
import dao.FuncionarioDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Pedido;
import model.PedidoProduto;
import service.PedidoService;

/**
 *
 * @author caioc
 */
public class ManterPedidoController extends HttpServlet {

    public ClienteDao cliDao = ClienteDao.INSTANCE;
    private final PedidoService pedidoService = new PedidoService();
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

    private void prepararOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            ArrayList<Funcionario> funcionarios = FuncionarioDao.listarVendedores();
            request.setAttribute("funcionarios", funcionarios);
            request.setAttribute("clientes", cliDao.listar());
            request.setAttribute("produtos", ProdutoDao.listar());
            StringBuilder produtos = new StringBuilder("");
            if (!operacao.equals("incluir")) {
                Pedido pedido = PedidoDao.get(Integer.parseInt(request.getParameter("cod")));
                request.setAttribute("pedido", pedido);
                pedido.getProdutos().forEach((pprod) -> {
                    produtos.append(String.format("%d,%d;", pprod.getProduto().getCodigo(), pprod.getQuantidade()));
                });
            }
            request.setAttribute("hiddenProdutos", produtos.toString());
            request.getRequestDispatcher("/manterPedido.jsp").forward(request, response);
        } catch (IOException | ClassNotFoundException | NumberFormatException | SQLException | ServletException ex) {
            throw new ServletException(ex.getMessage());
        }
    }

    private void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String retorno;
        try {
            retorno = pedidoService.confirmarOperacao(request.getParameter("operacao"),
                    request.getParameter("inputCodigo"),
                    request.getParameter("hiddenProdutos"),
                    request.getParameter("inputTipo"),
                    request.getParameter("inputFuncionario"),
                    request.getParameter("inputCliente"),
                    request.getParameter("inputEstado"));
            if (retorno.contains("Erro durante a operação: ")) {
                throw new ServletException(retorno);
            }
            response.sendRedirect(request.getContextPath() + "/pedidos");
        } catch (IOException e) {
            throw new ServletException("Erro ao processar controller: " + e.getMessage());
        }
    }

}
