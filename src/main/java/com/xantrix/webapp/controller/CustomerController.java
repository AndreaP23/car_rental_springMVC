package com.xantrix.webapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xantrix.webapp.domain.Prenotazione;
import com.xantrix.webapp.domain.Veicolo;
import com.xantrix.webapp.dto.PrenotazioneDTO;
import com.xantrix.webapp.repository.PrenotazioneRepository;
import com.xantrix.webapp.repository.VeicoloRepository;

@Controller
public class CustomerController {

    @Autowired
    private VeicoloRepository veicoloRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

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
            model.addAttribute("prenotazioneDTO", new PrenotazioneDTO());
            return "prenotazioneVeicolo";
        } else {
            model.addAttribute("errorMessage", "Veicolo non trovato.");
            return "listveicoli";
        }
    }

    @PostMapping("/salvaPrenotazione")
    public String salvaPrenotazione(@ModelAttribute PrenotazioneDTO prenotazioneDTO, Model model) {
        Veicolo veicolo = veicoloRepository.findById(prenotazioneDTO.getVeicoloId());
        
        if (!validateVeicolo(veicolo, model)) return "prenotazioneVeicolo";

        LocalDate localDataInizio = prenotazioneDTO.getDataInizio();
        LocalDate localDataFine = prenotazioneDTO.getDataFine();

        if (!validateDates(localDataInizio, localDataFine, model, veicolo)) return "prenotazioneVeicolo";

        Prenotazione prenotazione = createPrenotazione(prenotazioneDTO, localDataInizio, localDataFine);
        prenotazioneRepository.save(prenotazione);

        veicolo.setDisponibilita(0);
        veicoloRepository.save(veicolo);

        return "redirect:/listveicoli";
    }

    private boolean validateVeicolo(Veicolo veicolo, Model model) {
        if (veicolo == null) {
            model.addAttribute("errorMessage", "Veicolo non trovato.");
            return false;
        }
        if (veicolo.getDisponibilita() == 0) {
            model.addAttribute("errorMessage", "Il veicolo non è disponibile.");
            return false;
        }
        return true;
    }

    private boolean validateDates(LocalDate startDate, LocalDate endDate, Model model, Veicolo veicolo) {
        if (startDate == null || endDate == null) {
            model.addAttribute("errorMessage", "Le date non possono essere nulle.");
            model.addAttribute("veicolo", veicolo);
            return false;
        }

        if (startDate.isBefore(LocalDate.now().plusDays(1))) {
            model.addAttribute("errorMessage", "La data di inizio deve essere almeno il giorno successivo alla data odierna.");
            model.addAttribute("veicolo", veicolo);
            return false;
        }

        if (endDate.isBefore(startDate.plusDays(2))) {
            model.addAttribute("errorMessage", "La data di fine deve essere almeno due giorni dopo la data di inizio.");
            model.addAttribute("veicolo", veicolo);
            return false;
        }

        return true;
    }

    private Prenotazione createPrenotazione(PrenotazioneDTO prenotazioneDTO, LocalDate startDate, LocalDate endDate) {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setVeicoloId(prenotazioneDTO.getVeicoloId());
        prenotazione.setUserId(prenotazioneDTO.getUserId());
        prenotazione.setDataPrenotazione(LocalDate.now()); 
        prenotazione.setDataInizio(startDate); 
        prenotazione.setDataFine(endDate); 
        return prenotazione;
    }
}
