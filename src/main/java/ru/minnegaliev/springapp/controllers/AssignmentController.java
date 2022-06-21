package ru.minnegaliev.springapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.minnegaliev.springapp.dao.AssignmentDAO;
import ru.minnegaliev.springapp.models.Assignment;
import ru.minnegaliev.springapp.util.AssignmentValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {
    private final AssignmentDAO assignmentDAO;
    private final AssignmentValidator assignmentValidator;

    public AssignmentController(AssignmentDAO assignmentDAO, AssignmentValidator assignmentValidator) {
        this.assignmentDAO = assignmentDAO;
        this.assignmentValidator = assignmentValidator;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("assignments", assignmentDAO.index());
        return "assignments/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("assignment", assignmentDAO.show(id));
        model.addAttribute("users", assignmentDAO.getUsersByAssignmentId(id));

        return "assignments/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("assignment") Assignment assignment) {
        return "assignments/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("assignments") @Valid Assignment assignment,
                         BindingResult bindingResult) {
        assignmentValidator.validate(assignment, bindingResult);

        if (bindingResult.hasErrors())
            return "assignments/new";

        assignmentDAO.save(assignment);
        return "redirect:/assignments";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("assignments", assignmentDAO.show(id));
        return "assignments/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("assignment") @Valid Assignment assignment, BindingResult bindingResult,
                         @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors())
            return "assignments/edit";

        assignmentDAO.update(id, assignment);
        return "redirect:/assignments";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        assignmentDAO.delete(id);
        return "redirect:/assignments";
    }
}
