package model;

/**
 *
 * @author ccezar
 */
public class Funcionario extends Pessoa {
    private int cpf;
    private String cargo;
    public Funcionario(int cpf, String nome, String telefone, String cargo) {
        super(nome, telefone);
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public Funcionario(int cpf, String cargo, String nome, String telefone, int numero, String rua, String bairro, String cidade, String cep, String complemento) {
        super(nome, telefone, numero, rua, bairro, cidade, cep, complemento);
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
}
