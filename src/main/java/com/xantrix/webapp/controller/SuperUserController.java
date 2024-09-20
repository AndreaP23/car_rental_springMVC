package com.xantrix.webapp.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.protobuf.TextFormat.ParseException;
import com.xantrix.webapp.domain.Prenotazione;
import com.xantrix.webapp.domain.User;
import com.xantrix.webapp.repository.PrenotazioneRepository;
import com.xantrix.webapp.repository.UserRepository;

@Controller
public class SuperUserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping("/listuser")
	public String listUsers(HttpSession session, Model model){
		List<User> users = userRepository.listUser();
		model.addAttribute("users",users);
		User user = (User) session.getAttribute("user");
		if (user != null && user.getRuolo() == 1){
			return "listuser";
        } else {
            return "redirect:/login"; 
        }
	}
	
	@GetMapping("/listprenotazioni")
	public String listPrenotazione(@RequestParam(required = false) String userId,
	                               @RequestParam(required = false) String veicoloId,
	                               @RequestParam(required = false) 
	                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInizio,
	                               @RequestParam(required = false) 
	                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFine,
	                               HttpSession session, Model model) {
	    User user = (User) session.getAttribute("user");
	    if (user != null && user.getRuolo() == 1) {
	        List<Prenotazione> prenotazioni;

	        if (userId == null && veicoloId == null && dataInizio == null && dataFine == null) {
	            prenotazioni = prenotazioneRepository.listPrenotazione(); 
	        } else {
	            prenotazioni = prenotazioneRepository.listSelPrenotazione(userId, veicoloId, dataInizio, dataFine); 
	        }

	        model.addAttribute("prenotazioni", prenotazioni);
	        model.addAttribute("userId", userId);
	        model.addAttribute("veicoloId", veicoloId);
	        model.addAttribute("dataInizio", dataInizio);
	        model.addAttribute("dataFine", dataFine);

	        return "listprenotazioni";
	    } else {
	        return "redirect:/"; 
	    }
	}

}
