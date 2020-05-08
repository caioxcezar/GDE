package service;

import dao.PedidoProdutoDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Pedido;
import model.PedidoProduto;

/**
 *
 * @author caioc
 */
public class PedidoProdutoService {
    private EstoqueService estoqueService = new EstoqueService();
    
    private boolean containsProduto(ArrayList<PedidoProduto> produtos, PedidoProduto produto) {
        return produtos.stream()
                .filter(e -> e.getProduto().getCodigo() == produto.getProduto().getCodigo())
                .findFirst()
                .isPresent();
    }

    protected void updateProdutos(Pedido pedidoAntes, ArrayList<PedidoProduto> produtos) throws SQLException, ClassNotFoundException {
        for (PedidoProduto pprod0 : produtos) {
            PedidoProduto p = null;
            int qtd = 0;
            for (PedidoProduto pprod1 : pedidoAntes.getProdutos()) {
                if (pprod0.getProduto().getCodigo() == pprod1.getProduto().getCodigo()) {
                    p = pprod0;
                    p.setCodigo(pprod1.getCodigo());
                    qtd = pprod1.getQuantidade() - p.getQuantidade();
                }
            }
            if (p != null) {
                PedidoProdutoDao.alterar(p, pedidoAntes);
                p.setQuantidade(qtd);
                estoqueService.addEstoque(p);
            } else {
                PedidoProdutoDao.salvar(pprod0, pedidoAntes);
                estoqueService.rmEstoque(pprod0);
            }
        }

        for (PedidoProduto pprod0 : pedidoAntes.getProdutos()) {
            if (!containsProduto(produtos, pprod0)) {
                PedidoProdutoDao.apagar(pprod0);
                estoqueService.addEstoque(pprod0);
            }
        }
    }

}
