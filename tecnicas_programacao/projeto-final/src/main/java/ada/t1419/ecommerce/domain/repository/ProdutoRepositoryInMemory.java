package ada.t1419.ecommerce.domain.repository;

import ada.t1419.ecommerce.domain.model.Produto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProdutoRepositoryInMemory implements ProdutoRepository {

    private final Map<String, Produto> produtos = new HashMap<>();

    @Override
    public Optional<Produto> findById(String id) {
        return Optional.ofNullable(produtos.get(id)).map(produto ->
                new Produto(produto.getCodigo(), produto.getNome(), produto.getDescricao(),
                        produto.getPreco(), produto.getQuantidadeEstoque(), produto.getDepartamento()));
    }

    @Override
    public void save(Produto produto) {
        produtos.put(produto.getCodigo(), new Produto(produto.getCodigo(), produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getQuantidadeEstoque(), produto.getDepartamento()));
    }
}
