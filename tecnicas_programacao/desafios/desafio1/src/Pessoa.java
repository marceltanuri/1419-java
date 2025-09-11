import java.time.LocalDate;
import java.util.List;

public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private List<RegistroProfissional> registros;

    public Pessoa(String nome, LocalDate dataNascimento, Sexo sexo, List<RegistroProfissional> registros) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.registros = registros;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public List<RegistroProfissional> getRegistros() {
        return registros;
    }
}
