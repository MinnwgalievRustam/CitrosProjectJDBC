package ru.minnegaliev.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.minnegaliev.springapp.dao.AssignmentDAO;
import ru.minnegaliev.springapp.dao.UserDAO;
import ru.minnegaliev.springapp.models.Assignment;
import ru.minnegaliev.springapp.models.User;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDAO userDAO;
    private final AssignmentDAO assignmentDAO;

    @Autowired
    public UserController(UserDAO userDAO, AssignmentDAO assignmentDAO) {
        this.userDAO = userDAO;
        this.assignmentDAO = assignmentDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDAO.index());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model, @ModelAttribute("assignment") Assignment assignment) {
        model.addAttribute("user", userDAO.show(id));

        Optional<Assignment> assignmentOwner = userDAO.getUsersOwner(id);

        if (assignmentOwner.isPresent())
            model.addAttribute("owner", assignmentOwner.get());
        else
            model.addAttribute("assignments", assignmentDAO.index());
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("users") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";

        userDAO.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("users", userDAO.show(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors())
            return "users/edit";

        userDAO.update(id, user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userDAO.delete(id);
        return "redirect:/users";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") Integer id) {
        userDAO.release(id);
        return "redirect:/users/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") Integer id, @ModelAttribute("assignment") Assignment selectedAssignment) {
        userDAO.assign(id, selectedAssignment);
        return "redirect:/users/" + id;
    }
}
