import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {

        DadosPessoais dadosPessoais = new DadosPessoais("Marcel", "0011223343", LocalDate.of(1990, Month.APRIL, 12));
        Cliente cliente = new Cliente(dadosPessoais);

        System.out.println(LocalDate.now());
        System.out.println(cliente.getDataDoCadastro());
        long diasDesdeADataDeCadastro = ChronoUnit.DAYS.between(cliente.getDataDoCadastro(), LocalDate.now());

        System.out.println(cliente.getDadosPessoais().getNome() + " cadastrado na plataforma há " + diasDesdeADataDeCadastro + " dias.");
    }

}
