package controller;

import java.io.IOException;
import dao.EstoqueDao;
import dao.ProdutoDao;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Estoque;
import model.Produto;

/**
 *
 * @author ccezar
 */
public class EstoqueController extends HttpServlet {

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
        try {
            request.setAttribute("produtos", EstoqueDao.listar());
            RequestDispatcher view = request.getRequestDispatcher("/estoque.jsp");
            view.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erro ao processar controller: \n" + e.getMessage());
        }
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
        try {
            String termo = request.getParameter("inputTermo");
            ArrayList<Estoque> estoque = new ArrayList<>();
            try {
                estoque = EstoqueDao.listar(Integer.parseInt(termo));
            } catch (NumberFormatException ex) {
                ArrayList<Produto> prods = ProdutoDao.listar(termo);
                for (Produto prod : prods) {
                    estoque.addAll(EstoqueDao.listarNomeProduto(prod));
                }
            }
            request.setAttribute("produtos", estoque);
            RequestDispatcher view = request.getRequestDispatcher("/estoque.jsp");
            view.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erro ao processar controller: \n" + e.getMessage());
        }
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
