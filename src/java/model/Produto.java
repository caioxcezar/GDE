package model;

/**
 *
 * @author ccezar
 */
public class Produto {
    private int codigo;
    private String nome, descricao;
    private Categoria categoria;
    private float valor;

    public Produto(int codigo, String nome, String descricao, Categoria categoria, float valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
