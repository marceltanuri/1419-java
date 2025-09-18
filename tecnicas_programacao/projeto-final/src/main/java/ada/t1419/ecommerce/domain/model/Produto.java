package ada.t1419.ecommerce.domain.model;


public class Produto{

    private String codigo;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeEstoque;

    public Produto(String codigo, String nome, String descricao, double preco, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCodigo() {
        return codigo;
    }   

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void baixarEstoque(int quantidade) {
        if (quantidade > quantidadeEstoque) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }
        this.quantidadeEstoque -= quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' + 
                ", preco=" + preco +
                ", quantidadeEstoque=" + quantidadeEstoque +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return codigo.equals(produto.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

}