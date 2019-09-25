package model;

import java.util.ArrayList;

/**
 *
 * @author ccezar
 */
public class EstoqueProduto {
private int quantidade, codigo;
    private Produto produto;

    public EstoqueProduto(int quantidade, int codigo, Produto produto) {
        this.quantidade = quantidade;
        this.codigo = codigo;
        this.produto = produto;
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
