package com.codegym.service;

import com.codegym.model.Role;
import com.codegym.model.User;
import com.codegym.model.UserPrinciple;
import com.codegym.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class UserService implements UserDetailsService {

    public static List<User> listUser = new ArrayList<com.codegym.model.User>();
    public static List<Role> listRole = new ArrayList<>();

    public UserService() {
        listRole.add(new Role(1, "ROLE_ADMIN"));
        listRole.add(new Role(2, "ROLE_USER"));

        String password = "$2a$10$xMq9EwZvdKUuvgiaM2T1Iuw9A1EGXVZaCIUPEwn1Isa9ffvPqNabe";
        com.codegym.model.User userKai = new com.codegym.model.User(1, "Kai", password);
        Set<Role> roleKai = new HashSet<>();
        roleKai.add(listRole.get(0));
        userKai.setRoles(roleKai);
        com.codegym.model.User userSena = new com.codegym.model.User(2, "sena", password);
        Set<Role> roleSena = new HashSet<>();
        roleSena.add(listRole.get(1));
        userSena.setRoles(roleSena);
        listUser.add(userKai);
        listUser.add(userSena);
    }

    public List<UserDTO> findAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User u : listUser) {
            userDTOS.add(toDTO(u));
        }
        return userDTOS;
    }

    public UserDTO findById(Long id) {
        for (User user : listUser) {
            if (Objects.equals(user.getId(), id)) {
                return toDTO(user);
            }
        }
        return null;
    }

    public User findByUsername(String username) {
        for (User user : listUser) {
            if (Objects.equals(user.getUsername(), username)) {
                return user;
            }
        }
        return null;
    }

    public boolean add(User user) {
        for (User userExist : listUser) {
            if (Objects.equals(user.getId(), userExist.getId()) || Objects.equals(user.getUsername(), userExist.getUsername())) {
                return false;
            }
        }
        listUser.add(user);
        return true;
    }

    public void delete(int id) {
        listUser.removeIf(user -> user.getId() == id);
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRoles());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (User user : listUser) {
            if (Objects.equals(user.getUsername(), username)) {
                return UserPrinciple.build(user);
            }
        }
        return null;
    }
}
