package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String statusName;
    @OneToMany(mappedBy = "status")
    private Set<Code> codes;

    public Status() {
    }

    public Status(int id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public Status(int id, String statusName, Set<Code> codes) {
        this.id = id;
        this.statusName = statusName;
        this.codes = codes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Set<Code> getCodes() {
        return codes;
    }

    public void setCodes(Set<Code> codes) {
        this.codes = codes;
    }
}
