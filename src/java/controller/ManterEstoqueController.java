package controller;

import dao.EstoqueDao;
import dao.PedidoDao;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Estoque;
import model.Pedido;
import model.PedidoProduto;

/**
 *
 * @author ccezar
 */
public class ManterEstoqueController extends HttpServlet {

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

    private void prepararOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            ArrayList<Pedido> pedidos = new ArrayList<>();
            if (!operacao.equals("incluir")) {
                Estoque estoque = EstoqueDao.get(Integer.parseInt(request.getParameter("cod")));
                request.setAttribute("estoque", estoque);
                pedidos.add(estoque.getPedido());
            } else {
                pedidos = PedidoDao.listarPendentesInterno();
            }
            request.setAttribute("pedidos", pedidos);
            request.getRequestDispatcher("/manterEstoque.jsp").forward(request, response);
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
                codigo = EstoqueDao.lastId() + 1;
            }
            Date data = new Date(Calendar.getInstance().getTime().getTime());

            Pedido pedido = PedidoDao.get(Integer.parseInt(request.getParameter("inputPedido")));
            switch (operacao) {
                case "incluir": {
                    for (PedidoProduto produto : pedido.getProdutos()) {
                        ArrayList<Estoque> produtosEstoque = EstoqueDao.listarCodProduto(produto.getProduto());
                        if (produtosEstoque.size() > 1) {
                            throw new Exception(String.format(
                                    "Erro ao salvar no estoque, produto %s repetido",
                                    produto.getProduto().getNome()));
                        } else if (produtosEstoque.size() == 0) {
                            Estoque estoque = new Estoque(codigo, produto.getQuantidade(), produto.getProduto(), data, pedido);
                            EstoqueDao.salvar(estoque);
                            codigo++;
                        } else {
                            Estoque estoque = produtosEstoque.get(0);
                            estoque.setQuantidade(estoque.getQuantidade() + produto.getQuantidade());
                            estoque.setPedido(pedido);
                            estoque.setDataAlteracao(data);
                            EstoqueDao.alterar(estoque);
                        }
                    }
                    pedido.setEstado("Pago");
                    PedidoDao.alterar(pedido);
                    break;
                }
                case "excluir":{
                    Estoque estoque = EstoqueDao.get(codigo);
                    EstoqueDao.apagar(estoque);
                    break;
                }
                case "visualizar":{
                    break;
                }
            }
            response.sendRedirect(request.getContextPath() + "/estoque");
        } catch (Exception e) {
            throw new ServletException("Erro ao processar controller: " + e.getMessage());
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

}
