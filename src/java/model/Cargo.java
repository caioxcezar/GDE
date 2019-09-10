package model;

/**
 *
 * @author ccezar
 */
public class Cargo {
    private int codigo;
    private String nome, descricao;

    public Cargo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Cargo(int codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
