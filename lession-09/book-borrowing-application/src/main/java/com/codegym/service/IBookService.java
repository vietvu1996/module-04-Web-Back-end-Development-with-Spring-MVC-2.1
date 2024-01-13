package com.codegym.service;

import com.codegym.exception.NotAvailableException;
import com.codegym.exception.WrongCodeException;
import com.codegym.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();

    Book findById(int id);

    Book save(Book book);

    void delete(int id);

    void borrow(Book book, int borrowQuantity) throws NotAvailableException;

    void returnBook(Book book, int returnCode) throws NotAvailableException, WrongCodeException;

    int getNextAvailableCode();

    boolean checkCode(int borrowCode);
}
