package com.codegym.service;

import com.codegym.model.Email;

import java.util.ArrayList;
import java.util.List;

public class EmailService implements IEmailService {
    private static final List<Email> emails;

    static {
        emails = new ArrayList<>();
        emails.add(new Email(1, "English", 5, true, "Viet"));
        emails.add(new Email(2, "Japanese", 10, false, "Hieu"));
        emails.add(new Email(3, "Vietnamese", 15, true, "Thao"));
        emails.add(new Email(4, "Chinese", 25, false, "Giang"));
        emails.add(new Email(5, "Korean", 50, true, "Huy"));
    }


    @Override
    public List<Email> findAll() {
        Email email = new Email();
        return emails;
    }

    @Override
    public void update(int id, Email email) {
        int index = emails.indexOf(findById(id));
        emails.set(index, email);
    }

    @Override
    public Email findById(int id) {
        return emails.get(id-1);
    }
}
