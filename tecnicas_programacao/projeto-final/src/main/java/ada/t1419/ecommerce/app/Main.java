package ada.t1419.ecommerce.app;

import java.util.ArrayList;
import ada.t1419.ecommerce.domain.model.Produto;
import ada.t1419.ecommerce.domain.model.pedido.ItemDePedido;
import ada.t1419.ecommerce.domain.model.pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        //criar um novo item de pedido
        Produto produto1 = new Produto("P001", "Notebook", "Notebook Gamer", 5000.00, 10);
        Produto produto2 = new Produto("P002", "Mouse", "Mouse Gamer", 150.00, 50);     
        ItemDePedido item1 = new ItemDePedido(produto1, 2);
        ItemDePedido item2 = new ItemDePedido(produto2, 1);
        Pedido pedido = new Pedido("PED123", null, new ArrayList<>());
        
        pedido.adicionarItem(produto1, 2);
        pedido.adicionarItem(produto2, 1);
        
        System.out.println("Item 1: " + item1);
        System.out.println("Item 2: " + item2);
        System.out.println("Valor total do pedido: " + pedido.calcularValorTotal());
        
        // getCupomDeDesconto é um optional, invocar isValido se cupom estiver presente 
        if(pedido.getCupomDeDesconto().isPresent()) {
            System.out.println("Cupom de desconto aplicado: " + pedido.getCupomDeDesconto().get().getCodigo());
            System.out.println(pedido.getCupomDeDesconto().get().isValido() ? "Cupom válido" : "Cupom inválido");
        } else {
            System.out.println("Nenhum cupom de desconto aplicado.");
        }
    }
}