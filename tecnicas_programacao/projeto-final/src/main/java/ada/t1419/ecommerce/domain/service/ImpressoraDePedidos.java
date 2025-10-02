package ada.t1419.ecommerce.domain.service;

import ada.t1419.ecommerce.domain.model.CupomDeDesconto;
import ada.t1419.ecommerce.domain.model.Departamento;
import ada.t1419.ecommerce.domain.model.pedido.ItensDePedido;
import ada.t1419.ecommerce.domain.model.pedido.Pedido;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

public enum ImpressoraDePedidos {

    INSTANCE;

    private static final DateTimeFormatter FORMATADOR_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final String SEPARADOR = "========================================\n";

    public String imprimirPedidoFormatado(Pedido pedido, PromocaoService promocaoService) {
        StringBuilder sb = new StringBuilder();
        imprimirCabecalho(sb, pedido);
        imprimirItens(sb, pedido);
        imprimirRodape(sb, pedido, promocaoService);
        return sb.toString();
    }

    private void imprimirCabecalho(StringBuilder sb, Pedido pedido) {
        String dataImpressao = LocalDateTime.now().format(FORMATADOR_DATA_HORA);
        sb.append(SEPARADOR);
        sb.append("Pedido: ").append(pedido.getNumero()).append("\n");
        sb.append("Cliente: ").append(pedido.getCliente().getNome())
          .append(" (").append(pedido.getCliente().getEmail()).append(")\n");
        sb.append("Impresso em: ").append(dataImpressao).append("\n");
        sb.append(SEPARADOR);
        sb.append("\n");
    }

    private void imprimirItens(StringBuilder sb, Pedido pedido) {
        Map<Departamento, ItensDePedido> itensPorDepartamento = pedido.listarItensPorDepartamento();
        itensPorDepartamento.forEach((departamento, itensDePedido) -> {
            sb.append("--- DEPARTAMENTO: ").append(departamento.name()).append(" ---\n");
            itensDePedido.paraCada((produto, quantidade) -> {
                String precoFormatado = String.format("R$ %.2f", produto.getPreco());
                sb.append("  - ").append(produto.getNome())
                  .append(" (").append(precoFormatado).append(")")
                  .append(" x ").append(quantidade).append("\n");
            });
            sb.append("\n");
        });
    }

    private void imprimirRodape(StringBuilder sb, Pedido pedido, PromocaoService promocaoService) {
        sb.append(SEPARADOR);
        double valorTotal = pedido.calcularValorTotal();
        double descontoCupom = calcularDescontoCupom(pedido, valorTotal);
        double descontoPromocoes = promocaoService.calcularDescontos(pedido);

        imprimirLinhaDesconto(sb, "Desconto Cupom", descontoCupom, pedido.getCupomDeDesconto().map(CupomDeDesconto::getCodigo));
        imprimirLinhaDesconto(sb, "Desconto Promoções", descontoPromocoes, Optional.empty());

        double valorTotalDescontos = descontoCupom + descontoPromocoes;
        String totalFormatado = String.format("R$ %.2f", valorTotal - valorTotalDescontos);
        sb.append("Valor Total: ").append(totalFormatado).append("\n");
        sb.append(SEPARADOR);
    }

    private double calcularDescontoCupom(Pedido pedido, double valorTotal) {
        return pedido.getCupomDeDesconto()
                .map(cupom -> valorTotal * cupom.getPercentualDesconto())
                .orElse(0.0);
    }

    private void imprimirLinhaDesconto(StringBuilder sb, String label, double valorDesconto, Optional<String> codigo) {
        String descontoFormatado = String.format("R$ %.2f", valorDesconto);
        sb.append(label).append(": ").append(descontoFormatado);
        codigo.ifPresent(c -> sb.append(" (").append(c).append(")"));
        sb.append("\n");
    }
}