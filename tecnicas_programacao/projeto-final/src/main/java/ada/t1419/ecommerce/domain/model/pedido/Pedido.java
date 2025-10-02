package ada.t1419.ecommerce.domain.model.pedido;

import ada.t1419.ecommerce.domain.model.Cliente;
import ada.t1419.ecommerce.domain.model.CupomDeDesconto;
import ada.t1419.ecommerce.domain.model.Departamento;
import ada.t1419.ecommerce.domain.model.Produto;

import java.util.Map;
import java.util.Optional;

public class Pedido {

    private String numero;
    private Cliente cliente;
    private ItensDePedido itens;
    private CupomDeDesconto cupomDeDesconto;

    public Pedido(String numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.itens = new ItensDePedido();
        this.cupomDeDesconto = null;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    // Métodos que delegam a manipulação de itens para a classe ItensDePedido
    public void adicionarItem(Produto produto, Integer quantidade) {
        this.itens.adicionar(produto, quantidade);
    }

    public void adicionarItem(Produto produto) {
        this.itens.adicionar(produto);
    }

    public void removerItem(Produto produto, Integer quantidade) {
        this.itens.remover(produto, quantidade);
    }

    public void removerItem(Produto produto) {
        this.itens.remover(produto);
    }

    public void excluirItem(Produto produto) {
        this.itens.excluir(produto);
    }

    public void limparItens() {
        this.itens.limpar();
    }

    public void paraCadaItem(java.util.function.BiConsumer<Produto, Integer> consumer) {
        this.itens.paraCada(consumer);
    }


    // Métodos que delegam os cálculos para a classe ItensDePedido
    public double calcularValorTotal() {
        return this.itens.calcularValorTotal();
    }

    public Map<Departamento, ItensDePedido> listarItensPorDepartamento() {
        return this.itens.listarItensPorDepartamento();
    }

    public double calcularValorTotalPorDepartamento(Departamento departamento) {
        return this.itens.calcularValorTotalPorDepartamento(departamento);
    }

    public int contarItensPorProduto(Produto produto) {
        return this.itens.contarItensPorProduto(produto);
    }

    public int contarItensPorDepartamento(Departamento departamento) {
        return this.itens.contarItensPorDepartamento(departamento);
    }


    // Métodos para manipulação do cupom de desconto
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
