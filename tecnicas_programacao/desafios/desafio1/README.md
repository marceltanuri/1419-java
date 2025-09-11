# Desafio: Calculadora de Aposentadoria

## Descrição

Crie um sistema de linha de comando para calcular se uma pessoa pode se aposentar com base em seu histórico de contribuição, data de nascimento e sexo biológico. O sistema deve utilizar a biblioteca `java.time` para todas as manipulações de data.

## Requisitos

O sistema deve ser capaz de:

1.  Receber os dados de uma pessoa:
    *   Nome
    *   Data de nascimento (`LocalDate`)
    *   Sexo biológico (`Enum: MASCULINO, FEMININO`)

2.  Receber uma lista de registros profissionais. Cada registro deve conter:
    *   Data de início (`LocalDate`)
    *   Data de fim (`LocalDate`)

3.  Calcular o tempo total de contribuição somando os períodos de todos os registros profissionais. O resultado deve ser expresso em anos, meses e dias.

4.  Verificar se a pessoa atende aos critérios de aposentadoria com base nas regras abaixo.

5.  Exibir uma mensagem clara para o usuário informando:
    *   O tempo total de contribuição.
    *   A idade da pessoa.
    *   Se a pessoa tem direito à aposentadoria.
    *   Se não tiver direito, informar quanto tempo falta (em anos, meses e dias) para atingir os critérios.

## Regras para Aposentadoria (Simplificadas para este desafio)

Para se aposentar, a pessoa deve atender **ambos** os critérios de idade e tempo de contribuição mínimo.

*   **Homens:**
    *   Idade mínima: 64 anos
    *   Tempo de contribuição mínimo: 35 anos

*   **Mulheres:**
    *   Idade mínima: 59 anos
    *   Tempo de contribuição mínimo: 30 anos

## Estrutura de Dados Sugerida

Você pode criar classes para organizar os dados:

```java
public enum Sexo {
    MASCULINO,
    FEMININO
}
```

```java
public class RegistroProfissional {
    private LocalDate dataInicio;
    private LocalDate dataFim;

    // Construtores, getters e setters
}
```

```java
public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private List<RegistroProfissional> registros;

    // Construtores, getters e setters
}
```

## Dicas

*   Use `LocalDate` para representar as datas.
*   Utilize `Period` ou `ChronoUnit` para calcular a diferença entre as datas e determinar a idade e o tempo de contribuição.
*   `Period.between(dataInicio, dataFim)` pode ser útil para calcular a duração de cada registro profissional.
*   Lembre-se que a classe `Period` possui os métodos `getYears()`, `getMonths()` e `getDays()` para obter os componentes do período. A soma de períodos pode não ser trivial, então pense em como agregar os anos, meses e dias de forma correta. Uma abordagem pode ser converter tudo para dias, somar e depois converter de volta para anos, meses e dias.

## Exemplo de Saída

**Nota:** Os cálculos abaixo consideram a data atual como **10 de setembro de 2025**.

### Cenário 1: Aprovado

```
Nome: João da Silva
Idade: 65 anos, 6 meses e 0 dias
Tempo de contribuição: 37 anos, 0 meses e 0 dias
Status: Apto para aposentadoria.
```

### Cenário 2: Reprovado (ambos os critérios)

```
Nome: Maria Souza
Idade: 58 anos, 6 meses e 5 dias
Tempo de contribuição: 28 anos, 0 meses e 0 dias
Status: Não apto para aposentadoria.
Tempo restante para aposentadoria:
- Faltam 0 anos, 5 meses e 25 dias para atingir a idade mínima.
- Faltam 2 anos, 0 meses e 0 dias para atingir o tempo de contribuição mínimo.
```

### Cenário 3: Reprovado (idade insuficiente)

```
Nome: Carlos Pereira
Idade: 63 anos, 2 meses e 9 dias
Tempo de contribuição: 40 anos, 0 meses e 0 dias
Status: Não apto para aposentadoria.
Tempo restante para aposentadoria:
- Faltam 0 anos, 9 meses e 21 dias para atingir a idade mínima.
- Tempo de contribuição mínimo já atingido.
```

### Cenário 4: Reprovado (contribuição insuficiente)

```
Nome: Ana Costa
Idade: 60 anos, 1 mês e 9 dias
Tempo de contribuição: 27 anos, 0 meses e 0 dias
Status: Não apto para aposentadoria.
Tempo restante para aposentadoria:
- Idade mínima já atingida.
- Faltam 3 anos, 0 meses e 0 dias para atingir o tempo de contribuição mínimo.
```

Boa sorte!