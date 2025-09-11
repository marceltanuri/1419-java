import java.time.LocalDate;

public class Cliente {

    private DadosPessoais dadosPessoais;
    private LocalDate dataDoCadastro;

    public Cliente(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
        // simula a data de cadastro com data aleatória
        int numero = (int) (Math.random() * (30 - 1 + 1)) + 1;
        this.dataDoCadastro = LocalDate.now().minusDays(numero);
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dadosPessoais=" + dadosPessoais +
                ", dataDoCadastro=" + dataDoCadastro +
                '}';
    }

    public LocalDate getDataDoCadastro() {
        return dataDoCadastro;
    }
}
