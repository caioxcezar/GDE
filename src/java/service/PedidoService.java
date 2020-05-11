package service;

import dao.ClienteDao;
import dao.EstoqueDao;
import dao.FuncionarioDao;
import dao.PedidoDao;
import dao.PedidoProdutoDao;
import dao.ProdutoDao;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import model.Cliente;
import model.Estoque;
import model.Funcionario;
import model.Pedido;
import model.PedidoProduto;

/**
 *
 * @author caioc
 */
public class PedidoService {
    private final EstoqueService estoqueService = new EstoqueService();
    private final ClienteDao cliDao;
    private final PedidoProdutoService pedidoProdutoService = new PedidoProdutoService();

    public PedidoService() {
        this.cliDao = ClienteDao.INSTANCE;
    }
    
    public String confirmarOperacao(String operacao, String strCodigo, String strProdutos, String tipo, String strFuncionario, String strCliente, String estado) {
        String retorno = "";
        try {
            int codigo = 0;
            
            if (!strCodigo.equals("")) {
                codigo = Integer.parseInt(strCodigo);
            } else {
                codigo = PedidoDao.lastId() + 1;
            }
            if (strProdutos.equals("")) {
                throw new ServletException("Por favor escolha um produto");
            }
            ArrayList<PedidoProduto> produtos = new ArrayList<>();
            for (String strProd : strProdutos.split(";")) {
                if (!strProd.equals("")) {
                    String[] prod = strProd.split(",");
                    PedidoProduto produto = new PedidoProduto(
                            Integer.parseInt(prod[1]),
                            ProdutoDao.get(Integer.parseInt(prod[0])));
                    if (!estoqueService.verificarDisponibilidade(produto) && tipo.equals("Externo") && !operacao.equals("excluir")) {
                        throw new ServletException(String.format("Produto %s indisponivel nessa quantidade, por favor fazer pedido interno",
                                        produto.getProduto().getNome()));
                    }
                    produtos.add(produto);
                }
            }
            if (produtos.size() == 0) {
                throw new ServletException("Favor escolher pelo menos um produto");
            }
            Funcionario funcionario = FuncionarioDao.get(Integer.parseInt(strFuncionario));
            Cliente cliente = cliDao.get(Integer.parseInt(strCliente));
            Date data = new Date(Calendar.getInstance().getTime().getTime());

            Pedido pedido = new Pedido(codigo, produtos, funcionario, cliente, data, estado, tipo);
            switch (operacao) {
                case "incluir":
                    pedido.setEstado("Pendente");
                    PedidoDao.salvar(pedido);
                    for (PedidoProduto pProd : pedido.getProdutos()) {
                        PedidoProdutoDao.salvar(pProd, pedido);
                        if (pedido.getTipo().equals("Externo")) {
                            Estoque estoque = EstoqueDao.listarCodProduto(pProd.getProduto()).get(0);
                            estoque.setQuantidade(estoque.getQuantidade() - pProd.getQuantidade());
                            estoque.setDataAlteracao(data);
                            EstoqueDao.alterar(estoque);
                        }
                    }
                    retorno = "Incluido com sucesso";
                    break;
                case "excluir": {
                    for (PedidoProduto pProd : PedidoDao.get(codigo).getProdutos()) {
                        PedidoProdutoDao.apagar(pProd);
                        estoqueService.addEstoque(pProd);
                    }
                    PedidoDao.apagar(pedido);
                    retorno = "Excluido com sucesso";
                    break;
                }
                case "alterar": {
                    pedidoProdutoService.updateProdutos(PedidoDao.get(codigo), pedido.getProdutos());
                    PedidoDao.alterar(pedido);
                    retorno = "Alterado com sucesso";
                    break;
                }
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException | ServletException e) {
            retorno = "Erro durante a operação: " + e.getMessage();
        } finally {
            return retorno;
        }
    }
}
