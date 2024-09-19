package com.xantrix.webapp.repository.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xantrix.webapp.domain.Prenotazione;
import com.xantrix.webapp.domain.Veicolo;
import com.xantrix.webapp.repository.PrenotazioneRepository;
import com.xantrix.webapp.repository.PrenotazioneMapper;

@Repository
public class PrenotazioneRepositoryImp implements PrenotazioneRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Prenotazione> listPrenotazione() {
		String sql = "SELECT * FROM prenotazioni";
		List<Prenotazione> prenotazioni =jdbcTemplate.query(sql, new PrenotazioneMapper());
		//Test:
		System.out.println("Numerro di prenotazioni " + prenotazioni.size());
		return prenotazioni;
	}
	
	 public void save(Prenotazione prenotazione) {
	        String sql = "INSERT INTO prenotazioni (user_id, veicolo_id, data_prenotazione, data_inizio, data_fine) VALUES (?, ?, ?, ?, ?)";

	        jdbcTemplate.update(sql,
	            prenotazione.getUserId(),
	            prenotazione.getVeicoloId(),
	            prenotazione.getDataPrenotazione(),
	            prenotazione.getDataInizio(),
	            prenotazione.getDataFine());
	    }

}
