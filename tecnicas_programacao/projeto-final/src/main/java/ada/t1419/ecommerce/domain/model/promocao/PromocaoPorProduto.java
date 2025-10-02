package ada.t1419.ecommerce.domain.model.promocao;

import ada.t1419.ecommerce.domain.model.Produto;
import ada.t1419.ecommerce.domain.model.pedido.Pedido;

import java.util.Map;

public class PromocaoPorProduto implements Promocao {

    private Produto produto;
    private Map<Integer, Double> faixasDeDesconto;

    public PromocaoPorProduto(Produto produto, Map<Integer, Double> faixasDeDesconto) {
        this.produto = produto;
        this.faixasDeDesconto = faixasDeDesconto;
    }

    @Override
    public double calcularDesconto(Pedido pedido) {
        int quantidadeDoProduto = pedido.contarItensPorProduto(produto);

        double percentualDesconto = faixasDeDesconto.entrySet().stream()
                .filter(entry -> quantidadeDoProduto >= entry.getKey())
                .mapToDouble(Map.Entry::getValue)
                .max()
                .orElse(0.0);

        return produto.getPreco() * quantidadeDoProduto * percentualDesconto;
    }
}
