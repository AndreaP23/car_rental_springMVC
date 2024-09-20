package com.xantrix.webapp.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class PrenotazioneDTO {
    private Long veicoloId;
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInizio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFine;


    // Getter e setter
    public Long getVeicoloId() {
        return veicoloId;
    }
    public void setVeicoloId(Long veicoloId) {
        this.veicoloId = veicoloId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public LocalDate getDataInizio() {
        return dataInizio;
    }
    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }
    public LocalDate getDataFine() {
        return dataFine;
    }
    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }
}
