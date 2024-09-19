package com.xantrix.webapp.repository.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xantrix.webapp.domain.Veicolo;
import com.xantrix.webapp.repository.VeicoloMapper;
import com.xantrix.webapp.repository.VeicoloRepository;

@Repository
public class VeicoloRepositoryImp implements VeicoloRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Veicolo> listVeicolo(){
		String sql = "SELECT * FROM veicolo WHERE disponibilita=1";
		List<Veicolo> veicolo =jdbcTemplate.query(sql, new VeicoloMapper());
		//Test:
		//System.out.println("Number of users retrieved: " + user.size());
		return veicolo;
	}

	@Override
	public Veicolo findById(Long veicoloId) {
		String sql = "SELECT * FROM veicolo WHERE veicolo_id= ?";
		try{
			return jdbcTemplate.queryForObject(sql, new Object[]{veicoloId}, new VeicoloMapper());
		}catch(Exception e) {
            return null;
        }
    }
	
	@Override
	public void save(Veicolo veicolo) {
        String sql = "UPDATE veicolo SET marca = ?, modello = ?, anno = ?, targa = ?, disponibilita = ? WHERE veicolo_id = ?";
        try {
            jdbcTemplate.update(sql,
                veicolo.getMarca(),
                veicolo.getModello(),
                veicolo.getAnno(),
                veicolo.getTarga(),
                veicolo.getDisponibilita(),
                veicolo.getIdVeicolo());
        } catch (Exception e) {
            System.out.println("errore " + e);
        }
    }
}
