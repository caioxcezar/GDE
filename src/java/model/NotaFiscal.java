package model;

import java.sql.Date;

/**
 *
 * @author ccezar
 */
public class NotaFiscal {
    private int codigo;
    private Date data;
    private Venda venda;

    public NotaFiscal(int codigo, Date data, Venda venda) {
        this.codigo = codigo;
        this.data = data;
        this.venda = venda;
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
}
