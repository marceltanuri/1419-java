package ada.t1419.ecommerce.domain.model;

import java.time.LocalDateTime;

public class CupomDeDesconto {

    private String codigo;
    private double percentualDesconto;
    private LocalDateTime dataValidade;

    public CupomDeDesconto(String codigo, double percentualDesconto, LocalDateTime dataValidade) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.dataValidade = dataValidade;
    }

    public String getCodigo() {
        return codigo;
    }   

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDateTime getDataValidade() {
        return dataValidade;
    }

    public boolean isValido() {
        return LocalDateTime.now().isBefore(dataValidade);  
    }

}