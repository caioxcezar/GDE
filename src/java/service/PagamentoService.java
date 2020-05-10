package service;

import dao.NotaFiscalDao;
import dao.PedidoDao;
import java.sql.Date;
import java.util.Calendar;
import javax.servlet.ServletException;
import model.NotaFiscal;
import model.Pedido;

/**
 *
 * @author caioc
 */
public class PagamentoService {

    public String confirmarOperacao(String operacao, String strCodigo, String strPedido) {
        String retorno = "";
        try {
            int codigo = 0;
            if (!strCodigo.equals("")) {
                codigo = Integer.parseInt(strCodigo);
            } else {
                codigo = NotaFiscalDao.lastId() + 1;
            }
            Pedido pedido = PedidoDao.get(Integer.parseInt(strPedido));
            Date data = new Date(Calendar.getInstance().getTime().getTime());

            NotaFiscal nFical = new NotaFiscal(codigo, data, pedido);
            switch (operacao) {
                case "incluir":
                    NotaFiscalDao.salvar(nFical);
                    pedido.setEstado("Pago");
                    PedidoDao.alterar(pedido);
                    retorno = "Incluido com sucesso";
                    break;
                case "excluir": {
                    NotaFiscalDao.apagar(nFical);
                    retorno = "Excluido com sucesso";
                    break;
                }
                case "visualizar": {
                    retorno = "É uma visualização, nada a fazer por aqui";
                    break;
                }
            }
        } catch (Exception e) {
            retorno = "Erro durante a operação: " + e.getMessage();
        } finally {
            return retorno;
        }
    }
}
