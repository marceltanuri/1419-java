package ada.t1419.ecommerce.app;

import ada.t1419.ecommerce.domain.model.Departamento;
import ada.t1419.ecommerce.domain.model.Produto;
import ada.t1419.ecommerce.domain.model.pedido.ItemDePedido;
import ada.t1419.ecommerce.domain.model.pedido.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //criar um novo item de pedido
        Produto produto1 = new Produto("P001", "Notebook", "Notebook Gamer", 5000.00, 10, Departamento.ELETRONICO);
        Produto produto2 = new Produto("P002", "Mouse", "Mouse Gamer", 150.00, 50, Departamento.ELETRONICO);
        Produto produto3 = new Produto("P003", "Camiseta", "Camiseta de algodão", 50.00, 200, Departamento.VESTUARIO);
        Produto produto4 = new Produto("P004", "Java Efetivo 3a edição", "Livro sobre Java", 120.00, 50, Departamento.LIVROS);

        Pedido pedido = new Pedido("PED123", null, new ArrayList<>());

        pedido.adicionarItem(produto1, 2);
        pedido.adicionarItem(produto2, 1);
        pedido.adicionarItem(produto3, 2);
        pedido.adicionarItem(produto4, 1);


        Map<Departamento, List<ItemDePedido>> itensPorDepartamento = pedido.listarItensPorDepartamento();

        System.out.println("Itens por departamento:");
        
        itensPorDepartamento.forEach((departamento, itens) -> {
            System.out.println("Departamento: " + departamento);
            System.out.println("Itens:");
            System.out.println("Valor total do departamento: " + pedido.calcularValorTotalPorDepartamento(departamento));
            itens.forEach(item -> System.out.println(item));
            System.out.println("-------------------------------");
        });

        System.out.println("Valor total do pedido: " + pedido.calcularValorTotal());

        // Desde a versão 8, introduziu-se o Stream junto com Interfaces Funcionais

        // getCupomDeDesconto é um optional, invocar isValido se cupom estiver presente
        if (pedido.getCupomDeDesconto().isPresent()) {
            System.out.println("Cupom de desconto aplicado: " + pedido.getCupomDeDesconto().get().getCodigo());
            System.out.println(pedido.getCupomDeDesconto().get().isValido() ? "Cupom válido" : "Cupom inválido");
        } else {
            System.out.println("Nenhum cupom de desconto aplicado.");
        }
    }
}