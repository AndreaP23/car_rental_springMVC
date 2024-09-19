package com.xantrix.webapp.repository;

import java.util.List;

import com.xantrix.webapp.domain.Prenotazione;

public interface PrenotazioneRepository {

	List<Prenotazione> listPrenotazione();
	void save(Prenotazione prenotazione);
}
