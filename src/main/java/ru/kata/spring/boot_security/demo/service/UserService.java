package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {  @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public List<User> showUsers();

    public void save(User user);

    public User getUser(Long id);

    public void delete(Long id);

    public void update(User user);

    public User findByUsername(String username);

    public List<Role> listRoles();

}
