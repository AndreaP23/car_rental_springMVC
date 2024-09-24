package com.xantrix.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.xantrix.webapp.domain.Prenotazione;

public class PrenotazioneMapper implements RowMapper<Prenotazione> {

    @Override
    public Prenotazione mapRow(ResultSet row, int rowNum) throws SQLException {
        Prenotazione prenotazione = new Prenotazione();
        try {
            prenotazione.setIdPrenotazione(row.getLong("prenotazione_id"));
            prenotazione.setDataPrenotazione(row.getDate("data_prenotazione").toLocalDate());
            prenotazione.setDataInizio(row.getDate("data_inizio").toLocalDate());
            prenotazione.setDataFine(row.getDate("data_fine").toLocalDate());
            prenotazione.setUserId(row.getLong("user_id"));
            prenotazione.setVeicoloId(row.getLong("veicolo_id"));
        } catch (SQLException ex) {
            System.err.println("Errore durante il mapping della prenotazione: " + ex.getMessage());
            throw ex;
        }
        return prenotazione;
    }
    
    
  //Passo l'istanza Date e lo converto in LocalDate
    /*
    private LocalDate convertToLocalDate(java.util.Date date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else if (date != null) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            return null;
        }
    }
}*/
}
