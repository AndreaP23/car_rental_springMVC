package com.xantrix.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xantrix.webapp.domain.User;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User user = new User();
        try {
            user.setUserId(row.getLong("user_id"));
            user.setNome(row.getString("nome"));
            user.setCognome(row.getString("cognome"));
            user.setEmail(row.getString("email"));
            user.setPassword(row.getString("password")); 
            user.setDataNascita(row.getDate("data_nascita"));
            user.setTelefono(row.getString("phone"));
            user.setRuolo(row.getInt("ruolo_id")); 
        } catch (Exception ex) {
            System.out.println("Errore!" + ex);
        }
        return user;
    }
}
