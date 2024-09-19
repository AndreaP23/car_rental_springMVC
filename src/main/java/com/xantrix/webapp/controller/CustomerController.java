package com.xantrix.webapp.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xantrix.webapp.domain.Prenotazione;
import com.xantrix.webapp.domain.Veicolo;
import com.xantrix.webapp.repository.PrenotazioneRepository;
import com.xantrix.webapp.repository.VeicoloRepository;

@Controller
public class CustomerController {
	
	@Autowired
	private VeicoloRepository veicoloRepository;
	
	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	
	@GetMapping("/listveicoli")
	public String listVeicoli(Model model) {
		List<Veicolo> veicoli = veicoloRepository.listVeicolo();
		model.addAttribute("veicoli", veicoli);
		return "listveicoli";
	}
	
	@GetMapping("/prenotaVeicolo")
	public String prenotaVeicolo(@RequestParam("veicoloId") Long veicoloId, Model model) {
	    Veicolo veicolo = veicoloRepository.findById(veicoloId);
	    if (veicolo != null) {
	        model.addAttribute("veicolo", veicolo);
	        return "prenotazioneVeicolo";
	    } else {
	        model.addAttribute("errorMessage", "Veicolo non trovato.");
	        return "listveicoli";
	    }
	}

	@PostMapping("/salvaPrenotazione")
	public String salvaPrenotazione(@RequestParam("veicoloId") Long veicoloId,
	                                @RequestParam("userId") Long userId,
	                                @RequestParam("dataInizio") String dataInizioStr,
	                                @RequestParam("dataFine") String dataFineStr,
	                                Model model) {
	    Veicolo veicolo = veicoloRepository.findById(veicoloId);
	    if (veicolo == null) {
	        model.addAttribute("errorMessage", "Veicolo non trovato.");
	        return "prenotazioneVeicolo";
	    }
	    
	    if (veicolo.getDisponibilita() == 0) {
	        model.addAttribute("errorMessage", "Il veicolo non è disponibile.");
	        return "prenotazioneVeicolo";
	    }

	    Date dataInizio;
	    Date dataFine;
	    try {
	        LocalDate localDataInizio = LocalDate.parse(dataInizioStr);
	        LocalDate localDataFine = LocalDate.parse(dataFineStr);
	        
	        if (localDataInizio.isBefore(LocalDate.now().plusDays(1))) {
	            model.addAttribute("errorMessage", "La data di inizio deve essere almeno il giorno successivo alla data odierna.");
	            return "prenotazioneVeicolo";
	        }
	        
	        if (localDataFine.isBefore(localDataInizio.plusDays(2))) {
	            model.addAttribute("errorMessage", "La data di fine deve essere almeno due giorni dopo la data di inizio.");
	            return "prenotazioneVeicolo";
	        }
	        
	        dataInizio = Date.valueOf(localDataInizio);
	        dataFine = Date.valueOf(localDataFine);
	    } catch (DateTimeParseException e) {
	        model.addAttribute("errorMessage", "Formato data non valido.");
	        return "prenotazioneVeicolo";
	    }

	    Prenotazione prenotazione = new Prenotazione();
	    prenotazione.setVeicoloId(veicoloId);
	    prenotazione.setUserId(userId);
	    prenotazione.setDataPrenotazione(new Date(System.currentTimeMillis())); 
	    prenotazione.setDataInizio(dataInizio);
	    prenotazione.setDataFine(dataFine); 
	    
	    prenotazioneRepository.save(prenotazione);
	    
	    veicolo.setDisponibilita(0); 
	    veicoloRepository.save(veicolo);

	    return "redirect:/listveicoli";
	}
}
