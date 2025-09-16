import java.time.LocalDate;
import java.util.List;

public class Pessoa {
    private final String nome;
    private final LocalDate dataNascimento;
    private final Sexo sexo;
    private final List<RegistroProfissional> registros;

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

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo=" + sexo +
                ", registros=" + registros +
                '}';
    }

    public String calcularAposentadoria() {
        CalcularAposentadoria calculadora;
        if (this.sexo == Sexo.MASCULINO) {
            calculadora = new CalculadoraAposentadoriaHomem(this);
        } else {
            calculadora = new CalculadoraAposentadoriaMulher(this);
        }
        return calculadora.calcular();
    }
}
