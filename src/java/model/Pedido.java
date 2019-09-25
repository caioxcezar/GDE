package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ccezar
 */
public class Pedido {

    private int codigo;
    private ArrayList<PedidoProduto> produtos;
    private Funcionario funcionario;
    private Cliente cliente;
    private double valor;
    private Date data;
    private String estado, tipo;

    public Pedido() {
    }

    public Pedido(int codigo, ArrayList<PedidoProduto> produtos, Funcionario funcionario, Cliente cliente, double valor, Date data, String estado, String tipo) {
        this.codigo = codigo;
        this.produtos = produtos;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.valor = valor;
        this.data = data;
        this.estado = estado;
        this.tipo = tipo;
    }

    
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return codigo;
    }

    public void setId(int id) {
        this.codigo = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<PedidoProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<PedidoProduto> produtos) {
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
