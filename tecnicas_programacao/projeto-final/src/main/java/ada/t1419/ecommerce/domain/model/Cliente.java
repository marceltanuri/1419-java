package ada.t1419.ecommerce.domain.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.regex.Pattern;

public class Cliente {

    private static final int IDADE_MINIMA = 18;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    private final String nome;
    private final String email;
    private final LocalDate dataNascimento;

    public Cliente(String nome, String email, LocalDate dataNascimento) {
        Objects.requireNonNull(nome, "O nome não pode ser nulo.");
        Objects.requireNonNull(email, "O e-mail não pode ser nulo.");
        Objects.requireNonNull(dataNascimento, "A data de nascimento não pode ser nula.");

        if (!isEmailValido(email)) {
            throw new IllegalArgumentException("Formato de e-mail inválido: " + email);
        }

        if (!isMaiorDeIdade(dataNascimento)) {
            throw new IllegalArgumentException("O cliente deve ter no mínimo " + IDADE_MINIMA + " anos.");
        }

        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
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

    private static boolean isEmailValido(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private static boolean isMaiorDeIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears() >= IDADE_MINIMA;
    }

    @Override
    public String toString() {
        return "Cliente{"
                + "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
