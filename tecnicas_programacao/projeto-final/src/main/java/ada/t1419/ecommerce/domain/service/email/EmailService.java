package ada.t1419.ecommerce.domain.service.email;

import ada.t1419.ecommerce.domain.model.pedido.Pedido;

public interface EmailService {

    void enviarEmailConfirmacao(Pedido pedido);

}
