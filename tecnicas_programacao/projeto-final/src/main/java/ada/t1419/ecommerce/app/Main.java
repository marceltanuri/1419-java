package ada.t1419.ecommerce.app;

import ada.t1419.ecommerce.domain.model.Cliente;
import ada.t1419.ecommerce.domain.model.Departamento;
import ada.t1419.ecommerce.domain.model.Produto;
import ada.t1419.ecommerce.domain.model.pedido.Pedido;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ada.t1419.ecommerce.domain.service.ImpressoraDePedidos;
import ada.t1419.ecommerce.domain.service.PromocaoService;
import ada.t1419.ecommerce.domain.model.promocao.PromocaoPorDepartamento;
import ada.t1419.ecommerce.domain.model.promocao.PromocaoPorProduto;

import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        //criar um novo item de pedido
        Produto produto1 = new Produto("P001", "Notebook", "Notebook Gamer", 5000.00, 10, Departamento.ELETRONICO);
        Produto produto2 = new Produto("P002", "Mouse", "Mouse Gamer", 150.00, 50, Departamento.ELETRONICO);
        Produto produto3 = new Produto("P003", "Camiseta", "Camiseta de algodão", 50.00, 200, Departamento.VESTUARIO);
        Produto produto4 = new Produto("P004", "Java Efetivo 3a edição", "Livro sobre Java", 120.00, 50, Departamento.LIVROS);

        Cliente cliente = new Cliente("Tanuri", "tanuri@email.com", LocalDate.of(1990, 1, 20));

        Pedido pedido = new Pedido("PED123", cliente);

        pedido.adicionarItem(produto1, 2);
        pedido.adicionarItem(produto2, 1);
        pedido.adicionarItem(produto3, 2);
        pedido.adicionarItem(produto4, 1);

        PromocaoService promocaoService = new PromocaoService();

        Map<Integer, Double> faixasEletronicos = new HashMap<>();
        faixasEletronicos.put(2, 0.10); // 10% de desconto para 2 ou mais itens
        promocaoService.adicionarPromocao(new PromocaoPorDepartamento(Departamento.ELETRONICO, faixasEletronicos));

        Map<Integer, Double> faixasNotebook = new HashMap<>();
        faixasNotebook.put(2, 0.05); // 5% de desconto para 2 ou mais notebooks
        promocaoService.adicionarPromocao(new PromocaoPorProduto(produto1, faixasNotebook));

        String relatorio = ImpressoraDePedidos.INSTANCE.imprimirPedidoFormatado(pedido, promocaoService);
        System.out.println(relatorio);


    }
}