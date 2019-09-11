package model;

/**
 *
 * @author ccezar
 */
public class Cliente extends Pessoa {
    private int cnpj;

    public Cliente(int cnpj, String nome, String telefone) {
        super(nome, telefone);
        this.cnpj = cnpj;
    }

    public Cliente(int cnpj, String nome, String telefone, int numero, int codigo, int cep, String rua, String bairro, String cidade, String complemento) {
        super(nome, telefone, numero, codigo, cep, rua, bairro, cidade, complemento);
        this.cnpj = cnpj;
    }
    

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }
    
    
}
