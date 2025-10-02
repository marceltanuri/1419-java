package ada.t1419.ecommerce.app;

import ada.t1419.ecommerce.domain.model.Cliente;
import ada.t1419.ecommerce.domain.model.Departamento;
import ada.t1419.ecommerce.domain.model.Produto;
import ada.t1419.ecommerce.domain.model.pedido.Pedido;
import ada.t1419.ecommerce.domain.model.promocao.PromocaoPorDepartamento;
import ada.t1419.ecommerce.domain.model.promocao.PromocaoPorProduto;
import ada.t1419.ecommerce.domain.repository.ProdutoRepository;
import ada.t1419.ecommerce.domain.repository.ProdutoRepositoryInMemory;
import ada.t1419.ecommerce.application.service.EstoqueService;
import ada.t1419.ecommerce.application.service.FinalizarPedidoService;
import ada.t1419.ecommerce.domain.service.ImpressoraDePedidos;
import ada.t1419.ecommerce.domain.service.PromocaoService;
import ada.t1419.ecommerce.domain.service.email.EmailService;
import ada.t1419.ecommerce.domain.service.email.EmailServiceFake;
import ada.t1419.ecommerce.domain.service.pagamento.PagamentoService;
import ada.t1419.ecommerce.domain.service.pagamento.PagamentoServiceFake;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Produto produto1 = new Produto("P001", "Notebook", "Notebook Gamer", 5000.00, 10, Departamento.ELETRONICO);
        Produto produto2 = new Produto("P002", "Mouse", "Mouse Gamer", 150.00, 50, Departamento.ELETRONICO);
        Produto produto3 = new Produto("P003", "Camiseta", "Camiseta de algodão", 50.00, 200, Departamento.VESTUARIO);
        Produto produto4 = new Produto("P004", "Java Efetivo 3a edição", "Livro sobre Java", 120.00, 1, Departamento.LIVROS);

        ProdutoRepository produtoRepository = new ProdutoRepositoryInMemory();
        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
        produtoRepository.save(produto3);
        produtoRepository.save(produto4);

        Cliente cliente = new Cliente("Tanuri", "tanuri@email.com", LocalDate.of(1990, 1, 20));

        System.out.println(">>> Tentando adicionar 2 unidades do livro (estoque: 1)");
        Pedido pedido = new Pedido("PED123", cliente);
        try {
            pedido.adicionarItem(produto4, 2); // This will fail, stock is 1
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao adicionar item ao pedido: " + e.getMessage());
        }

        System.out.println("\n>>> Adicionando itens com quantidade válida ao pedido");
        pedido.adicionarItem(produto1, 1);
        pedido.adicionarItem(produto2, 1);
        pedido.adicionarItem(produto3, 1);
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

        System.out.println("\n>>> Simulando compra concorrente do último livro...");
        Produto livroDoEstoque = produtoRepository.findById("P004").get();
        livroDoEstoque.baixarEstoque(1);
        produtoRepository.save(livroDoEstoque);
        System.out.println(">>> Estoque do livro atualizado para: " + produtoRepository.findById("P004").get().getQuantidadeEstoque());


        PagamentoService pagamentoService = new PagamentoServiceFake();
        EstoqueService estoqueService = new EstoqueService(produtoRepository);
        EmailService emailService = new EmailServiceFake();

        FinalizarPedidoService finalizarPedidoService = new FinalizarPedidoService(
                pagamentoService, estoqueService, emailService, produtoRepository
        );

        try {
            System.out.println("\n>>> Tentando finalizar o pedido...");
            finalizarPedidoService.finalizar(pedido);
        } catch (IllegalStateException e) {
            System.err.println("Erro ao finalizar o pedido: " + e.getMessage());
        }
    }
}