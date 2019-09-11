package model;

import java.sql.Date;

/**
 *
 * @author ccezar
 */
public class NotaFiscal {
    private int nota;
    private Date data;
    private Venda venda;

    public NotaFiscal(int nota, Date data, Venda venda) {
        this.nota = nota;
        this.data = data;
        this.venda = venda;
    }

    
    
    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
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
