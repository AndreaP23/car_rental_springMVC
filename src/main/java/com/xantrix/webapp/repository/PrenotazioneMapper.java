package com.xantrix.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xantrix.webapp.domain.Prenotazione;
import com.xantrix.webapp.domain.User;

public class PrenotazioneMapper implements RowMapper<Prenotazione> {

	@Override
	 public Prenotazione mapRow(ResultSet row, int rowNum) throws SQLException {
        Prenotazione prenotazione = new Prenotazione();
        try {
        	 prenotazione.setIdPrenotazione(row.getLong("prenotazione_id"));
        	 prenotazione.setDataPrenotazione(row.getDate("data_prenotazione"));
        	 prenotazione.setDataInizio(row.getDate("data_inizio"));
        	 prenotazione.setDataFine(row.getDate("data_fine"));
        	 prenotazione.setUserId(row.getLong("user_id"));
        	 prenotazione.setVeicoloId(row.getLong("veicolo_id"));
        	 
        } catch (Exception ex) {
            System.out.println("Errore!" + ex);
        }
        return prenotazione;
    }
}
