package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.showUsers());
        return "admin/admin";
    }

    @GetMapping("/create")
    public String addNewUser(ModelMap modelMap) {
        User user = new User();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("listRoles", userService.listRoles());
        modelMap.addAllAttributes(model);
        return "admin/create";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, ModelMap modelMap) {
        Map<String, Object> model = new HashMap<>();
        model.put("user", userService.getUser(id));
        model.put("listRoles", userService.listRoles());
        modelMap.addAllAttributes(model);
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
