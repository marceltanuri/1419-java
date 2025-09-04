package domain;

class PedidoDeCompra {

 Cliente cliente;
 List<ItemDoPedido> itens;
 Date dataCriacao;
 StatusPedido status;

enum StatusPedido {
 ABERTO,
 AGUARDANDO_PAGAMENTO,
 PAGO,
 FINALIZADO
}

}