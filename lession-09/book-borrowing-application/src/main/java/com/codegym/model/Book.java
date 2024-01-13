package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String author;
    @Column(name = "book_name")
    @NotBlank
    private String bookName;

    @Min(0)
    private Integer quantity;
    @Column(name = "year_of_release")
    @NotNull
    @Min(1)
    private Integer yearOfRelease;
    @OneToMany(mappedBy = "book")
    private Set<Code> codes;

    public Book() {
    }

    public Book(int id, String author, String bookName, Integer quantity, Integer yearOfRelease, Set<Code> codes) {
        this.id = id;
        this.author = author;
        this.bookName = bookName;
        this.quantity = quantity;
        this.yearOfRelease = yearOfRelease;
        this.codes = codes;
    }

    public Book(int id, String author, String bookName, Integer quantity, Integer yearOfRelease) {
        this.id = id;
        this.author = author;
        this.bookName = bookName;
        this.quantity = quantity;
        this.yearOfRelease = yearOfRelease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Set<Code> getCodes() {
        return codes;
    }

    public void setCodes(Set<Code> codes) {
        this.codes = codes;
    }
}
