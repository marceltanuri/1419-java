package ada.t1419.ecommerce.application.service;

import ada.t1419.ecommerce.domain.model.pedido.Pedido;
import ada.t1419.ecommerce.domain.repository.ProdutoRepository;

public class EstoqueService {

    private final ProdutoRepository produtoRepository;

    public EstoqueService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void darBaixaEstoque(Pedido pedido) {
        System.out.println("[ESTOQUE] Dando baixa no estoque para o pedido: " + pedido.getNumero());
        pedido.paraCadaItem((produto, quantidade) -> {
            System.out.println("[ESTOQUE] Baixando " + quantidade + " unidades do produto: " + produto.getNome());
            produto.baixarEstoque(quantidade);
            produtoRepository.save(produto);
        });
        System.out.println("[ESTOQUE] Baixa de estoque concluída!");
    }
}
