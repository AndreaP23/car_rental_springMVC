package com.xantrix.webapp.repository.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xantrix.webapp.domain.Prenotazione;
import com.xantrix.webapp.repository.PrenotazioneRepository;
import com.xantrix.webapp.repository.PrenotazioneMapper;

@Repository
public class PrenotazioneRepositoryImp implements PrenotazioneRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Prenotazione> listPrenotazione() {
        String sql = "SELECT * FROM prenotazioni";
        List<Prenotazione> prenotazioni = jdbcTemplate.query(sql, new PrenotazioneMapper());
        System.out.println("Numero di prenotazioni: " + prenotazioni.size());
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

    @Override
    public List<Prenotazione> listSelPrenotazione(String userId, String veicoloId, LocalDate dataInizio, LocalDate dataFine) {
        StringBuilder sql = new StringBuilder("SELECT p.* FROM prenotazioni p ")
                .append("JOIN veicolo v ON p.veicolo_id = v.veicolo_id ")
                .append("WHERE (1=1)"); // Iniziare con WHERE (1=1) per facilitare l'aggiunta di condizioni

        List<Object> params = new ArrayList<>();

        if (userId != null && !userId.isEmpty()) {
            sql.append(" AND p.user_id = ?");
            params.add(userId);
        }
        if (veicoloId != null && !veicoloId.isEmpty()) {
            sql.append(" AND p.veicolo_id = ?");
            params.add(veicoloId);
        }
        if (dataInizio != null) {
            sql.append(" AND p.data_inizio = ?");
            params.add(dataInizio);
        }
        if (dataFine != null) {
            sql.append(" AND p.data_fine = ?");
            params.add(dataFine);
        }

        return jdbcTemplate.query(sql.toString(), new PrenotazioneMapper(), params.toArray());
    }
}
