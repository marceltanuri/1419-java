package ada.t1419.ecommerce.domain.model.pedido;

import ada.t1419.ecommerce.domain.model.Cliente;
import ada.t1419.ecommerce.domain.model.CupomDeDesconto;
import ada.t1419.ecommerce.domain.model.Produto;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import ada.t1419.ecommerce.domain.model.Departamento;
import java.util.stream.Collectors;


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

    // listar itens por departamento
    // qual estrutura de dados me ajuda quando eu preciso agrupar dados
    // Map é uma estrutura de dados que te ajuda nesse desafio
    // Map<K,V> mapa = new HashMap();


    // Mapa é uma estrutura de dados.
    // A qual organiza os dados por: Chave e Valor
    
    // Listas: Organiza os dados em sequencia
    // Filas: Tambem em sequencia a ordem de entrada e a ordem de saida sao especificas: FIFO
    // Pilhas: Tambem tem um ordem especifica de entrada e saida: LIFO
    // Set: Nao permite duplicatas
    // Tree: Organiza e ordena
    // Mapa: Organizaçao em Chave e Valor


    public Map<Departamento, List<ItemDePedido>> listarItensPorDepartamento() {
        return itens.stream()
                .collect(Collectors.groupingBy(item ->  item.produto().getDepartamento()));
    }

    public double calcularValorTotalPorDepartamento(Departamento departamento) {
        //use o obejto itens, e:
        // pode resolver com logica de programação basica
        // ou com programação funcional
        //     filter
        //     map
        //     reduce
        return 0.0;
    }


    public void aplicarCupomDesconto(CupomDeDesconto cupom) {
        this.cupomDeDesconto = cupom;
    }

    public Optional<CupomDeDesconto> getCupomDeDesconto() {
        return Optional.ofNullable(cupomDeDesconto);
    }

}