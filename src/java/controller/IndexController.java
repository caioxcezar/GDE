package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author caioc
 */
public class IndexController extends HttpServlet {

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
        String acao = "incluir";
        String operacao = "prepararOperacao";
        String caminho = request.getParameter("caminho");
        request.setAttribute("acao", acao);
        request.setAttribute("operacao", operacao);
        switch (caminho) {
            case "manterCliente":
                getServletContext().getRequestDispatcher("/manterCliente").forward(request, response);
                break;
            case "manterFuncionario":
                getServletContext().getRequestDispatcher("/manterFuncionario").forward(request, response);
                break;
            case "manterProduto":
                getServletContext().getRequestDispatcher("/manterProduto").forward(request, response);
                break;
            case "manterCategoria":
                getServletContext().getRequestDispatcher("/manterCategoria").forward(request, response);
                break;
            case "manterCargo":
                getServletContext().getRequestDispatcher("/manterCargo").forward(request, response);
                break;
            case "clientes":
                getServletContext().getRequestDispatcher("/clientes");
                break;
            case "funcionarios":
                getServletContext().getRequestDispatcher("/funcionarios");
                break;
            case "produtos":
                getServletContext().getRequestDispatcher("/produtos");
                break;
            case "estoque":
                getServletContext().getRequestDispatcher("/estoque");
                break;
            case "categorias":
                getServletContext().getRequestDispatcher("/categorias");
                break;
            case "cargos":
                getServletContext().getRequestDispatcher("/cargos");
                break;
            case "pedidos":
                getServletContext().getRequestDispatcher("/pedidos");
                break;
            case "notasFiscais":
                getServletContext().getRequestDispatcher("/notasFiscais");
                break;
            case "manterPedido":
                getServletContext().getRequestDispatcher("/manterPedido");
                break;
            case "manterPagamento":
                getServletContext().getRequestDispatcher("/manterPagamento");
                break;
            case "manterEstoque":
                getServletContext().getRequestDispatcher("/manterEstoque");
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

}
