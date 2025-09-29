# 📝 Map, Filter e Reduce: As Funções Essenciais

Este documento explica os três pilares da manipulação de coleções na **Programação Funcional** (`Map`, `Filter`, `Reduce`) e oferece exercícios práticos para fixar o aprendizado.

---

## 🚀 O que são Map, Filter e Reduce?

São Funções de Ordem Superior (Higher-Order Functions) usadas para processar dados em coleções (Arrays, Listas) de forma **declarativa** e **imutável** (sem alterar o *array* original).

### 1. `Map` (Transformação)

| Conceito | Descrição |
| :--- | :--- |
| **Objetivo** | Transformar cada elemento da lista. |
| **Retorno** | Uma **nova lista** de **mesmo tamanho**, com os elementos transformados. |
| **Função** | Recebe o elemento atual e deve retornar o **novo valor** desse elemento. |

### 2. `Filter` (Seleção/Filtro)

| Conceito | Descrição |
| :--- | :--- |
| **Objetivo** | Selecionar um subconjunto de elementos da lista. |
| **Retorno** | Uma **nova lista** contendo apenas os elementos que passaram no teste. |
| **Função** | Recebe o elemento atual e deve retornar um booleano (`true` para manter, `false` para descartar). |

### 3. `Reduce` (Redução/Agregação)

| Conceito | Descrição |
| :--- | :--- |
| **Objetivo** | Reduzir a lista a um **único valor** (soma, média, objeto, etc.). |
| **Retorno** | Um **único valor** (qualquer tipo: número, string, objeto, outra lista). |
| **Função** | Recebe o **acumulador** (resultado parcial) e o elemento atual, e deve retornar o **novo valor** do acumulador. |

---

## 💡 Exercícios Práticos

Use as listas de dados abaixo para resolver os exercícios.

```javascript
const numeros = [2, 5, 8, 1, 10, 7, 4];

const usuarios = [
  { nome: 'Ana', idade: 28 },
  { nome: 'Bruno', idade: 35 },
  { nome: 'Carla', idade: 22 },
  { nome: 'Diego', idade: 40 }
];
```

1. Exercícios com Map

    1.1 - Descrição: Dobre todos os valores na lista numeros.

    - Resultado Esperado: [4, 10, 16, 2, 20, 14, 8]

    1.2 - Descrição: Crie uma lista com apenas os nomes dos objetos usuarios.

    - Resultado Esperado: ['Ana', 'Bruno', 'Carla', 'Diego']


2. Exercícios com Filter
   
    2.1 - Descrição: Filtre a lista numeros para manter apenas os números maiores que 5.

    - Resultado Esperado: [8, 10, 7]

    2.2 - Descrição: Filtre a lista usuarios para manter apenas aqueles com idade igual ou superior a 30.

    - Resultado Esperado: [{nome: 'Bruno', idade: 35}, {nome: 'Diego', idade: 40}]

3. Exercícios com Reduce
    
    3.1 - Descrição: Calcule a soma total de todos os elementos na lista numeros.

    - Resultado Esperado: 37

    3.2 - Descrição: Encontre o objeto do usuário mais velho na lista usuarios.

    - Resultado Esperado: { nome: 'Diego', idade: 40 }

4. Exercício Combinado (Pipeline)
    
    4.1 - Descrição: Calcule a soma das idades de todos os usuários que têm menos de 35 anos.

    4.2 - Passos: 1. Filter (idade < 35) → 2. Map (obter apenas as idades) → 3. Reduce (somar as idades).

    - Resultado Esperado: 28 + 22 = 50








