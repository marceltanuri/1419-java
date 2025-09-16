import java.time.LocalDate;

class CalculadoraAposentadoriaMulher implements CalcularAposentadoria {
    private final Pessoa pessoa;
    private static final int IDADE_MINIMA_APOSENTADORIA = 60;
    private static final int TEMPO_MINIMO_CONTRIBUICAO = 30;

    public CalculadoraAposentadoriaMulher(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String calcular() {
        boolean aptoPorIdade = !pessoa.getDataNascimento().plusYears(IDADE_MINIMA_APOSENTADORIA).isAfter(LocalDate.now());

        //int anosContribuicao = pessoa.getAnosContribuicao();

        if (aptoPorIdade) {
            return "Apto para aposentadoria";
        } else {
            return "Não apto para aposentadoria";
        }
    }


}