package model;

/**
 *
 * @author ccezar
 */
public class Produto {
    private int codigo;
    private String nome, descricao, categoria;

    public Produto(String nome, String descricao, String categoria) {
        if (descricao.trim().equals("") || nome.trim().equals("") || categoria.trim().equals(""))
            throw new RuntimeException("Nenhum campo pode estar em branco");
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Produto(int codigo, String nome, String descricao, String categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
