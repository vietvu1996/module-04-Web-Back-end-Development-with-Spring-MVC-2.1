package com.codegym.controller;

import com.codegym.model.Email;
import com.codegym.service.EmailService;
import com.codegym.service.IEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/email")
public class EmailController {
    private final IEmailService emailService = new EmailService();

    @GetMapping("")
    public String list(Model model) {
        List<Email> emails = emailService.findAll();
        model.addAttribute("emails", emails);
        return "list";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("email", emailService.findById(id));
        System.out.println(id);
        return "update";
    }

    @PostMapping("/update")
    public String update(Email email) {
        emailService.update(email.getId(), email);
        System.out.println(email.getId());
        return "redirect:/email";
    }
}
