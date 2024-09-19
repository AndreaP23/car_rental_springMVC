package com.xantrix.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.xantrix.webapp.domain.User;
import com.xantrix.webapp.repository.UserRepository;

@Controller
public class SuperUserController {
	
	@Autowired
	private UserRepository userRepository;
	
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
	public String listPrenotazione(HttpSession session, Model model){
		List<User> users = userRepository.listUser();
		model.addAttribute("users",users);
		User user = (User) session.getAttribute("user");
		if (user != null && user.getRuolo() == 1){
			return "listprenotazioni";
        } else {
            return "redirect:/login"; 
        }
	}

}
