package ada.t1419.ecommerce.domain.service.pagamento;

import ada.t1419.ecommerce.domain.model.pedido.Pedido;

public interface PagamentoService {

    boolean processarPagamento(Pedido pedido);

}
