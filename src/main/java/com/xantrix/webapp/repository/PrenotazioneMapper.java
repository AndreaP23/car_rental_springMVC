package com.xantrix.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.jdbc.core.RowMapper;

import com.xantrix.webapp.domain.Prenotazione;

public class PrenotazioneMapper implements RowMapper<Prenotazione> {

    @Override
    public Prenotazione mapRow(ResultSet row, int rowNum) throws SQLException {
        Prenotazione prenotazione = new Prenotazione();
        try {
            prenotazione.setIdPrenotazione(row.getLong("prenotazione_id"));
            prenotazione.setDataPrenotazione(convertToLocalDate(row.getDate("data_prenotazione")));
            prenotazione.setDataInizio(convertToLocalDate(row.getDate("data_inizio")));
            prenotazione.setDataFine(convertToLocalDate(row.getDate("data_fine")));
            prenotazione.setUserId(row.getLong("user_id"));
            prenotazione.setVeicoloId(row.getLong("veicolo_id"));
        } catch (SQLException ex) {
            System.err.println("Errore durante il mapping della prenotazione: " + ex.getMessage());
            throw ex; 
        }
        return prenotazione;
    }

    private LocalDate convertToLocalDate(java.util.Date date) {
        return (date != null) ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }
}
