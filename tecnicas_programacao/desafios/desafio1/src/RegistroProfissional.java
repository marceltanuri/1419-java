import java.time.LocalDate;

public class RegistroProfissional {
    private final LocalDate dataInicio;
    private final LocalDate dataFim;

    public RegistroProfissional(LocalDate dataInicio, LocalDate dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    @Override
    public String toString() {
        return "RegistroProfissional{" +
                "dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                '}';
    }
}
