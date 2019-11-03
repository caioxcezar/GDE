package controller;

import dao.CargoDao;
import dao.EstadoDao;
import dao.FuncionarioDao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;
import model.Estado;
import model.Funcionario;

/**
 *
 * @author ccezar
 */
@WebServlet(name = "ManterFuncionarioController", urlPatterns = {"/manterFuncionario"})
public class ManterFuncionarioController extends HttpServlet {

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
            request.setAttribute("estados", EstadoDao.listar());
            request.setAttribute("cargos", CargoDao.listar());
            if (!operacao.equals("incluir")) {
                int cod = Integer.parseInt(request.getParameter("cod"));
                request.setAttribute("funcionario", FuncionarioDao.get(cod));
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterFuncionario.jsp");
            view.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }

    private void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String operacao = request.getParameter("operacao");
            int codigo = 0;
            if (!request.getParameter("inputCodigo").equals("")) {
                codigo = Integer.parseInt(request.getParameter("inputCodigo"));
            } else {
                codigo = FuncionarioDao.lastId() + 1;
            }

            String cpf = request.getParameter("inputCPF");
            String nome = request.getParameter("inputNome");
            String telefone = request.getParameter("inputTelefone");
            int numero = Integer.parseInt(request.getParameter("inputNumero"));
            String cep = request.getParameter("inputCep");
            String rua = request.getParameter("inputRua");
            String bairro = request.getParameter("inputBairro");
            String cidade = request.getParameter("inputCidade");
            String complemento = request.getParameter("inputComplemento");
            Estado estado = EstadoDao.get(request.getParameter("inputEstado"));
            Cargo cargo = CargoDao.get(Integer.parseInt(request.getParameter("inputCargo")));
            String strSalario = request.getParameter("inputSalario").replace(',', '.');
            float salario = Float.parseFloat(strSalario);
            
            Funcionario funcionario = new Funcionario(cpf, cargo, nome, telefone, 
                    numero, codigo, cep, rua, bairro, cidade, complemento, estado, salario);
            switch (operacao) {
                case "incluir":
                    FuncionarioDao.salvar(funcionario);
                    break;
                case "excluir":
                    FuncionarioDao.apagar(funcionario);
                    break;
                case "alterar":
                    FuncionarioDao.alterar(funcionario);
                    break;
            }
            //request.getRequestDispatcher("/categorias.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/funcionarios");
        } catch (IOException | ClassNotFoundException | NumberFormatException | SQLException e) {
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
