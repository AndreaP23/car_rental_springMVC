package com.xantrix.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xantrix.webapp.domain.Veicolo;


public class VeicoloMapper implements RowMapper<Veicolo> {

    @Override
    public Veicolo mapRow(ResultSet row, int rowNum) throws SQLException {
        Veicolo veicolo = new Veicolo();
        try {
            veicolo.setIdVeicolo(row.getLong("veicolo_id"));
            veicolo.setMarca(row.getString("marca"));
            veicolo.setModello(row.getString("modello"));
            veicolo.setAnno(row.getInt("anno"));
            veicolo.setTarga(row.getString("targa"));
            veicolo.setDisponibilita(row.getInt("disponibilita"));
        } catch (Exception ex) {
            System.out.println("Errore!" + ex);
        }
        return veicolo;
    }
}
