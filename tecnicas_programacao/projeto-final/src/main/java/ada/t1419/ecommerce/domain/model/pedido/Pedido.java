package ada.t1419.ecommerce.domain.model.pedido;

import java.util.List;
import ada.t1419.ecommerce.domain.model.Cliente;
import ada.t1419.ecommerce.domain.model.CupomDeDesconto;
import ada.t1419.ecommerce.domain.model.Produto;
import java.util.Optional;

public class Pedido {

    private String numero;
    private Cliente cliente;
    private List<ItemDePedido> itens;
    private CupomDeDesconto cupomDeDesconto;

    public Pedido(String numero, Cliente cliente, List<ItemDePedido> itens) {
        this.numero = numero;
        this.cliente = cliente;
        this.itens = itens;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemDePedido> getItens() {
        return itens;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        this.itens.add(new ItemDePedido(produto, quantidade));
    }

    public double calcularValorTotal() {
        double valorTotal = itens.stream()
                .mapToDouble(item -> item.produto().getPreco() * item.quantidade())
                .sum();
        
        if (cupomDeDesconto != null && cupomDeDesconto.isValido()) {
            valorTotal -= valorTotal * (cupomDeDesconto.getPercentualDesconto() / 100);
        }
        
        return valorTotal;
    }  

    public void removerItem(Produto produto) {
        // removeIf recebe por parâmetro um predicado (Predicate) (Interface Funcional que retorna boolean) permite o uso de expressões lambda
        this.itens.removeIf(item -> item.produto().equals(produto));
    }

    public void removerCupomDesconto() {
        this.cupomDeDesconto = null;
    }

    public void aplicarCupomDesconto(CupomDeDesconto cupom) {
        this.cupomDeDesconto = cupom;
    }

    public Optional<CupomDeDesconto> getCupomDeDesconto() {
        return Optional.ofNullable(cupomDeDesconto);
    }

}