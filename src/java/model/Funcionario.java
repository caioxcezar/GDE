package model;

/**
 *
 * @author ccezar
 */
public class Funcionario extends Pessoa {
    private String cpf;
    private Cargo cargo;
    public Funcionario(String cpf, String nome, String telefone, Cargo cargo) {
        super(nome, telefone);
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public Funcionario(String cpf, Cargo cargo, String nome, String telefone, 
            int numero, int codigo, String cep, String rua, String bairro, String cidade, 
            String complemento) {
        super(nome, telefone, numero, codigo, cep, rua, bairro, cidade, complemento);
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
}
