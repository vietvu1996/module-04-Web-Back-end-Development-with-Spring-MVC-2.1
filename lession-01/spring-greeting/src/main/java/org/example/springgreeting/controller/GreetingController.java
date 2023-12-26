package org.example.springgreeting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String welcomeToJava(String name,Model model){
        model.addAttribute("name", name);
        return "index";
    }
}
