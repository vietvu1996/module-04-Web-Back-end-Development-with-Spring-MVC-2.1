package com.personal_calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/submit")
public class CalculatorController {
    @GetMapping("")
    public String show() {
        return "index";
    }

    @PostMapping("")
    public String calculate(@RequestParam("firstOperand") int number1,
                            @RequestParam("secondOperand") int number2,
                            @RequestParam("submitButton") String operation,
                            Model model) {
        int result = 0;
        switch (operation) {
            case "Addition":
                result = number1 + number2;
                break;
            case "Subtraction":
                result = number1 - number2;
                break;
            case "Multiplication":
                result = number1 * number2;
                break;
            case "Division":
                result = number1 / number2;
                break;
        }
        model.addAttribute("result", result);
        return "index";
    }
}
