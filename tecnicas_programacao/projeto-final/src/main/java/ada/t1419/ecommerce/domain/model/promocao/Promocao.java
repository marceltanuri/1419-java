package ada.t1419.ecommerce.domain.model.promocao;

import ada.t1419.ecommerce.domain.model.pedido.Pedido;

public interface Promocao {

    double calcularDesconto(Pedido pedido);

}
