package service;

import dao.EstoqueDao;
import dao.PedidoDao;
import dao.PedidoProdutoDao;
import java.sql.SQLException;
import model.Estoque;
import model.PedidoProduto;
import model.Produto;

/**
 *
 * @author caioc
 */
public class EstoqueService {

    public static int verificarQuantidade(Produto produto) throws SQLException, ClassNotFoundException {
        int qtd = 0;
        for (Estoque estoque : EstoqueDao.listarPorProduto(produto)) {
            qtd += estoque.getQuantidade();
        }
        return qtd;
    }

    public static boolean verificarDisponibilidade(PedidoProduto produto) throws SQLException, ClassNotFoundException {
        return verificarQuantidade(produto.getProduto()) >= produto.getQuantidade();
    }

    public static void adcionarEstoque(PedidoProduto pProd) throws SQLException, ClassNotFoundException {
        Estoque estoque = EstoqueDao.listarPorProduto(pProd.getProduto()).get(0);
        estoque.setQuantidade(estoque.getQuantidade() + pProd.getQuantidade());
        EstoqueDao.alterar(estoque);
    }

    public static void removerEstoque(PedidoProduto pProd) throws SQLException, ClassNotFoundException {
        Estoque estoque = EstoqueDao.listarPorProduto(pProd.getProduto()).get(0);
        estoque.setQuantidade(estoque.getQuantidade() - pProd.getQuantidade());
        EstoqueDao.alterar(estoque);
    }
}
