package controller;

import dao.ClienteDao;
import dao.EstoqueDao;
import dao.FuncionarioDao;
import dao.PedidoDao;
import dao.PedidoProdutoDao;
import dao.ProdutoDao;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Estoque;
import model.Funcionario;
import model.Pedido;
import model.PedidoProduto;
import service.EstoqueService;
import service.PedidoProdutoService;

/**
 *
 * @author caioc
 */
public class ManterPedidoController extends HttpServlet {
    public ClienteDao cliDao = ClienteDao.INSTANCE;
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
                for (PedidoProduto pprod : pedido.getProdutos()) {
                    produtos.append(String.format("%d,%d;", pprod.getProduto().getCodigo(), pprod.getQuantidade()));
                }
            }
            request.setAttribute("hiddenProdutos", produtos.toString());
            request.getRequestDispatcher("/manterPedido.jsp").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex.getMessage());
        }
    }

    private void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String operacao = request.getParameter("operacao");
            int codigo = 0;
            if (!request.getParameter("inputCodigo").equals("")) {
                codigo = Integer.parseInt(request.getParameter("inputCodigo"));
            } else {
                codigo = PedidoDao.lastId() + 1;
            }
            String strProdutos = request.getParameter("hiddenProdutos");
            if (strProdutos.equals("")) {
                throw new ServletException("Por favor escolha um produto");
            }
            ArrayList<PedidoProduto> produtos = new ArrayList<>();
            String tipo = request.getParameter("inputTipo");
            for (String strProd : strProdutos.split(";")) {
                if (!strProd.equals("")) {
                    String[] prod = strProd.split(",");
                    PedidoProduto produto = new PedidoProduto(
                            Integer.parseInt(prod[1]),
                            ProdutoDao.get(Integer.parseInt(prod[0])));
                    if (!EstoqueService.verificarDisponibilidade(produto) && tipo.equals("Externo") && !operacao.equals("excluir")) {
                        throw new ServletException(
                                String.format("Produto %s indisponivel nessa quantidade, por favor fazer pedido interno",
                                        produto.getProduto().getNome()));
                    }
                    produtos.add(produto);
                }
            }

            Funcionario funcionario = FuncionarioDao.get(Integer.parseInt(request.getParameter("inputFuncionario")));
            Cliente cliente = cliDao.get(Integer.parseInt(request.getParameter("inputCliente")));
            Date data = new Date(Calendar.getInstance().getTime().getTime());
            String estado = request.getParameter("inputEstado");

            Pedido pedido = new Pedido(codigo, produtos, funcionario, cliente, data, estado, tipo);
            switch (operacao) {
                case "incluir":
                    pedido.setEstado("Pendente");
                    PedidoDao.salvar(pedido);
                    for (PedidoProduto pProd : pedido.getProdutos()) {
                        PedidoProdutoDao.salvar(pProd, pedido);
                        if (pedido.getTipo().equals("Externo")) {
                            Estoque estoque = EstoqueDao.listarCodProduto(pProd.getProduto()).get(0);
                            estoque.setQuantidade(estoque.getQuantidade() - pProd.getQuantidade());
                            estoque.setDataAlteracao(data);
                            EstoqueDao.alterar(estoque);
                        }
                    }
                    break;
                case "excluir": {
                    for (PedidoProduto pProd : PedidoDao.get(codigo).getProdutos()) {
                        PedidoProdutoDao.apagar(pProd);
                        EstoqueService.adcionarEstoque(pProd);
                    }
                    PedidoDao.apagar(pedido);
                    break;
                }
                case "alterar": {
                    PedidoProdutoService.updateProdutos(PedidoDao.get(codigo), pedido.getProdutos());
                    PedidoDao.alterar(pedido);
                    break;
                }
            }
            response.sendRedirect(request.getContextPath() + "/pedidos");
        } catch (Exception e) {
            throw new ServletException("Erro ao processar controller: " + e.getMessage());
        }
    }

}
