package model;

/**
 *
 * @author ccezar
 */
public class Funcionario extends Pessoa {
    private int cpf;
    private Cargo cargo;
    public Funcionario(int cpf, String nome, String telefone, Cargo cargo) {
        super(nome, telefone);
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public Funcionario(int cpf, Cargo cargo, String nome, String telefone, int numero, String rua, String bairro, String cidade, int cep, String complemento) {
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
}
