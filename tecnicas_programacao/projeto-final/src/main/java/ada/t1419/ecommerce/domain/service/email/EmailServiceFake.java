package ada.t1419.ecommerce.domain.service.email;

import ada.t1419.ecommerce.domain.model.pedido.Pedido;

public class EmailServiceFake implements EmailService {

    @Override
    public void enviarEmailConfirmacao(Pedido pedido) {
        System.out.println("[EMAIL] Enviando email de confirmação para: " + pedido.getCliente().getEmail());
        System.out.println("[EMAIL] Pedido " + pedido.getNumero() + " confirmado!");
    }
}
