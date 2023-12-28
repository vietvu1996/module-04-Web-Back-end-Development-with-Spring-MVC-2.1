package com.spices_of_sandwich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/save")
public class SandwichController {

    @GetMapping("")
    public String show() {
        return "index";
    }


    @PostMapping("")
    public String save(@RequestParam("spices") String[] spices, Model model) {
        model.addAttribute("spices", spices);
        return "spices";
    }

}
