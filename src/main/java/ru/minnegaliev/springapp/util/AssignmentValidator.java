package ru.minnegaliev.springapp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.minnegaliev.springapp.dao.AssignmentDAO;
import ru.minnegaliev.springapp.models.Assignment;

@Component
public class AssignmentValidator implements Validator {

    private final AssignmentDAO assignmentDAO;

    public AssignmentValidator(AssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Assignment.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Assignment assignment = (Assignment) o;
        if (assignmentDAO.getAssignmentFullInfo(assignment.getTextOrder()).isPresent())
            errors.rejectValue("textOrder","","Поручение с таким содержанием существует");
    }
}
