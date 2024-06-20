package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class IniziBD {
    private UserService userService;
    private RoleRepository roleRepository;


    @Autowired
    public IniziBD(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    @Transactional
    public void initializeUsers() {
        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        roleRepository.save(userRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        User adminUser = new User("admin", "admin", "ali@mail.ru", 21L);
        adminUser.setRoles(adminRoles);
        userService.save(adminUser);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        User regularUser = new User("user", "user", "turpal@gmail.com", 23L);
        regularUser.setRoles(userRoles);
        userService.save(regularUser);
    }
}
