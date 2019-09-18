package model;

/**
 *
 * @author ccezar
 */
public class Cliente extends Pessoa {
    private String cnpj;

    public Cliente(String cnpj, String nome, String telefone) {
        super(nome, telefone);
        this.cnpj = cnpj;
    }

    public Cliente(String cnpj, String nome, String telefone, int numero, int codigo, 
            String cep, String rua, String bairro, String cidade, String complemento) {
        super(nome, telefone, numero, codigo, cep, rua, bairro, cidade, complemento);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
    
}
