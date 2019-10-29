package controller;

import dao.EstoqueDao;
import dao.ProdutoDao;
import java.io.IOException;
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

    private void prepararOperacao(HttpServletRequest request, HttpServletResponse response) {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("produtos", ProdutoDao.listar());
            if (!operacao.equals("incluir")){
                int cod = Integer.parseInt(request.getParameter("cod"));
                request.setAttribute("estoque", EstoqueDao.get(cod));
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterEstoque.jsp");
            view.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
            int quantidade = Integer.parseInt(request.getParameter("inputQuantidade"));
            Produto produto = ProdutoDao.get(Integer.parseInt(request.getParameter("inputProduto")));
            Estoque estoque = new Estoque(codigo, quantidade, produto);
            switch (operacao) {
                case "incluir":
                    if (EstoqueDao.listarPorProduto(produto).size() != 0){
                        throw new ServletException("Produto já esta no banco");
                    }
                    EstoqueDao.salvar(estoque);
                    break;
                case "excluir":
                    EstoqueDao.apagar(estoque);
                    break;
                case "alterar":
                    EstoqueDao.alterar(estoque);
                    break;
            }
            //request.getRequestDispatcher("/categorias.jsp").forward(request, response);
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
