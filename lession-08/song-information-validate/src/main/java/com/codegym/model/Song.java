package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 800,message = "Name must be less than 800 characters")
    @Pattern(regexp = "^[a-zA-Z 0-9]*$", message = "Name contains invalid characters")
    private String name;

    @NotBlank
    @Size(max = 300, message = "Cannot input greater than 300 character")
    @Pattern(regexp = "^[a-zA-Z 0-9]*$", message = "Cannot input special character is: '@ ; , . = - + '")
    private String singer;

    @NotBlank
    @Size(max = 1000, message = "Cannot input greater than 300 character")
    @Pattern(regexp = "^[a-zA-Z 0-9]*$")
    private String typeSong;

    public Song() {
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

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTypeSong() {
        return typeSong;
    }

    public void setTypeSong(String typeSong) {
        this.typeSong = typeSong;
    }
}
