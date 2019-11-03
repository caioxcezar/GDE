package model;

import java.sql.Date;

/**
 *
 * @author ccezar
 */
public class Estoque {

    private int codigo, quantidade;
    private Produto produto;
    private Date data, dataAlteracao;
    private Pedido pedido;

    public Estoque(int codigo, int quantidade, Produto produto, Date data, Pedido pedido) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.produto = produto;
        this.data = data;
        this.pedido = pedido;
    }

    public Estoque(int codigo, int quantidade, Produto produto, Date data, Pedido pedido, Date dataAlteracao) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.produto = produto;
        this.data = data;
        this.dataAlteracao = dataAlteracao;
        this.pedido = pedido;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Pedido getPedido() {
        return this.pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
