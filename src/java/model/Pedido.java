package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ccezar
 */
public class Pedido {
    private int codigo;
    private ArrayList<Produto> produtos;
    private Cliente cliente;
    private double valor;
    private Date data;

    public Pedido(ArrayList<Produto> produtos, Cliente cliente, double valor, Date data) {
        this.produtos = produtos;
        this.cliente = cliente;
        this.valor = valor;
        this.data = data;
    }

    public int getId() {
        return codigo;
    }

    public void setId(int id) {
        this.codigo = id;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
