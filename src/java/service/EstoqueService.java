package service;

import dao.EstoqueDao;
import dao.PedidoDao;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import model.Estoque;
import model.Pedido;
import model.PedidoProduto;
import model.Produto;

/**
 *
 * @author caioc
 */
public class EstoqueService {
    /***
     * Verifica quantidade de um determinado produto em estoque. Retona 0 caso ocorra algum erro.
     * @param produto
     * @return
     * @throws ClassNotFoundException 
     */
    private int verificarQuantidade(Produto produto) {
        int qtd = 0;
        try {
            for (Estoque estoque : EstoqueDao.listarCodProduto(produto)) {
                qtd += estoque.getQuantidade();
            }
        } catch (ClassNotFoundException | SQLException e) {
            return 0;
        }
        return qtd;
    }

    protected boolean verificarDisponibilidade(PedidoProduto produto) throws SQLException, ClassNotFoundException {
        return verificarQuantidade(produto.getProduto()) >= produto.getQuantidade();
    }

    protected void addEstoque(PedidoProduto pProd) throws SQLException, ClassNotFoundException {
        Estoque estoque = EstoqueDao.listarCodProduto(pProd.getProduto()).get(0);
        estoque.setQuantidade(estoque.getQuantidade() + pProd.getQuantidade());
        EstoqueDao.alterar(estoque);
    }

    protected void rmEstoque(PedidoProduto pProd) throws SQLException, ClassNotFoundException {
        Estoque estoque = EstoqueDao.listarCodProduto(pProd.getProduto()).get(0);
        estoque.setQuantidade(estoque.getQuantidade() - pProd.getQuantidade());
        EstoqueDao.alterar(estoque);
    }

    public String confirmarOperacao(String operacao, String strCodigo, String strPedido) {
        String retorno = "";
        try {
            int codigo = 0;
            if (!strCodigo.equals("")) {
                codigo = Integer.parseInt(strCodigo);
            } else {
                codigo = EstoqueDao.lastId() + 1;
            }
            Date data = new Date(Calendar.getInstance().getTime().getTime());

            Pedido pedido = PedidoDao.get(Integer.parseInt(strPedido));
            switch (operacao) {
                case "incluir": {
                    for (PedidoProduto produto : pedido.getProdutos()) {
                        ArrayList<Estoque> produtosEstoque = EstoqueDao.listarCodProduto(produto.getProduto());
                        if (produtosEstoque.size() > 1) {
                            throw new Exception(String.format(
                                    "Erro ao salvar no estoque, produto %s repetido",
                                    produto.getProduto().getNome()));
                        } else if (produtosEstoque.isEmpty()) {
                            Estoque estoque = new Estoque(codigo, produto.getQuantidade(), produto.getProduto(), data, pedido);
                            EstoqueDao.salvar(estoque);
                            codigo++;
                        } else {
                            Estoque estoque = produtosEstoque.get(0);
                            estoque.setQuantidade(estoque.getQuantidade() + produto.getQuantidade());
                            estoque.setPedido(pedido);
                            estoque.setDataAlteracao(data);
                            EstoqueDao.alterar(estoque);
                        }
                    }
                    pedido.setEstado("Pago");
                    PedidoDao.alterar(pedido);
                    retorno = "Estoque incluido com sucesso";
                    break;
                }
                case "excluir":{
                    Estoque estoque = EstoqueDao.get(codigo);
                    EstoqueDao.apagar(estoque);
                    retorno = "Estoque excluido com sucesso";
                    break;
                }
                case "visualizar":{
                    retorno = "É uma visualização, nada a fazer por aqui";
                    break;
                }
            }
        } catch (Exception e) {
            retorno = "Erro durante a operação: " + e.getMessage();
        }
        return retorno;
    }
}
