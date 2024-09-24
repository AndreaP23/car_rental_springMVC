package com.xantrix.webapp.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xantrix.webapp.domain.User;
import com.xantrix.webapp.dto.UserDTO;
import com.xantrix.webapp.repository.UserRepository;
import com.xantrix.webapp.response.UserResponse;
import com.xantrix.webapp.service.UserService;

@Controller
public class HomeController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/")
    public String getWelcome(Model model) {
        return "login";
    }
    
    @GetMapping("/login")
    public String showLoginPage() {
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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  
        }
        return "redirect:/login";  
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO()); 
        return "register";
    }
    
    @PostMapping(value = "/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserResponse registerUser(@ModelAttribute @Valid UserDTO userDTO, BindingResult result) {
        UserResponse response = new UserResponse();

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

            response.setValidated(false);
            response.setErrorMessages(errors);
            return response; 
        } 

        User user = new User();
        user.setNome(userDTO.getNome());
        user.setCognome(userDTO.getCognome());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setTelefono(userDTO.getTelefono());
        user.setDataNascita(userDTO.getDataNascita());
        user.setRuolo(2); 

        try {
            userRepository.saveUser(user);
            response.setValidated(true);
            response.setSuccessMessage("Registrazione completata con successo!");
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                response.setValidated(false);
                response.setErrorMessages(Map.of("email", "Email già in uso. Riprova con un'email diversa."));
            } else {
                response.setValidated(false);
                response.setErrorMessages(Map.of("general", "Errore durante la registrazione. Riprova."));
            }
        }
        return response;
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