package com.xantrix.webapp.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Prenotazione implements Serializable {

    private static final long serialVersionUID = 5070222554188197455L;

    private Long idPrenotazione;
    private LocalDate dataPrenotazione;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    
    // Chiavi esterne
    private Long userId;
    private Long veicoloId;

    public Prenotazione() {
        super();
    }

    public Prenotazione(Long idPrenotazione, LocalDate dataPrenotazione, LocalDate dataInizio, LocalDate dataFine, Long userId, Long veicoloId) {
        this.idPrenotazione = idPrenotazione;
        this.dataPrenotazione = dataPrenotazione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.userId = userId;
        this.veicoloId = veicoloId;
    }

    public Long getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(Long idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVeicoloId() {
        return veicoloId;
    }

    public void setVeicoloId(Long veicoloId) {
        this.veicoloId = veicoloId;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "idPrenotazione=" + idPrenotazione +
                ", dataPrenotazione=" + dataPrenotazione +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", userId=" + userId +
                ", veicoloId=" + veicoloId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prenotazione)) return false;

        Prenotazione that = (Prenotazione) o;

        return idPrenotazione != null && idPrenotazione.equals(that.idPrenotazione);
    }

    @Override
    public int hashCode() {
        return idPrenotazione != null ? idPrenotazione.hashCode() : 0;
    }
}
