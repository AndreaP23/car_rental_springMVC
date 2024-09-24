package com.xantrix.webapp.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xantrix.webapp.domain.User;
import com.xantrix.webapp.repository.UserRepository;
import com.xantrix.webapp.service.UserService;

@Controller
public class HomeController
{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private UserService userService;
	
	
	@RequestMapping(value="/")
	public String getWelcome2(Model model)
	{
		return "login";
	}
	
	@PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        User user = userService.authenticate(email, password);
        if (user != null) {
        	session.setAttribute("user", user);
            if (user.getRuolo() == 1) {
                return "redirect:/schermataSuperUser";
            } else if (user.getRuolo() == 2) {
                return "redirect:/schermataCustomer";
            }
        } else {
        	System.out.println("Email o password errati.");
            model.addAttribute("errorMessage", "Email o password errati.");
            return "login";
        }
        return "login";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
	    return "login";  
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();  
	    }
	    return "redirect:/login";  
	}	
	
	@GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }
	
	@PostMapping("/register")
    public String registerUser(@RequestParam String nome,
                               @RequestParam String cognome,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String telefono,
                               @RequestParam String dataNascita,
                               Model model) {
        User user = new User();
        user.setNome(nome);
        user.setCognome(cognome);
        user.setEmail(email);
        user.setPassword(password); 
        user.setTelefono(telefono);
        user.setDataNascita(dataNascita != null ? java.sql.Date.valueOf(dataNascita) : null);
        user.setRuolo(2); 
        
        try { 
            userRepository.saveUser(user);
            model.addAttribute("successMessage", "Registrazione completata con successo!");
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                model.addAttribute("errorMessage", "Email già in uso. Riprova con un'email diversa.");
            } else {
                model.addAttribute("errorMessage", "Errore durante la registrazione. Riprova.");
            }
            return "register"; 
        }

        return "register";
    }
	
	
    @GetMapping("/schermataSuperUser")
    public String showSuperUserPage(HttpSession session, Model model) {
    	User user = (User) session.getAttribute("user");
        if (user != null && user.getRuolo() == 1) {
            return "schermataSuperUser"; 
        } else {
            return "redirect:/login"; 
        }
    }

    @GetMapping("/schermataCustomer")
    public String showCustomerPage(HttpSession session, Model model) {
    	User user = (User) session.getAttribute("user");
        if (user != null && user.getRuolo() == 2) {
            return "schermataCustomer"; 
        } else {
            return "redirect:/login"; 
        }
    }

}