package service;

import dao.CargoDao;
import java.sql.SQLException;
import javax.servlet.ServletException;
import model.Cargo;

/**
 *
 * @author caioc
 */
public class CargoService {
    public String confirmarOperacao(String operacao, String strCodigo, String nome, String descricao) {
        String retorno = "";        
        try {
            int codigo = 0;
            if (!strCodigo.equals("")) {
                codigo = Integer.parseInt(strCodigo);
            } else {
                codigo = CargoDao.lastId() + 1;
            }
            Cargo cargo = new Cargo(codigo, nome, descricao);
            switch (operacao) {
                case "incluir":
                    CargoDao.salvar(cargo);
                    retorno = "Incluido com sucesso";
                    break;
                case "excluir":
                    CargoDao.apagar(cargo);
                    retorno = "Excluido com sucesso";
                    break;
                case "alterar":
                    CargoDao.alterar(cargo);
                    retorno = "Alteraco com sucesso";
                    break;
            }
            
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            retorno = "Erro durante a operação: " + e.getMessage();
        }
        return retorno;
    }
}
