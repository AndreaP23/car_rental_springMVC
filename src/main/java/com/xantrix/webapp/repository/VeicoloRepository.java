package com.xantrix.webapp.repository;

import java.util.List;

import com.xantrix.webapp.domain.Veicolo;

public interface VeicoloRepository {

	List<Veicolo> listVeicolo();
	Veicolo findById(Long veicoloId);
	void save(Veicolo veicolo);
}
