import java.time.LocalDate;

public class RegistroProfissional {
    private LocalDate dataInicio;
    private LocalDate dataFim;

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
}
