package service;

import dao.CategoriaDao;
import java.sql.Date;
import java.util.Calendar;
import javax.servlet.ServletException;
import model.Categoria;

/**
 *
 * @author caioc
 */
public class CategoriaService {

    public String confirmarOperacao(String operacao, String strCodigo, String nome, String descricao, String strData) {
        String retorno = "";
        try {
            int codigo = 0;
            if (!strCodigo.equals("")) {
                codigo = Integer.parseInt(strCodigo);
            } else {
                codigo = CategoriaDao.lastId() + 1;
            }
            if (strData.equals("")) {
                throw new ServletException("Por favor escolha um produto");
            }
            Date data = Date.valueOf(strData);
            if (data.compareTo(new Date(Calendar.getInstance().getTime().getTime())) > 0) {
                throw new ServletException("Data Errada");
            }
            Categoria categoria = new Categoria(codigo, nome, descricao, data);
            switch (operacao) {
                case "incluir":
                    CategoriaDao.salvar(categoria);
                    retorno = "Incluido com sucesso";
                    break;
                case "excluir":
                    CategoriaDao.apagar(categoria);
                    retorno = "Excluido com sucesso";
                    break;
                case "alterar":
                    CategoriaDao.alterar(categoria);
                    retorno = "Alterado com sucesso";
                    break;
            }
        } catch (Exception e) {
            retorno = "Erro durante a operação: " + e.getMessage();
        } finally {
            return retorno;
        }
    }
}
