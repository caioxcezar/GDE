package service;

import dao.EstoqueDao;
import java.sql.SQLException;
import model.Estoque;
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
    public static int verificarQuantidade(Produto produto) {
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

    public static boolean verificarDisponibilidade(PedidoProduto produto) throws SQLException, ClassNotFoundException {
        return verificarQuantidade(produto.getProduto()) >= produto.getQuantidade();
    }

    public static void adcionarEstoque(PedidoProduto pProd) throws SQLException, ClassNotFoundException {
        Estoque estoque = EstoqueDao.listarCodProduto(pProd.getProduto()).get(0);
        estoque.setQuantidade(estoque.getQuantidade() + pProd.getQuantidade());
        EstoqueDao.alterar(estoque);
    }

    public static void removerEstoque(PedidoProduto pProd) throws SQLException, ClassNotFoundException {
        Estoque estoque = EstoqueDao.listarCodProduto(pProd.getProduto()).get(0);
        estoque.setQuantidade(estoque.getQuantidade() - pProd.getQuantidade());
        EstoqueDao.alterar(estoque);
    }
}
