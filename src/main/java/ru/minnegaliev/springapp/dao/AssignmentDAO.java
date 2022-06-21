package ru.minnegaliev.springapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.minnegaliev.springapp.models.Assignment;
import ru.minnegaliev.springapp.models.User;

import java.util.List;
import java.util.Optional;

@Component
public class AssignmentDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AssignmentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Assignment> index() {
        return jdbcTemplate.query("SELECT * FROM assignments", new BeanPropertyRowMapper<>(Assignment.class));
    }

    public Assignment show(Integer id) {
        return jdbcTemplate.query("SELECT * FROM assignments WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Assignment.class))
                .stream().findAny().orElse(null);
    }

    public void save(Assignment assignment) {
        jdbcTemplate.update("INSERT INTO assignments(subject_order,sign_control,sign_performance,text_order) VALUES (?,?,?,?)",
                assignment.getSubjectOrder(), assignment.getSignControl(), assignment.getSignPerformance(), assignment.getTextOrder());
    }

    public void update(Integer id, Assignment assignmentUpdate) {
        jdbcTemplate.update("UPDATE assignments SET subject_order=?,sign_control=?,sign_performance=?,text_order=? where  id=?",
                assignmentUpdate.getSubjectOrder(), assignmentUpdate.getSignControl(), assignmentUpdate.getSignPerformance(), assignmentUpdate.getTextOrder(), id);
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM  assignments  WHERE id=?", id);
    }

    public Optional<Assignment> getAssignmentFullInfo(String textOrder) {
        return jdbcTemplate.query("SELECT * FROM assignments WHERE text_order=?", new Object[]{textOrder},
                new BeanPropertyRowMapper<>(Assignment.class)).stream().findAny();
    }

    public List<User> getUsersByAssignmentId(Integer id){
        return jdbcTemplate.query("SELECT * FROM people WHERE assignment_id =?", new Object[]{id},
                new BeanPropertyRowMapper<>(User.class));
    }
}
