package com.codegym.controller;

import com.codegym.exception.DuplicateEmailException;
import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ModelAndView showList() {
        ModelAndView view = new ModelAndView("list");
        List<Customer> customers = customerService.findAll();
        view.addObject("customers", customers);
        return view;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView view = new ModelAndView("create");
        view.addObject("customer", new Customer());
        return view;
    }

    @PostMapping("/save")
    public String save(Customer customer, RedirectAttributes redirect) throws DuplicateEmailException {
        customerService.save(customer);
        redirect.addFlashAttribute("message", "Created new customer successfully !");
        return "redirect:/customers";
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("error");
    }

    @GetMapping("/{id}/update")
    public ModelAndView update(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        ModelAndView view = new ModelAndView("update");
        view.addObject("customer", customer);
        return view;
    }

    @PostMapping("/update")
    public String update(Customer customer, RedirectAttributes redirect) throws DuplicateEmailException {
        customerService.save(customer);
        redirect.addFlashAttribute("message", "Updated new customer successfully !");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteForm(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        ModelAndView view = new ModelAndView("delete");
        view.addObject("customer", customer);
        return view;
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("message", "Removed customer successfully !");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewCustomer(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        ModelAndView view = new ModelAndView("view");
        view.addObject("customer", customer);
        return view;
    }
}
