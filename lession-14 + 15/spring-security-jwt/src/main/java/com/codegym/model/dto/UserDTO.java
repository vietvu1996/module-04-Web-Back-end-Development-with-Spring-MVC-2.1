package com.codegym.model.dto;

import com.codegym.model.Role;

import java.util.Set;

public class UserDTO {
    private int id;
    private String name;
    private Set<Role> roles;

    public UserDTO() {
    }

    public UserDTO(int id, String name, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
