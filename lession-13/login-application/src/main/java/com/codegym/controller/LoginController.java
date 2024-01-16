package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("")
    public ModelAndView loginPage() {
        ModelAndView view = new ModelAndView("/login");
        view.addObject("user", new User());
        return view;
    }

    @PostMapping("/doLogin")
    public ModelAndView login(@ModelAttribute("user") User user) {
        ModelAndView view = new ModelAndView("/dashboard");
        view.addObject("user", user);
        return view;
    }
}
