package ada.t1419.ecommerce.application.service;

import ada.t1419.ecommerce.domain.model.pedido.Pedido;
import ada.t1419.ecommerce.domain.repository.ProdutoRepository;
import ada.t1419.ecommerce.domain.service.email.EmailService;
import ada.t1419.ecommerce.domain.service.pagamento.PagamentoService;

public class FinalizarPedidoService {

    private final PagamentoService pagamentoService;
    private final EstoqueService estoqueService;
    private final EmailService emailService;
    private final ProdutoRepository produtoRepository;

    public FinalizarPedidoService(PagamentoService pagamentoService,
                                  EstoqueService estoqueService,
                                  EmailService emailService,
                                  ProdutoRepository produtoRepository) {
        this.pagamentoService = pagamentoService;
        this.estoqueService = estoqueService;
        this.emailService = emailService;
        this.produtoRepository = produtoRepository;
    }

    public void finalizar(Pedido pedido) {
        System.out.println("[FINALIZAR_PEDIDO] Iniciando finalização do pedido: " + pedido.getNumero());

        pedido.paraCadaItem((produto, quantidade) -> {
            produtoRepository.findById(produto.getCodigo())
                    .ifPresentOrElse(produtoDoEstoque -> {
                        if (produtoDoEstoque.getQuantidadeEstoque() < quantidade) {
                            throw new IllegalStateException("O produto '" + produto.getNome() + "' não possui estoque suficiente para completar a compra. " +
                                    "Quantidade disponível: " + produtoDoEstoque.getQuantidadeEstoque() + ", Quantidade no pedido: " + quantidade);
                        }
                    }, () -> {
                        throw new IllegalStateException("O produto '" + produto.getNome() + "' não foi encontrado no estoque.");
                    });
        });

        if (pagamentoService.processarPagamento(pedido)) {
            estoqueService.darBaixaEstoque(pedido);
            new Thread(() -> emailService.enviarEmailConfirmacao(pedido)).start();
            System.out.println("[FINALIZAR_PEDIDO] Pedido finalizado com sucesso!");
        } else {
            System.out.println("[FINALIZAR_PEDIDO] Falha no pagamento. O pedido não foi finalizado.");
        }
    }
}
