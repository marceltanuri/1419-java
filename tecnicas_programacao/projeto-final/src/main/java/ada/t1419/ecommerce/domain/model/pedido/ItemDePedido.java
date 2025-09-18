package ada.t1419.ecommerce.domain.model.pedido;

import ada.t1419.ecommerce.domain.model.Produto;

public record ItemDePedido (Produto produto, int quantidade) {
}

// um record já tem os métodos equals, hashCode e toString implementados automaticamente