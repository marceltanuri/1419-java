import java.time.LocalDate;

public class DadosPessoais {

    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;

    public DadosPessoais(String nome, String cpf, LocalDate dataDeNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    @Override
    public String toString() {
        return "DadosPessoais{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                '}';
    }
}
