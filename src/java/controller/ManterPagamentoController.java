package controller;

import dao.NotaFiscalDao;
import dao.PedidoDao;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.NotaFiscal;
import model.Pedido;

/**
 *
 * @author caioc
 */
public class ManterPagamentoController extends HttpServlet {

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
                NotaFiscal nota = NotaFiscalDao.get(Integer.parseInt(request.getParameter("cod")));
                request.setAttribute("notaFiscal", nota);
                pedidos.add(nota.getPedido());
            } else {
                pedidos = PedidoDao.listarPendentesExterno();
            }
            request.setAttribute("pedidos", pedidos);
            request.getRequestDispatcher("/manterPagamento.jsp").forward(request, response);
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
                codigo = NotaFiscalDao.lastId() + 1;
            }
            Pedido pedido = PedidoDao.get(Integer.parseInt(request.getParameter("inputPedido")));
            Date data = new Date(Calendar.getInstance().getTime().getTime());

            NotaFiscal nFical = new NotaFiscal(codigo, data, pedido);
            switch (operacao) {
                case "incluir":
                    NotaFiscalDao.salvar(nFical);
                    pedido.setEstado("Pago");
                    PedidoDao.alterar(pedido);
                    break;
                case "excluir": {
                    NotaFiscalDao.apagar(nFical);
                    break;
                }
                case "alterar": {
                    NotaFiscalDao.alterar(nFical);
                    break;
                }
            }
            //request.getRequestDispatcher("/pedidos.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/notasFiscais");
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
