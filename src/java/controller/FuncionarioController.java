package controller;

import dao.ClienteDao;
import dao.FuncionarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Funcionario;

/**
 *
 * @author ccezar
 */
public class FuncionarioController extends HttpServlet {

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
            ArrayList<Funcionario> funcionarios = FuncionarioDao.listar();
            request.setAttribute("funcionarios", funcionarios);
            RequestDispatcher view = request.getRequestDispatcher("/funcionarios.jsp");
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
            ArrayList<Funcionario> funcionarios = new ArrayList<>();
            try{
                funcionarios = FuncionarioDao.listar(Integer.parseInt(termo));
            }catch(NumberFormatException ex){
                funcionarios = FuncionarioDao.listar(termo);
            }
            request.setAttribute("funcionarios", funcionarios);
            RequestDispatcher view = request.getRequestDispatcher("/funcionarios.jsp");
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
