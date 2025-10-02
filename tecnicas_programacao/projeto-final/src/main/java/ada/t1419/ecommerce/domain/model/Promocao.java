package ada.t1419.ecommerce.domain.model;

public class Promocao {

    private String codigo;
    private String descricao;
    private TipoPromocao tipo;
    private double percentualDesconto;
    private int quantidadeMinima;

    private Departamento departamento;

    private String codigoProduto;

    public Promocao(String codigo, String descricao, double percentualDesconto,
                   int quantidadeMinima, Departamento departamento) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.percentualDesconto = percentualDesconto;
        this.quantidadeMinima = quantidadeMinima;
        this.departamento = departamento;
        this.tipo = TipoPromocao.POR_DEPARTAMENTO;
    }

       public Promocao(String codigo, String descricao, double percentualDesconto,
                   int quantidadeMinima, String codigoProduto) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.percentualDesconto = percentualDesconto;
        this.quantidadeMinima = quantidadeMinima;
        this.codigoProduto = codigoProduto;
        this.tipo = TipoPromocao.POR_PRODUTO;
    }


    public boolean seAplicaA(Produto produto, int quantidade) {
        if (quantidade < quantidadeMinima) {
            return false;
        }

        if (tipo == TipoPromocao.POR_DEPARTAMENTO) {
            return produto.getDepartamento() == departamento;
        } else {
            return produto.getCodigo().equals(codigoProduto);
        }
    }


    public double calcularDesconto(double valorOriginal) {
        return valorOriginal * (percentualDesconto / 100.0);
    }


    public double calcularValorComDesconto(double valorOriginal) {
        return valorOriginal - calcularDesconto(valorOriginal);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoPromocao getTipo() {
        return tipo;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    // Enum para identificar o tipo de promoção
    public enum TipoPromocao {
        POR_DEPARTAMENTO,
        POR_PRODUTO
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Promoção: ").append(descricao);
        sb.append(" - ").append(percentualDesconto).append("% de desconto");
        sb.append(" (mín. ").append(quantidadeMinima).append(" itens)");

        if (tipo == TipoPromocao.POR_DEPARTAMENTO) {
            sb.append(" - Departamento: ").append(departamento);
        } else {
            sb.append(" - Produto: ").append(codigoProduto);
        }

        return sb.toString();
    }
}