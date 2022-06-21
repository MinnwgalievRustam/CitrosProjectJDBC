package ru.minnegaliev.springapp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.minnegaliev.springapp.models.Assignment;
import ru.minnegaliev.springapp.models.User;


import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index() {
        return jdbcTemplate.query("SELECT * FROM people", new BeanPropertyRowMapper<>(User.class));

    }
    public User show(Integer id) {
        return jdbcTemplate.query("SELECT * FROM people WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO people(surname, name, last_name, job_title) VALUES(?, ?, ?,?)", user.getSurname(),
                user.getName(),user.getLastName(),user.getJobTitle());
    }

    public void update(Integer id, User updateUser){
        jdbcTemplate.update("UPDATE people SET surname=?,name=?,last_name=?,job_title=? where id=?",updateUser.getSurname(),
                updateUser.getName(),updateUser.getLastName(),updateUser.getJobTitle());
    }
    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM people WHERE id=?", id);
    }

    public Optional<Assignment> getUsersOwner(Integer id){
        return jdbcTemplate.query("SELECT assignments * FROM people JOIN assignments on people.assignment_id = assignments.id"+
                "where people.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Assignment.class))
                .stream().findAny();
    }
    public void release(Integer id) {
        jdbcTemplate.update("UPDATE people SET assignment_id =NULL WHERE id=?", id);
    }
    public void assign(Integer id, Assignment selectedAssignment){
        jdbcTemplate.update("update people set assignment_id=? where id=?", selectedAssignment.getId(),id);
    }
}
