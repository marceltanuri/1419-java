package ada.t1419.ecommerce.domain.repository;

import ada.t1419.ecommerce.domain.model.Produto;

import java.util.Optional;

public interface ProdutoRepository {

    Optional<Produto> findById(String id);

    void save(Produto produto);

}
