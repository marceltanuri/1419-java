package ada.t1419.ecommerce.domain.service.pagamento;

import ada.t1419.ecommerce.domain.model.pedido.Pedido;

public class PagamentoServiceFake implements PagamentoService {

    @Override
    public boolean processarPagamento(Pedido pedido) {
        System.out.println("[PAGAMENTO] Processando pagamento para o pedido: " + pedido.getNumero());
        // Simula um pagamento bem-sucedido
        System.out.println("[PAGAMENTO] Pagamento aprovado!");
        return true;
    }
}
