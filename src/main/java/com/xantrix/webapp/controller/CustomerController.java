package com.xantrix.webapp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xantrix.webapp.domain.Prenotazione;
import com.xantrix.webapp.domain.Veicolo;
import com.xantrix.webapp.dto.PrenotazioneDTO;
import com.xantrix.webapp.repository.PrenotazioneRepository;
import com.xantrix.webapp.repository.VeicoloRepository;
import com.xantrix.webapp.response.PrenotazioneResponse;

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

    @PostMapping(value = "/salvaPrenotazione", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PrenotazioneResponse salvaPrenotazione(@ModelAttribute @Valid PrenotazioneDTO prenotazioneDTO, 
                                                  BindingResult result, 
                                                  Model model) {
        PrenotazioneResponse response = new PrenotazioneResponse();

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            response.setValidated(false);
            response.setErrorMessages(errors);
            return response;
        }

        Veicolo veicolo = veicoloRepository.findById(prenotazioneDTO.getVeicoloId());
        
        
        if (!validateVeicolo(veicolo, response)) return response;

        LocalDate localDataInizio = prenotazioneDTO.getDataInizio();
        LocalDate localDataFine = prenotazioneDTO.getDataFine();

        
        if (!validateDates(localDataInizio, localDataFine, response, veicolo)) return response;

        Prenotazione prenotazione = createPrenotazione(prenotazioneDTO, localDataInizio, localDataFine);
        try {
            prenotazioneRepository.save(prenotazione);
            veicolo.setDisponibilita(0);
            veicoloRepository.save(veicolo);
            response.setValidated(true);
            response.setSuccessMessage("Prenotazione effettuata con successo!");
        } catch (DataIntegrityViolationException e) {
            logger.error("Errore durante il salvataggio della prenotazione", e);
            response.setValidated(false);
            response.setErrorMessages(Map.of("general", "Errore durante la prenotazione. Riprova."));
        }

        return response;
    }

    
    private boolean validateVeicolo(Veicolo veicolo, PrenotazioneResponse response) {
        if (veicolo == null) {
            response.setValidated(false);
            response.setErrorMessages(Map.of("veicolo", "Veicolo non trovato."));
            return false;
        }
        if (veicolo.getDisponibilita() == 0) {
            response.setValidated(false);
            response.setErrorMessages(Map.of("veicolo", "Il veicolo non è disponibile."));
            return false;
        }
        return true;
    }

    private boolean validateDates(LocalDate startDate, LocalDate endDate, PrenotazioneResponse response, Veicolo veicolo) {
        if (startDate == null || endDate == null) {
            response.setValidated(false);
            response.setErrorMessages(Map.of("dates", "Le date non possono essere nulle."));
            return false;
        }

        if (startDate.isBefore(LocalDate.now().plusDays(1))) {
            response.setValidated(false);
            response.setErrorMessages(Map.of("startDate", "La data di inizio deve essere almeno il giorno successivo alla data odierna."));
            return false;
        }

        if (endDate.isBefore(startDate.plusDays(2))) {
            response.setValidated(false);
            response.setErrorMessages(Map.of("endDate", "La data di fine deve essere almeno due giorni dopo la data di inizio."));
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
