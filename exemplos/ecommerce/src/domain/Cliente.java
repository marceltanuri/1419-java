package domain;

class Cliente{
   
   //atributos ou propriedades ou campos
    DadosPessoais dadosPessoais;

class DadosPessoais {
 String nome;
 String email;
 String documentoIdentificacao;
 String telefone;
 String endereco;
}

void limparDadosPessoais() {
    dadosPessoais = null;
}

}

