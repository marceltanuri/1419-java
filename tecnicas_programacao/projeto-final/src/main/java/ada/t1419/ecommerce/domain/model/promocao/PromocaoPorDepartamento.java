package ada.t1419.ecommerce.domain.model.promocao;

import ada.t1419.ecommerce.domain.model.Departamento;
import ada.t1419.ecommerce.domain.model.pedido.Pedido;

import java.util.Map;

public class PromocaoPorDepartamento implements Promocao {

    private Departamento departamento;
    private Map<Integer, Double> faixasDeDesconto;

    public PromocaoPorDepartamento(Departamento departamento, Map<Integer, Double> faixasDeDesconto) {
        this.departamento = departamento;
        this.faixasDeDesconto = faixasDeDesconto;
    }

    @Override
    public double calcularDesconto(Pedido pedido) {
        int quantidadeNoDepartamento = pedido.contarItensPorDepartamento(departamento);

        double percentualDesconto = faixasDeDesconto.entrySet().stream()
                .filter(entry -> quantidadeNoDepartamento >= entry.getKey())
                .mapToDouble(Map.Entry::getValue)
                .max()
                .orElse(0.0);

        double valorTotalDepartamento = pedido.calcularValorTotalPorDepartamento(departamento);
        return valorTotalDepartamento * percentualDesconto;
    }
}
