import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CalculadoraAposentadoria calculadora = new CalculadoraAposentadoria();

        // Cenário 1: Apto para aposentadoria
        RegistroProfissional regJoao1 = new RegistroProfissional(LocalDate.of(1985, 1, 1), LocalDate.of(2005, 1, 1));
        RegistroProfissional regJoao2 = new RegistroProfissional(LocalDate.of(2005, 1, 1), LocalDate.of(2022, 1, 1));
        Pessoa joao = new Pessoa("João da Silva", LocalDate.of(1960, 3, 10), Sexo.MASCULINO, Arrays.asList(regJoao1, regJoao2));

        // Cenário 2: Não apto (faltam idade e contribuição)
        RegistroProfissional regMaria1 = new RegistroProfissional(LocalDate.of(1995, 1, 1), LocalDate.of(2023, 1, 1));
        Pessoa maria = new Pessoa("Maria Souza", LocalDate.of(1967, 3, 5), Sexo.FEMININO, Arrays.asList(regMaria1));

        // Cenário 3: Não apto (falta idade)
        RegistroProfissional regCarlos1 = new RegistroProfissional(LocalDate.of(1983, 1, 1), LocalDate.of(2023, 1, 1));
        Pessoa carlos = new Pessoa("Carlos Pereira", LocalDate.of(1962, 7, 1), Sexo.MASCULINO, Arrays.asList(regCarlos1));

        // Cenário 4: Não apto (falta contribuição)
        RegistroProfissional regAna1 = new RegistroProfissional(LocalDate.of(1996, 1, 1), LocalDate.of(2023, 1, 1));
        Pessoa ana = new Pessoa("Ana Costa", LocalDate.of(1965, 8, 1), Sexo.FEMININO, Arrays.asList(regAna1));

        List<Pessoa> pessoas = Arrays.asList(joao, maria, carlos, ana);

        System.out.println("--- Verificando Cenários de Aposentadoria ---");
        System.out.println("Os resultados abaixo estão incorretos até que a lógica seja implementada.");
        System.out.println("---------------------------------------------------");

        for (Pessoa pessoa : pessoas) {
            String resultado = calculadora.verificarAposentadoria(pessoa);
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Resultado esperado: [O aluno deve verificar no README.md]");
            System.out.println("Resultado atual: " + resultado);
            System.out.println("---");
        }
    }
}
