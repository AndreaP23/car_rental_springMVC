package com.xantrix.webapp.repository;

import java.util.List;

import com.xantrix.webapp.domain.User;
import com.xantrix.webapp.domain.Veicolo;

public interface UserRepository {

	List <User> listUser();
	
	void saveUser(User user);
	
	User findUserByEmail(String email);
	
	
}
