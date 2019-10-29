package model;
/**
 *
 * @author ccezar
 */
public class PedidoProduto {

    private int codigo, quantidade;
    private Produto produto;

    public PedidoProduto(int codigo, int quantidade, Produto produto) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public PedidoProduto(int quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
        
}
