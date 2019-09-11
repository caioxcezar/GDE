package model;

import java.sql.Date;

/**
 *
 * @author ccezar
 */
class Venda {
    private int codigo;
    private Date data;
    private Cliente cliente;
    private Pedido pedido;
    private Funcionario funcionario;
    private boolean pago;
    private float total;

    public Venda(int codigo, Date data, Cliente cliente, Pedido pedido, Funcionario funcionario, boolean pago, float total) {
        this.codigo = codigo;
        this.data = data;
        this.cliente = cliente;
        this.pedido = pedido;
        this.funcionario = funcionario;
        this.pago = pago;
        this.total = total;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
