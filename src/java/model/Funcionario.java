package model;

/**
 *
 * @author ccezar
 */
public class Funcionario extends Pessoa {
    private String cpf;
    private Cargo cargo;
    private float salario;
    
    public Funcionario(String cpf, String nome, String telefone, Cargo cargo, float salario) {
        super(nome, telefone);
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Funcionario(String cpf, Cargo cargo, String nome, String telefone, int numero, int codigo, String cep, String rua, String bairro, String cidade, String complemento, Estado estado, float salario) {
        super(nome, telefone, numero, codigo, cep, rua, bairro, cidade, complemento, estado);
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
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
