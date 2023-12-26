package org.example.list_customer_management.controller;

import org.example.list_customer_management.bean.Customer;
import org.example.list_customer_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String showList(Model model) {
        model.addAttribute("customers",customerService.findAll());
        return "customers/list";
    }
}
