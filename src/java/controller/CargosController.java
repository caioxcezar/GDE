package controller;

import dao.CargoDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;

/**
 *
 * @author ccezar
 */
public class CargosController extends HttpServlet {

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
            ArrayList<Cargo> cargos = CargoDao.listar();
            request.setAttribute("cargos", cargos);
            RequestDispatcher view = request.getRequestDispatcher("/cargos.jsp");
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
            ArrayList<Cargo> cargos = new ArrayList<>();
            try {
                cargos = CargoDao.listar(Integer.parseInt(termo));
            }catch(NumberFormatException ex){
                cargos = CargoDao.listar(termo);
            }
            request.setAttribute("cargos", cargos);
            RequestDispatcher view = request.getRequestDispatcher("/cargos.jsp");
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
