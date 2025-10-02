package ada.t1419.ecommerce.domain.service;

import ada.t1419.ecommerce.domain.model.pedido.Pedido;
import ada.t1419.ecommerce.domain.model.promocao.Promocao;

import java.util.ArrayList;
import java.util.List;

public class PromocaoService {

    private final List<Promocao> promocoes;

    public PromocaoService() {
        this.promocoes = new ArrayList<>();
    }

    public void adicionarPromocao(Promocao promocao) {
        this.promocoes.add(promocao);
    }

    public double calcularDescontos(Pedido pedido) {
        return promocoes.stream()
                .mapToDouble(promocao -> promocao.calcularDesconto(pedido))
                .sum();
    }
}
