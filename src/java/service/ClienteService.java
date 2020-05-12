package service;

import dao.ClienteDao;
import dao.IClienteDao;
import java.sql.SQLException;
import model.Cliente;

/**
 *
 * @author caioc
 */
public class ClienteService {
    public IClienteDao cliDao = ClienteDao.INSTANCE;
    public String confirmarOperacao(String operacao, String strCodigo, Cliente cliente) {
        String retorno = "";
        try {
            int codigo = 0;
            if (!strCodigo.equals("")) {
                codigo = Integer.parseInt(strCodigo);
            } else {
                codigo = cliDao.lastId() + 1;
            }
            cliente.setCodigo(codigo);
            switch (operacao) {
                case "incluir":
                    cliDao.salvar(cliente);
                    retorno = "Incluido com sucesso";
                    break;
                case "excluir":
                    cliDao.apagar(cliente);
                    retorno = "Excluido com sucesso";
                    break;
                case "alterar":
                    retorno = "Alterado com sucesso";
                    cliDao.alterar(cliente);
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            retorno = "Erro durante a operação: " + e.getMessage();
        } finally {
            return retorno;
        }
    }
}
