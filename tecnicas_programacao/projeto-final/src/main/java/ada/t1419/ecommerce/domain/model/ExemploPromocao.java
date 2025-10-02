package ada.t1419.ecommerce.domain.model;

public class ExemploPromocao {

    public static void main(String[] args) {

        Produto produto1 = new Produto("P001", "Smartphone", "iPhone 14", 3000.0, 10, Departamento.ELETRONICO);
        Produto produto2 = new Produto("P002", "Camiseta", "Camiseta Básica", 50.0, 20, Departamento.VESTUARIO);

        Promocao promocaoDepartamento = new Promocao(
            "PROMO001",
            "Black Friday Eletrônicos",
            15.0,
            2,
            Departamento.ELETRONICO
        );

        Promocao promocaoProduto = new Promocao(
            "PROMO002",
            "Desconto Camisetas",
            10.0,
            3,
            "P002"
        );

        System.out.println("=== TESTANDO PROMOÇÕES ===\n");

        int quantidade1 = 3;
        double valorTotal1 = produto1.getPreco() * quantidade1;

        System.out.println("Produto: " + produto1.getNome());
        System.out.println("Quantidade: " + quantidade1);
        System.out.println("Valor original: R$ " + valorTotal1);

        if (promocaoDepartamento.seAplicaA(produto1, quantidade1)) {
            double valorComDesconto = promocaoDepartamento.calcularValorComDesconto(valorTotal1);
            double desconto = promocaoDepartamento.calcularDesconto(valorTotal1);
            System.out.println("Promoção aplicada: " + promocaoDepartamento.getDescricao());
            System.out.println("Desconto: R$ " + desconto);
            System.out.println("Valor final: R$ " + valorComDesconto);
        } else {
            System.out.println("Nenhuma promoção aplicável");
        }

        System.out.println("\n" + "=".repeat(40) + "\n");

        int quantidade2 = 4;
        double valorTotal2 = produto2.getPreco() * quantidade2;

        System.out.println("Produto: " + produto2.getNome());
        System.out.println("Quantidade: " + quantidade2);
        System.out.println("Valor original: R$ " + valorTotal2);

        if (promocaoProduto.seAplicaA(produto2, quantidade2)) {
            double valorComDesconto = promocaoProduto.calcularValorComDesconto(valorTotal2);
            double desconto = promocaoProduto.calcularDesconto(valorTotal2);
            System.out.println("Promoção aplicada: " + promocaoProduto.getDescricao());
            System.out.println("Desconto: R$ " + desconto);
            System.out.println("Valor final: R$ " + valorComDesconto);
        } else {
            System.out.println("Nenhuma promoção aplicável");
        }

        System.out.println("\n" + "=".repeat(40) + "\n");

        int quantidade3 = 2;
        double valorTotal3 = produto2.getPreco() * quantidade3;

        System.out.println("Produto: " + produto2.getNome());
        System.out.println("Quantidade: " + quantidade3);
        System.out.println("Valor original: R$ " + valorTotal3);

        if (promocaoProduto.seAplicaA(produto2, quantidade3)) {
            double valorComDesconto = promocaoProduto.calcularValorComDesconto(valorTotal3);
            double desconto = promocaoProduto.calcularDesconto(valorTotal3);
            System.out.println("Promoção aplicada: " + promocaoProduto.getDescricao());
            System.out.println("Desconto: R$ " + desconto);
            System.out.println("Valor final: R$ " + valorComDesconto);
        } else {
            System.out.println("Nenhuma promoção aplicável (quantidade mínima: " + promocaoProduto.getQuantidadeMinima() + ")");
        }

        System.out.println("\n=== INFORMAÇÕES DAS PROMOÇÕES ===\n");
        System.out.println(promocaoDepartamento);
        System.out.println(promocaoProduto);
    }
}