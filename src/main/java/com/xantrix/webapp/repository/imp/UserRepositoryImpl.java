package com.xantrix.webapp.repository.imp;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xantrix.webapp.domain.User;
import com.xantrix.webapp.repository.UserMapper;
import com.xantrix.webapp.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<User> listUser(){
		String sql = "SELECT * FROM user";
		
		List<User> user = jdbcTemplate.query(sql, new UserMapper());
		//Test:
		//System.out.println("Numero di utenti trovati: " + user.size());
		return user;
	}
	
	@Override
    public void saveUser(User user) {
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String sql = "INSERT INTO user (nome, cognome, email, password, phone, data_nascita, ruolo_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getNome(), user.getCognome(), user.getEmail(), hashedPassword, user.getTelefono(), user.getDataNascita(), user.getRuolo());
    }
	
	@Override
    public User findUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
    }
	
	
}
