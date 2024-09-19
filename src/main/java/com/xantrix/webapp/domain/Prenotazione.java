package com.xantrix.webapp.domain;

import java.io.Serializable;
import java.util.Date;

public class Prenotazione implements Serializable {

	private static final long serialVersionUID = 5070222554188197455L;

	private Long idPrenotazione;
	private Date dataPrenotazione;
	private Date dataInizio;
	private Date dataFine;
	
	// Chiavi esterne
    private Long userId;
    private Long veicoloId;
    
    
	public Prenotazione() {
		super();
	}
	public Long getIdPrenotazione() {
		return idPrenotazione;
	}
	public void setIdPrenotazione(Long idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}
	public Date getDataPrenotazione() {
		return dataPrenotazione;
	}
	public void setDataPrenotazione(Date dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
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
    
    
}
