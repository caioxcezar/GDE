package controller;

import dao.CategoriaDao;
import dao.ProdutoDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categoria;
import model.Produto;

/**
 *
 * @author ccezar
 */
public class ManterProdutoController extends HttpServlet {

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
            request.setAttribute("categorias", CategoriaDao.listar());
            if(!operacao.equals("incluir")){
                int cod = Integer.parseInt(request.getParameter("cod"));
                request.setAttribute("produto", ProdutoDao.get(cod));
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterProduto.jsp");
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
                codigo = ProdutoDao.lastId() + 1;
            }

            String nome = request.getParameter("inputNome");
            String strValor = request.getParameter("inputValor").replace(',', '.');
            float valor = Float.parseFloat(strValor);
            Categoria categoria = CategoriaDao.get(Integer.parseInt(request.getParameter("inputCategoria")));
            String descricao = request.getParameter("inputDescricao");
            
            Produto produto = new Produto(codigo, nome, descricao, categoria, valor);
            switch (operacao) {
                case "incluir":
                    ProdutoDao.salvar(produto);
                    break;
                case "excluir":
                    ProdutoDao.apagar(produto);
                    break;
                case "alterar":
                    ProdutoDao.alterar(produto);
                    break;
            }
            //request.getRequestDispatcher("/categorias.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/produtos");
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
