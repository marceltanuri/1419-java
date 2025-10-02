package ada.t1419.ecommerce.domain.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Cliente {

    private String documento;
    private String nome;
    private String email;
    private LocalDate dataNascimento;

    public Cliente(String documento, String nome, String email, LocalDate dataNascimento) {
        this.documento = documento;
        this.nome = nome;
        this.email = email;
        if(isMaiorDeIdade(dataNascimento)){
            this.dataNascimento = dataNascimento;
        }
    }

    public boolean isMaiorDeIdade(LocalDate dataNascimento) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        LocalDate hoje = LocalDate.now();

        if (dataNascimento == null) {
            System.out.println("Data de nascimento não pode ser nula.");
            return false;
        }else if(dataNascimento.isAfter(hoje)) {
            System.out.println("Data de nascimento inválida: " + dataNascimento.format(formatter));
            return false;
        }else{
            Period periodo = Period.between(dataNascimento, hoje);
            if(periodo.getYears() < 18){
                System.out.println("Cadastro não permitido, cliente menor de idade! Idade do cliente: " + periodo.getYears() + " anos.");
                return false;
            }
        }

        Period periodo = Period.between(dataNascimento, hoje);
        System.out.println("Cadastro permitido, cliente maior de 18 anos! Idade do cliente: " + periodo.getYears() + " anos.");
        return periodo.getYears() >= 18;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void detalhesCliente(){
        System.out.println("----- Detalhes do Cliente -----");
        System.out.println("Documento: " + documento);
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Data de Nascimento: " + dataNascimento);
        System.out.println("-------------------------------");
    }
}
