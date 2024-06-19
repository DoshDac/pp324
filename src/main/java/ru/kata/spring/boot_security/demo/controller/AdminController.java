package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.showUsers());
        return "admin/admin";
    }

    @GetMapping("/create")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", userService.listRoles());
        return "admin/create";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("listRoles", userService.listRoles());
        return "admin/update";
    }

    @PatchMapping("/update")
    public String saveUpdateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}