package model;

/**
 *
 * @author ccezar
 */
public class PedidoProduto extends Produto {
    private int quantidade;

    public PedidoProduto(int quantidade, int codigo, String nome, 
            String descricao, Categoria categoria, float valor) {
        super(codigo, nome, descricao, categoria, valor);
        this.quantidade = quantidade;
    }
}
