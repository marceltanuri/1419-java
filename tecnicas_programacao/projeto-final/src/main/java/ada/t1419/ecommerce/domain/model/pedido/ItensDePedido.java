package ada.t1419.ecommerce.domain.model.pedido;

import ada.t1419.ecommerce.domain.model.Departamento;
import ada.t1419.ecommerce.domain.model.Produto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class ItensDePedido {

    Map<Produto, Integer> itens;

    public ItensDePedido() {
        this.itens = new HashMap<>();
    }

    private ItensDePedido(Map<Produto, Integer> itens) {
        this.itens = itens;
    }

    public void paraCada(BiConsumer<Produto, Integer> consumer) {
        this.itens.forEach(consumer);
    }

    public void adicionar(Produto produto, Integer quantidade) {
        int quantidadeJaAdicionada = this.itens.getOrDefault(produto, 0);
        if (produto.getQuantidadeEstoque() < quantidade + quantidadeJaAdicionada) {
            throw new IllegalArgumentException("Não é possível adicionar a quantidade desejada do produto '"
                    + produto.getNome() + "', pois não há estoque suficiente. " +
                    "Quantidade disponível: " + produto.getQuantidadeEstoque() + ", " +
                    "Quantidade já adicionada no carrinho: " + quantidadeJaAdicionada + ", " +
                    "Quantidade solicitada: " + quantidade);
        }
        this.itens.merge(produto, quantidade, Integer::sum);
    }

    public void adicionar(Produto produto) {
        this.adicionar(produto, 1);
    }

    public void remover(Produto produto, Integer quantidade) {
        this.itens.computeIfPresent(produto, (p, oldValue) -> {
            if (oldValue <= quantidade) {
                return null;
            }
            return oldValue - quantidade;
        });
    }

    public void remover(Produto produto) {
        this.remover(produto, 1);
    }

    public void excluir(Produto produto) {
        this.itens.remove(produto);
            
    }

    public void limpar() {
        this.itens.clear();
    }

    public double calcularValorTotal() {
        return this.itens.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPreco() * entry.getValue())
                .sum();
    }   

    public Map<Departamento, ItensDePedido> listarItensPorDepartamento() {
        return this.itens.entrySet().stream()
                .collect(Collectors.groupingBy(
                        entry -> entry.getKey().getDepartamento(),
                        Collectors.collectingAndThen(
                                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue),
                                ItensDePedido::new
                        )
                ));
    }

    public double calcularValorTotalPorDepartamento(Departamento departamento){
        return this.itens.entrySet().stream()
                .filter(entry -> entry.getKey().getDepartamento().equals(departamento))
                .mapToDouble(entry -> entry.getKey().getPreco() * entry.getValue())
                .sum();
    }

    public int contarItensPorProduto(Produto produto) {
        return this.itens.getOrDefault(produto, 0);
    }

    public int contarItensPorDepartamento(Departamento departamento) {
        return this.itens.entrySet().stream()
                .filter(entry -> entry.getKey().getDepartamento().equals(departamento))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
    
}