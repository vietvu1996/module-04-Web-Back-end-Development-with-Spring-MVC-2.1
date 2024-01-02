package com.codegym.service;

import com.codegym.model.Email;

import java.util.List;

public interface IEmailService {
    List<Email> findAll();

    Email findById(int id);

    void update(int id, Email email);
}
