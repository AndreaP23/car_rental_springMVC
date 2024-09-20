package com.xantrix.webapp.repository;

import java.time.LocalDate;
import java.util.List;

import com.xantrix.webapp.domain.Prenotazione;

public interface PrenotazioneRepository {

	List<Prenotazione> listPrenotazione();
	void save(Prenotazione prenotazione);
	List<Prenotazione> listSelPrenotazione(String userId, String veicoloId, LocalDate dataInizio, LocalDate dataFine);
}
