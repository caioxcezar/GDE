package model;
/**
 *
 * @author ccezar
 */
public class PedidoProduto {

    private int quantidade, codigo, codigoPedido;
    private Produto produto;

    public PedidoProduto(int quantidade, int codigo, int codigoPedido, Produto produto) {
        this.quantidade = quantidade;
        this.codigo = codigo;
        this.codigoPedido = codigoPedido;
        this.produto = produto;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
}
