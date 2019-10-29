package model;

import java.sql.Date;

/**
 *
 * @author ccezar
 */
public class NotaFiscal {
    private int codigo;
    private Date data;
    private Pedido pedido;

    public NotaFiscal(int codigo, Date data, Pedido pedido) {
        this.codigo = codigo;
        this.data = data;
        this.pedido = pedido;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setVenda(Pedido pedido) {
        this.pedido = pedido;
    }
    
}
